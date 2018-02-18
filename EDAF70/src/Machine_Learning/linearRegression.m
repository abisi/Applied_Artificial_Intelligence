%Linear regression

%Data sets from Salammbo FR and EN, # of ocurrences of 'a'
salFr = load('salammbo_a_fr.txt');
salEn = load('salammbo_a_en.txt');

salFrx = salFr(:,1); %# of letters in chapter
salFry = salFr(:,2); %# of 'a' 
salEnx = salEn(:,1); 
salEny = salEn(:,2); 


%Scaling to 0-1
maxFr = max(salFrx);
minFr = min(salFrx);
maxEn = max(salEnx);
minEn = min(salEnx);

salFrxScaled = (salFrx - minFr) / (maxFr - minFr);
salEnxScaled = (salEnx - minEn) / (maxEn - minEn);

%Plot (data)
figure;
plot(salFrxScaled, salFry,'.');
xlabel('Number of letters in chapter');
ylabel('Occurences of the letter a');
hold on;
plot(salEnxScaled, salEny,'.');

%Gradient descent algorithm (batch or/and stochastic updates)
%Parameters
learningRate = 0.001;  %To be changed/updated if no divergence / try different values of alpha
maxiter = 10000;
w0 = [0; 0];
tol = 0.001;

%Batch
[wFrBatch,iterFrBatch] = batchGradient(salFrxScaled, salFry, w0, learningRate, tol, maxiter);
[wEnBatch,iterEnBatch] = batchGradient(salEnxScaled, salEny, w0, learningRate, tol, maxiter);
%Stochastic
[wFrSto,iterFrSto] = stochasticGradient(salFrxScaled, salFry, w0, learningRate, tol, maxiter);
[wEnSto,iterEnSto] = stochasticGradient(salEnxScaled, salEny, w0, learningRate, tol, maxiter);


%Plot(linear regression y = w0 + w1*x)
%Batch
xFr1 = min(salFrxScaled):max(salFrxScaled);
plot(xFr1, wFrBatch(1) + wFrBatch(2)*xFr1);
xEn1 = min(salEnxScaled):max(salEnxScaled);
plot(xEn1, wEnBatch(1) + wEnBatch(2)*xEn1);
%Stochastic
xFr2 = min(salFrxScaled):max(salFrxScaled);
plot(xFr2, wFrSto(1) + wFrSto(2)*xFr2);
xEn2 = min(salEnxScaled):max(salEnxScaled);
plot(xEn2, wEnSto(1) + wEnSto(2)*xEn2);
legend('French text','English text','Fr: batch', 'En: batch','Fr : stochastic', 'En: stochastic');
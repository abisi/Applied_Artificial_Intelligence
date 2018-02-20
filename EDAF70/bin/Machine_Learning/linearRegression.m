%Linear regression

%Data sets from Salammbo FR and EN, # of ocurrences of 'a'
salFr = load('salammbo_a_fr.txt');
salEn = load('salammbo_a_en.txt');

salFrx = salFr(:,1); %# of letters in chapter
salFry = salFr(:,2); %# of 'a' 
salEnx = salEn(:,1); 
salEny = salEn(:,2); 


%Scaling to 0-1
maxXFr = max(salFrx);
maxYFr = max(salFry);
maxXEn = max(salEnx);
maxYEn = max(salEny);

salFrXScaled = salFrx ./ maxXFr;
salFrYScaled = salFry ./ maxYFr;
salEnXScaled = salEnx ./ maxXEn;
salEnYScaled = salEny ./ maxYEn;

%Plot (data)
figure;
plot(salFrXScaled, salFrYScaled,'.');
xlabel('Number of letters in chapter');
ylabel('Occurences of the letter a');
hold on;
plot(salEnXScaled, salEnYScaled,'.');

%Gradient descent algorithm (batch or/and stochastic updates)
%Parameters
learningRate = 0.01;  %To be changed/updated if no divergence / try different values of alpha
maxiter = 1000000;
w0 = [0; 0];
tol = 0.001;

%Batch
[wFrBatch,iterFrBatch] = batchGradient(salFrXScaled, salFrYScaled, w0, learningRate, tol, maxiter);
[wEnBatch,iterEnBatch] = batchGradient(salEnXScaled, salEnYScaled, w0, learningRate, tol, maxiter);

%Stochastic
[wFrSto,iterFrSto] = stochasticGradient(salFrXScaled, salFrYScaled, w0, learningRate, tol, maxiter);
[wEnSto,iterEnSto] = stochasticGradient(salEnXScaled, salEnYScaled, w0, learningRate, tol, maxiter);


%Plot(linear regression y = w0 + w1*x)
%Batch
range = linspace(0,1);
plot(range, wFrBatch(1) + wFrBatch(2)*range);
plot(range, wEnBatch(1) + wEnBatch(2)*range);
%Stochastic
plot(range, wFrSto(1) + wFrSto(2)*range);
plot(range, wEnSto(1) + wEnSto(2)*range);
legend('French text','English text','Fr: batch', 'En: batch','Fr : stochastic', 'En: stochastic');

save parameters.mat salFrXScaled salFrYScaled salEnXScaled salEnYScaled w0 learningRate tol maxiter

%% Learning Rate vs iterations

% load paramters
load parameters.mat

maxiter = 20000;

figure;
hold on

for learningRate = 10.^linspace(-6,0,12)
    
    % Batch
    [wFrBatch,iterFrBatch] = batchGradient(salFrXScaled, salFrYScaled, w0, learningRate, tol, maxiter);
    [wEnBatch,iterEnBatch] = batchGradient(salEnXScaled, salEnYScaled, w0, learningRate, tol, maxiter);

    %Stochastic
    [wFrSto,iterFrSto] = stochasticGradient(salFrXScaled, salFrYScaled, w0, learningRate, tol, maxiter);
    [wEnSto,iterEnSto] = stochasticGradient(salEnXScaled, salEnYScaled, w0, learningRate, tol, maxiter);
    
    % plot results
    plot(log(learningRate),iterFrBatch,'+')
    plot(log(learningRate),iterEnBatch,'+')
    plot(log(learningRate),iterFrSto,'ro')
    plot(log(learningRate),iterEnSto,'ro')
    
end
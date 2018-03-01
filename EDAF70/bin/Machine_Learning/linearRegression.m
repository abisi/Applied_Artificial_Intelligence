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
hold on;
plot(salFrXScaled, salFrYScaled,'b*'); %French in blue
xlabel('Frequencies of letters per chapter');
ylabel('Frequencies of the letter \it a');
title('Letter frequencies in \itSalaambô', 'Interpreter','tex')
plot(salEnXScaled, salEnYScaled,'ro'); %English in green

%Gradient descent algorithm (batch or/and stochastic updates)
%Parameters
learningRate = 0.01;  %To be changed/updated if no divergence / try different values of alpha
maxiter = 1000000;
w0 = [0; 0];
tol = 0.001;

%Batch
[wFrBatch,iterFrBatch,timeFrBatch] = batchGradient(salFrXScaled, salFrYScaled, w0, learningRate, tol, maxiter);
[wEnBatch,iterEnBatch,timeEnBatch] = batchGradient(salEnXScaled, salEnYScaled, w0, learningRate, tol, maxiter);

%Stochastic
[wFrSto,iterFrSto,timeFrSto] = stochasticGradient(salFrXScaled, salFrYScaled, w0, learningRate, tol, maxiter);
[wEnSto,iterEnSto,timeEnSto] = stochasticGradient(salEnXScaled, salEnYScaled, w0, learningRate, tol, maxiter);


%Plot(linear regression y = w0 + w1*x)
%Batch
range = linspace(0,1);
plot(range, wFrBatch(1) + wFrBatch(2)*range);
plot(range, wEnBatch(1) + wEnBatch(2)*range);
%Stochastic
plot(range, wFrSto(1) + wFrSto(2)*range);
plot(range, wEnSto(1) + wEnSto(2)*range);
legend('French text','English text','Fr: batch, y = 0.002 + 0.986x ', 'En: batch, y = -0.0004 + 0.994x','Fr : stochastic, y = 0.009 + 0.983x', 'En: stochastic, y = -0.0007 + 0.995x');
%Zoom in
axes('position',[.65 .175 .25 .25]);
box on % put box around new pair of axes
index = (range > 0.485) & (range < 0.515);
hold on;
plot(range(index), wFrBatch(1) + wFrBatch(2)*range(index));
plot(range(index), wEnBatch(1) + wEnBatch(2)*range(index));
plot(range(index), wFrSto(1) + wFrSto(2)*range(index));
plot(range(index), wEnSto(1) + wEnSto(2)*range(index));
axis tight;

save parameters.mat salFrXScaled salFrYScaled salEnXScaled salEnYScaled w0 learningRate tol maxiter

%% Learning rate vs iterations

% Load parameters
load parameters.mat
maxiter = 20000;
learningRate = 10.^linspace(-6,0,12);
iterFrB = [];
iterEnB = [];
iterFrS = [];
iterEnS = [];

for i = 1:length(learningRate)
    
    % Batch
    [wFrBatch,iterFrBatch] = batchGradient(salFrXScaled, salFrYScaled, w0, learningRate(i), tol, maxiter);
    iterFrB = [iterFrB iterFrBatch];
    [wEnBatch,iterEnBatch] = batchGradient(salEnXScaled, salEnYScaled, w0, learningRate(i), tol, maxiter);
    iterEnB = [iterEnB iterEnBatch];
    %Stochastic
    [wFrSto,iterFrSto] = stochasticGradient(salFrXScaled, salFrYScaled, w0, learningRate(i), tol, maxiter);
    iterFrS = [iterFrS iterFrSto];
    [wEnSto,iterEnSto] = stochasticGradient(salEnXScaled, salEnYScaled, w0, learningRate(i), tol, maxiter);
    iterEnS = [iterEnS iterEnSto];

    
end

    %Plot results
    figure;
    subplot(2,1,1); 
    hold on;
    stem(log(learningRate),iterFrB,'b*','LineStyle', ':'); 
    stem(log(learningRate),iterEnB,'ro', 'LineStyle', ':');
    xlabel('Learning rate \alpha');
    ylabel('Number of iterations');
    title('Batch gradient descent');
    legend('French','English');
    
    subplot(2,1,2); 
    hold on;
    stem(log(learningRate),iterFrS,'b*','LineStyle', ':');
    stem(log(learningRate),iterEnS,'ro','LineStyle', ':');
    xlabel('Learning rate \alpha');
    ylabel('Number of iterations');
    title('Stochastic gradient descent');
    legend('French','English');
    
    
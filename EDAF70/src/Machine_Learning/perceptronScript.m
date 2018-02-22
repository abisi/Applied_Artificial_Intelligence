% Load parameters into workspace
X = salammbotext('salammbo_a_en.txt','salammbo_a_fr.txt');

% Define parameters and execute the perceptron algorithm
alpha = 1;
matchingRate = 0.95;
maxiter = 100000;

[w,iter,time] = perceptron(X,alpha,matchingRate,maxiter);

% Plot results : y = w_0 + w_1 * x_1

% Normalize data
Xplot = X;
Xplot(:,3) = Xplot(:,3)./max(Xplot(:,3));
Xplot(:,5) = Xplot(:,5)./max(Xplot(:,5));

% Extract line
range = [ones(1,100) ; linspace(0,1)];
y = -(w(1:2,:)'*range)./w(3);

% Plot
figure;
hold on
plot(Xplot(1:15,3),Xplot(1:15,5),'ro'); 
plot(Xplot(16:30,3),Xplot(16:30,5),'*');
plot(range(2,:),y(1,:))
xlabel('Frequencies of letters per chapter');
ylabel('Frequencies of the letter \it a');
title('Perceptron classification');
legend('English','French','Linear classification line');
hold off

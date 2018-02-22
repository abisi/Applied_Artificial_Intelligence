% Load parameters into workspace
X = salammbotext('salammbo_a_en.txt','salammbo_a_fr.txt');

% Define parameters and execute the perceptron algorithm
alpha = 1;
tol = 0.0001;
maxiter = 100000;

[w,iter,time] = logisticregression(X,alpha,maxiter,tol);

% plot results : y = w_0 + w_1 * x_1

% normalize data
Xplot = X;
Xplot(:,3) = Xplot(:,3)./max(Xplot(:,3));
Xplot(:,5) = Xplot(:,5)./max(Xplot(:,5));

% extract line
range = [ones(1,100) ; linspace(0,1)];
y = -(w(1:2,:)'*range)./w(3);

% plot
figure;
plot(Xplot(1:15,3),Xplot(1:15,5),'ro'); hold on
plot(Xplot(16:30,3),Xplot(16:30,5),'*');
plot(range(2,:),y(1,:))
legend('english','french','lin. classification line');
hold off

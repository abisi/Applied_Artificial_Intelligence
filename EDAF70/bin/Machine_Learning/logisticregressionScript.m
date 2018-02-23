% Load parameters into workspace
X = salammbotext('salammbo_a_en.txt','salammbo_a_fr.txt');

% Define parameters and execute the perceptron algorithm
alpha = 1;
tol = 0.0001;
maxiter = 100000;

[w,iter,time] = logisticregression(X,alpha,maxiter,tol);

% Plot results : y = w_0 + w_1 * x_1

% Normalize data
Xplot = X;
Xplot(:,3) = Xplot(:,3)./max(Xplot(:,3));
Xplot(:,5) = Xplot(:,5)./max(Xplot(:,5));

% Extract line
range = [ones(1,100) ; linspace(0,1)];
y = -(w(1:2,:)'*range)./w(3);
zoom = [ones(1,100) ; linspace(0.4,0.6)];
y_zoom = -(w(1:2,:)'*zoom)./w(3);

%Plot
figure;
hold on;
plot(Xplot(1:15,3),Xplot(1:15,5),'ro');
plot(Xplot(16:30,3),Xplot(16:30,5),'b*');
plot(range(2,:),y(1,:),'k');
xlabel('Frequencies of letters per chapter');
ylabel('Frequencies of the letter \it a');
title('Logistic regression classification');
legend('English','French','Linear classification line');
%Zoom in
axes('position',[.65 .175 .25 .25]);
box on % put box around new pair of axes
%index = (range > 0.4) & (range < 0.6);
plot(Xplot([1 2 4 6 8 9 11 12],3),Xplot([1 2 4 6 8 9 11 12],5),'ro'); hold on;
plot(Xplot([16 17 19 21 23 24 26 27],3),Xplot([16 17 19 21 23 24 26 27],5),'b*');
plot(zoom(2,:), y_zoom(1,:),'k');
hold off;

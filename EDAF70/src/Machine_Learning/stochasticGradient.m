function [w, iter] = stochasticGradient(dataX, dataY, w0, alpha, tol, maxiter)
%Gradient descent algorithm (univariate linear regression) - stochastic
% updates ('online learning', updates carried out one example at a time)
%      output: w, the solution (weights w0, w1)
%              iter, # of iterations needed
%
%      input: A, matrix containing the training sets of 15 points in x,y
%                plane
%             w0, inital guess for the weights vector, w(1) = w_0, w(2) =
%             w_1
%             tol, tolerance threshold
%             maxiter, maximal number of iterations allowed
%

%Sum of squared errors as defined in the slides
loss = @(w) sum((dataY - (w(1) + w(2).*dataX)).^2); 
dLoss_w0 = @(w) -2 * sum(dataY - (w(1) + w(2).*dataX));
dLoss_w1 = @(w) -2 * sum((dataY - (w(1) + w(2).* dataX)).*dataX) ; 

% Define parameters
q = length(dataX); %Data set of q examples(15)
X = repmat([ones(15,1) dataX], floor(maxiter/q)+q, 1);
Y = repmat(dataY,floor(maxiter/q)+q, 1);

iter = 1;
w = w0;

gradientLoss = [dLoss_w0(w); dLoss_w1(w)];


while (iter < maxiter && norm(gradientLoss) > tol)
    
    delta = Y(iter) - X(iter,:)*w;
    w = w + alpha * delta * X(iter,:)';
    
    gradientLoss = [dLoss_w0(w); dLoss_w1(w)];
    iter = iter + 1;
end
    
end

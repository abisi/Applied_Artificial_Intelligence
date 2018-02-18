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
X(:,1) = ones(15,1);
X(:,2) = dataX;
iter = 1;
w = w0;
eval = [];
eval(iter) = loss(w);
while (iter < maxiter && eval(end) > tol)
     for i = 1:size(dataX(:,1))
     w = w + alpha * (dataY(i) - X(i)*w)*X(i);    
     %w(1) = w(1) + alpha.*(dataX(i) - (w(1) + w(2).*dataY(i)));
     %w(2) = w(2) + alpha.*dataY(i).*(dataX(i) - (w(1) + w(2).*dataY(i)));
     end
eval(iter) = loss(w);
iter = iter + 1;
end
    
end

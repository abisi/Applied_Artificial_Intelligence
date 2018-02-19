function [w, iter] = batchGradient(dataX, dataY, w0, alpha, tol, maxiter)
%Gradient descent algorithm (univariate linear regression) - batch updates
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

loss = @(w) sum((dataY - (w(1) + w(2).*dataX)).^2);   %Sum of squared errors as defined in the slides (TO CHANGE)
dLoss_w0 = @(w) -2 * sum(dataY - (w(1) + w(2).*dataX));
dLoss_w1 = @(w) -2 * sum((dataY - (w(1) + w(2).* dataX)).*dataX) ; 

% Define parameters
X = [ones(15,1) dataX];
q = length(dataX); %Data set of q examples(15)

% Initialize
iter = 1;
w = w0;
gradientLoss = [dLoss_w0(w); dLoss_w1(w)];

while (norm(gradientLoss) > tol && iter < maxiter)
    w = w + (alpha/q)*X'*(dataY - X*w);
    %w(1) = w(1) - 2*(alpha./15)*dLoss_w0(w);
    %w(2) = w(2) - 2*(alpha./15)*dLoss_w1(w);
    gradientLoss = [dLoss_w0(w); dLoss_w1(w)];
    iter = iter + 1;
end

end



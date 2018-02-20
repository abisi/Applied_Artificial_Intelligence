function [w, iter, time] = stochasticGradient(dataX, dataY, w0, alpha, tol, maxiter)
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

% initialize timing
tic;
tstart = tic;

%Sum of squared errors as defined in the slides
loss = @(w) sum((dataY - (w(1) + w(2).*dataX)).^2); 
dLoss_w0 = @(w) -2 * sum(dataY - (w(1) + w(2).*dataX));
dLoss_w1 = @(w) -2 * sum((dataY - (w(1) + w(2).* dataX)).*dataX) ; 

% Define parameters
q = length(dataX); %Data set of q examples(15)
X = [ones(q,1) dataX];

% Initialize running data
iter = 0;
w = w0;
gradientLoss = [dLoss_w0(w); dLoss_w1(w)];

while (iter < maxiter && norm(gradientLoss) > tol)
    
    % shuffle samples
    perm = randperm(q);
    Xperm = X(perm',:);
    Yperm = dataY(perm',:);
    
    % go throgh the shuffled samples
    for i = 1:q
        delta = Yperm(i) - Xperm(i,:)*w;
        w = w + alpha * delta * Xperm(i,:)';
    end
    
    % evaluate the gradient loss
    gradientLoss = [dLoss_w0(w); dLoss_w1(w)];
    
    % increment the iteration
    iter = iter + 1;
    
end

% time elapsed
time = toc(tstart);
    
end

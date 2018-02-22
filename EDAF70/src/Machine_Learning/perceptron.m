function [w,iter,time] = perceptron(X,alpha,matchingRate,maxiter)

    % Initialize timing
    tic;
    tstart = tic;

    % Define parameters
    q = size(X,1);
    n = size(X,2);
    
    % Scale input
    features = floor(n/2);
    M = zeros(q,features);
    Y = X(:,1);
    
    % Extract relevant features from matrix and scale them to [0,1]
    for i = 1:features 
        M(:,i) = X(:,2*i+1)./max(X(:,2*i+1));
    end
    
    % Running parameters
    matches = 0;    
    iter = 0;
    w = zeros(3,1);
    
    %We permute and update until we reach a defined matching rate (matchingRate*q)
    while (matches <= matchingRate*q && iter < maxiter) 
       
        % Reset matches
        matches = 0;
         
        % Shuffle samples
        perm = randperm(q);
        Xperm = [ones(q,1) M(perm',:)];
        Yperm = Y(perm',1);
        
        for i=1:q 
            
           y_tilde = H(w,Xperm(i,:));
           delta = Yperm(i) - y_tilde;
           
           if delta == 0  %i.e. correct classification as y = y_tilde
               matches = matches + 1;
           end
           
           w = w + alpha*delta*Xperm(i,:)';
           
        end
        
        iter = iter + 1;
        
    end
    
    % Time
    time = toc(tstart);

end
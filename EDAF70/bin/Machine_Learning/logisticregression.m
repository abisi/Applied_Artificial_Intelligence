function [w,iter,time] = logisticregression(X,alpha,maxiter,tol)

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
    
    % running parameters
    iter = 0;
    w = zeros(3,1);
    
    while (iter < maxiter)
       

        % shuffle samples
        perm = randperm(q);
        Xperm = [ones(q,1) M(perm',:)];
        Yperm = Y(perm',1);
        
        for i=1:q 
            
           h_w = H_log(w,Xperm(i,:));
           delta = (Yperm(i) - h_w)*h_w*(1-h_w);
           
           if norm(delta) < tol
               time = toc(tstart);
               return;
           end
           
           w = w + alpha*delta*Xperm(i,:)';
           
        end
        
        iter = iter + 1;
        
    end
    
    % time
    time = toc(tstart);

end
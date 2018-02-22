function logistic = H_log(w,x)
%For the logistic regression classifier

logistic = 1/(1+exp(-x*w)); 

end
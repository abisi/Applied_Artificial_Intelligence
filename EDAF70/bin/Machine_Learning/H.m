function y_tilde = H(w,x)
%For the perceptron classifier

if (x*w >= 0)
    y_tilde = 1;
else
    y_tilde = 0;
end

end
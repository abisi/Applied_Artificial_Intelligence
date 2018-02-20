% Load parameters into workspace
X = salammbotext('salammbo_a_en.txt','salammbo_a_fr.txt');

% Execute the perceptron algorithm
w = perceptron(X);
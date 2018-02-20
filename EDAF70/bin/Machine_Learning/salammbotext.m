function X = salammbotext(salammbo_en, salammbo_fr)

X_en = importfile_en(salammbo_en);
X_fr = importfile_en(salammbo_fr);

samples = size(X_en,1);

X_en = [zeros(samples,1), ones(samples,1), X_en(:,1), 2* ones(samples,1), X_en(:,2)];
X_fr = [ones(samples,1), ones(samples,1), X_fr(:,1), 2* ones(samples,1), X_fr(:,2)];

X = [X_en ; X_fr];

end
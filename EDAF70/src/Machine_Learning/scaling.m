%Scaling method [values from 0 to 1]

%Import the files as 2D matrices
salammboFr = importfr('salammbo_a_fr.txt', 1, 15);
salammboEn = importen('salammbo_a_en.txt', 1, 15);

%Scaling of the number of 'a' only
salFrNorm = (salammboFr-min(salammboFr(:,2))) ./ (max(salammboFr(:,2)-min(salammboFr(:,2))));
salEnNorm = (salammboEn-min(salammboEn(:,2))) ./ (max(salammboEn(:,2)-min(salammboEn(:,2))));
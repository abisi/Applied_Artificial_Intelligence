
text = importTextFile('HMMLocalizer.txt');
[rows, cols] = size(text);
MEANS = mean(text,1);
p = polyfit(1:cols,MEANS,15);
f = fit([1:cols]',MEANS','exp1');
figure;
plot(MEANS); hold on
plot(polyval(p,1:cols));
%plot(f,1:cols,MEANS);
hold off
MEAN = mean(MEANS(20:end))
clear

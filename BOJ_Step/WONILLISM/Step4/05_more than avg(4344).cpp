#include<cstdio>
int a[1000];
int main() {
	int c, n, cnt;
	double avg;
	scanf("%d", &c);
	while (c--) {
		cnt = 0, avg = 0;
		scanf("%d", &n);
		for (int i = 0; i < n; i++) {
			scanf("%d", &a[i]);
			avg += a[i];
		}
		avg = (double)avg / n;
		for (int i = 0; i < n; i++)
			if (a[i] > avg)cnt++;
		printf("%.3lf%%\n", (double)cnt / n * 100);
	}

	return 0;
}
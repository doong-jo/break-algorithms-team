#include<cstdio>
int cnt;
void Process(int n) {
	for (int i = 1; i <= n; i++) {
		if (i < 100)cnt++;
		if (i >= 100 && i < 1000)
			if (i % 10 - (i / 10) % 10 == (i / 10) % 10 - i / 100)
				cnt++;
	}
	printf("%d", cnt);
}
int main() {
	int n;
	scanf("%d", &n);
	Process(n);
	return 0;
}
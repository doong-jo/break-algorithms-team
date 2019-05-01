#include<cstdio>
int main() {
	int n, sum = 0;
	char a;
	scanf("%d", &n);
	for (int i = 0; i < n; i++) {
		scanf(" %c", &a);
		sum += a - 48;
	}
	printf("%d", sum);
	return 0;
}
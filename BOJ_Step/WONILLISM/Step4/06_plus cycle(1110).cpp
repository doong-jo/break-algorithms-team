#include<cstdio>
int main() {
	int a, b, c, d = 0;
	scanf("%d", &a);
	b = a;
	while (1) {
		c = b / 10 + b % 10;
		b = (b % 10) * 10 + c % 10;
		d++;
		if (a == b)break;
	}
	printf("%d", d);
	return 0;
}
#include<cstdio>
int main() {
	char str[101];
	scanf("%s", str);
	int i = 1;
	while (str[i-1]) {
		printf("%c", str[i-1]);
		if (i % 10 == 0)	printf("\n");
		i++;
	}
	return 0;
}
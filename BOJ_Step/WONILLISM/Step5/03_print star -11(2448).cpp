#include<cstdio>
char a[3072][6144];
//1. 2n * n 만큼의 배열을 초기화한다
//   문자열의 끝부분인 n-1부분은 null문자 처리
//	 나머지는 공백처리
//2. 재귀함수를 이용해서 가장 작은 삼각형
//     *  
//    * *
//   *****
//  의 꼭지점 좌표를 기준으로 좌, 우에 그린다
void printStar(int n, int x, int y) {
	if (n == 3) {
		a[y][x] = '*';
		a[y + 1][x - 1] = '*';
		a[y + 1][x + 1] = '*';
		a[y + 2][x - 2] = '*';
		a[y + 2][x - 1] = '*';
		a[y + 2][x] = '*';
		a[y + 2][x + 1] = '*';
		a[y + 2][x + 2] = '*';
		return;
	}
	printStar(n / 2, x, y);
	printStar(n / 2, x + n / 2, y + n / 2);
	printStar(n / 2, x - n / 2, y + n / 2);
}
int main() {
	int n;
	scanf("%d", &n);
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < 2 * n - 1; j++) {
			if (j == 2 * n - 1) a[i][j] = '\0';
			else a[i][j] = ' ';
		}
	}
	printStar(n, n - 1, 0);
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < 2 * n - 1; j++) {
			printf("%c", a[i][j]);
		}
		printf("\n");
	}
	return 0;
}
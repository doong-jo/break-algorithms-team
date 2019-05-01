#include<cstdio>
char a[3072][6144];
//1. 2n * n ��ŭ�� �迭�� �ʱ�ȭ�Ѵ�
//   ���ڿ��� ���κ��� n-1�κ��� null���� ó��
//	 �������� ����ó��
//2. ����Լ��� �̿��ؼ� ���� ���� �ﰢ��
//     *  
//    * *
//   *****
//  �� ������ ��ǥ�� �������� ��, �쿡 �׸���
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
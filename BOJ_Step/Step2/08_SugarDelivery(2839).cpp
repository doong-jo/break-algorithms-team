#include<cstdio>
//5�� �������� �� ������ �������ٸ� �״�� ���
//�׷��� �ʴٸ� -3�� �ϰ� ī��Ʈ +1
//�ݺ�
int main() {
	int n;
	int cnt = 0;
	scanf("%d", &n);
	while (1) {
		if (n % 5 == 0) {
			cnt += n / 5;
			printf("%d", cnt);
			break;
		}
		else if (n <= 0) {
			printf("-1");
			break;
		}
		else {
			n -= 3;
			cnt++;
		}
	}
	return 0;
}
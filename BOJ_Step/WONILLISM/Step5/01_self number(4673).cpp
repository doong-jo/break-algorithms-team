#include<cstdio>

bool N[10001];
int Process(int n) {
	int sum = n;		//�ڱ��ڽ� ������
	while (n) {
		sum += n % 10;	//1���ڸ� ���� ������
		n /= 10;			//�� �ڸ� ����
	}
	return sum;
}
int main() {
	for (int i = 1; i < 10001; i++) {
		int idx = Process(i);
		if (idx <= 10001)
			N[idx] = true;	//�����ѹ��� �ƴ� ���� check
	}
	for (int i = 1; i < 10001; i++)
		if (!N[i]) printf("%d\n", i);
	return 0;
}
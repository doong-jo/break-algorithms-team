/*
 *#2309 �ϰ�������
 *��ȩ���� �������� ��¥ �ϰ������̸� ã�ƶ�.
 *�ϰ��������� Ű�� ������ 100
 *
 *�־����� Ű�� 100�� �����ʴ� �ڿ���
 *�����̴� ��� Ű�� �ٸ���
 *������ ������ ���� ������ ��쿡�� �ƹ��ų� ���
 *�ϰ��������� Ű�� ������������ ���, ã�� �� ���� ��� ����
 */

#include<stdio.h>
#include<algorithm>
	using namespace std;

const int N = 9;
const int TOTAL = 100;

//��ȩ ������ Ű ���� ���� ��ȯ�ϴ� �Լ�
int totalSum(const int *arr) {
	int sum = 0;
	for (int i = 0; i < N; i++) {
		sum += arr[i];
	}
	return sum;
}

//��ȩ ������ Ű�� �� �տ��� �� ���� ������ Ű�� �� ���� 100�� ���� Ž��
int solve(int *arr) {
	int sum = totalSum(arr);
	int answer = 0;

	//2���� Ű�� ���� ���ü� �ִ� ��� ��츦 Ž�� ������
	//���� �������� ��� ���� ������ ������ ��ȯ
	for (int i = 0; i < N; i++) {
		for (int j = i + 1; j < N; j++) {
			answer = sum - (arr[i] + arr[j]);
			if (answer == TOTAL) {
				arr[i] = -1;
				arr[j] = -1;
				return 0;
			}
		}
	}
	return -1;
}
int main() {
	int arr[N];
	for (int i = 0; i < N; i++) {
		scanf("%d", &arr[i]);
	}

	solve(arr);
	sort(arr, arr + N); // <algorithm> �����Լ� �������� ����

	for (int i = 2; i < N; i++) {
		printf("%d\n", arr[i]);
	}

	return 0;
}
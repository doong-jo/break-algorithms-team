#include<cstdio>

//1���� (MON TUE WED THU FRI SAT SUN)
//1�� 1�� ���� ������
//x�� �������� �� �ϼ� + y �� 7�� ���� �������� �ش��ϴ� ����
int Month[12] = { 31, 28, 31, 30, 31, 30, 31, 31, 30 ,31, 30, 31 };
char Day[7][4] = { "SUN", "MON","TUE" ,"WED" ,"THU" ,"FRI" ,"SAT"};
int main() {
	int x, y;
	scanf("%d %d", &x, &y);
	int date = 0;
	for (int i = 0; i < x-1; i++) {
		date += Month[i];
	}
	date += y;
	printf("%s", Day[date%7]);

	return 0;
}
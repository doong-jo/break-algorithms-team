#include<cstdio>

//1주일 (MON TUE WED THU FRI SAT SUN)
//1월 1일 부터 월요일
//x월 전까지의 총 일수 + y 를 7로 나눈 나머지가 해당하는 요일
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
#include<cstdio>

bool N[10001];
int Process(int n) {
	int sum = n;		//자기자신 더해줌
	while (n) {
		sum += n % 10;	//1의자리 숫자 더해줌
		n /= 10;			//한 자리 줄임
	}
	return sum;
}
int main() {
	for (int i = 1; i < 10001; i++) {
		int idx = Process(i);
		if (idx <= 10001)
			N[idx] = true;	//셀프넘버가 아닌 수를 check
	}
	for (int i = 1; i < 10001; i++)
		if (!N[i]) printf("%d\n", i);
	return 0;
}
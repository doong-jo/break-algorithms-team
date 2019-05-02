#include<stdio.h>

int N;
int S;
int count = 0;
int numArr[25];

void solve(int sum, int step) {

	if (step == N) {
		if (sum == S) count++;
		return;
	}

	solve(sum + numArr[step], step + 1);
	solve(sum, step + 1);
}
int main(void) {
	scanf("%d%d", &N, &S);

	for (int i = 0; i < N; i++) {
		scanf("%d", &numArr[i]);
	}
	solve(0, 0);

	if (S == 0) {
		count -= 1;
	}
	printf("%d\n", count);

	return 0;
}
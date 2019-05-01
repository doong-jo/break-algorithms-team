#include<cstdio>
int A[1000];
int main() {
	int N, Max = 0;
	double Result = 0;
	scanf("%d", &N);
	for (int i = 0; i < N; i++) {
		scanf("%d", &A[i]);
		if (Max < A[i])Max = A[i];
	}
	for (int i = 0; i < N; i++) 
		Result += (double)A[i] / Max * 100;
	printf("%.2lf", (double)Result / N);
	return 0;
}
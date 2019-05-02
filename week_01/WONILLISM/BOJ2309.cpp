/*
 *#2309 일곱난쟁이
 *아홉명의 난쟁이중 진짜 일곱난쟁이를 찾아라.
 *일곱난쟁이의 키의 총합은 100
 *
 *주어지는 키는 100을 넘지않는 자연수
 *난쟁이는 모두 키가 다르다
 *가능한 정답이 여러 가지인 경우에는 아무거나 출력
 *일곱난쟁이의 키를 오름차순으로 출력, 찾을 수 없는 경우 없다
 */

#include<stdio.h>
#include<algorithm>
	using namespace std;

const int N = 9;
const int TOTAL = 100;

//아홉 난쟁이 키 값의 합을 반환하는 함수
int totalSum(const int *arr) {
	int sum = 0;
	for (int i = 0; i < N; i++) {
		sum += arr[i];
	}
	return sum;
}

//아홉 난쟁이 키의 총 합에서 두 명의 난쟁이 키를 뺀 합이 100인 것을 탐색
int solve(int *arr) {
	int sum = totalSum(arr);
	int answer = 0;

	//2명의 키를 빼서 나올수 있는 모든 경우를 탐색 하지만
	//답이 여러개일 경우 먼저 나오는 답으로 반환
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
	sort(arr, arr + N); // <algorithm> 내장함수 오름차순 정렬

	for (int i = 2; i < N; i++) {
		printf("%d\n", arr[i]);
	}

	return 0;
}
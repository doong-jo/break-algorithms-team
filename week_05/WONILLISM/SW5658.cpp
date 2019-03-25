#include<iostream>
#include<fstream>
using namespace std;

/*문제
각 변에 다음과 같이 16진수 숫자(0~F)가 적혀 있는 보물상자가 있다.

보물 상자의 뚜껑은 시계방향으로 돌릴 수 있고, 한 번 돌릴 때마다 숫자가 시계방향으로 한 칸씩 회전한다.



각 변에는 동일한 개수의 숫자가 있고, 시계방향 순으로 높은 자리 숫자에 해당하며 하나의 수를 나타낸다.

예를 들어 [Fig.1]의 수는 1A3, B54, 8F9, D66이고, [Fig.2]의 수는 61A, 3B5, 48F, 9D6이다.

보물상자에는 자물쇠가 걸려있는데, 이 자물쇠의 비밀번호는 보물 상자에 적힌 숫자로 만들 수 있는 모든 수 중, 

K번째로 큰 수를 10진 수로 만든 수이다.

N개의 숫자가 입력으로 주어졌을 때, 보물상자의 비밀 번호를 출력하는 프로그램을 만들어보자.

(서로 다른 회전 횟수에서 동일한 수가 중복으로 생성될 수 있다. 크기 순서를 셀 때 같은 수를 중복으로 세지 않도록 주의한다.)



[제약 사항]


N은 4의 배수이고, 8이상 28이하의 정수이다. (8 ≤ N ≤ 28)
N개의 숫자는 각각 0이상 F이하로 주어진다. (A~F는 알파벳 대문자로만 주어진다.)
K는 생성 가능한 수의 개수보다 크게 주어지지 않는다.

*/
const int MAX_N = 29;
const int MAX_T = 100;
const int MAX_SUM = 3*28;
int T;
int N[MAX_T], K[MAX_T];			//T : 테스트 케이스 개수, 8 <= N <= 28 , K <= N
char S[MAX_T][MAX_N];			//테스트 케이스 만큼 문자열 개수
int sum[MAX_SUM];
int Temp[MAX_SUM];

void Input() {
	//cin >> T;
	//for (int i = 0; i < T; i++) {
	//	cin >> N[i] >> K[i];
	//	cin >> S[i];
	//	cout << S[i];
	//}
	ifstream fin("1.in");
	fin >> T;
	for (int i = 0; i < T; i++) {
		fin >> N[i] >> K[i];
		fin >> S[i];
	}
	fin.close();
}
void Rotate(int t,int n) {
	int tmp;
	tmp = S[t][n-1];
	for (int i = n-1; i > 0; i--) {
		S[t][i] = S[t][i - 1];
	}
	S[t][0] = tmp;
}
int ChangeDec(char x) 
{
	switch (x) {
	case'A':	return 10;
	case'B':	return 11;
	case'C':	return 12;
	case'D':	return 13;
	case'E':	return 14;
	case'F':	return 15;
	default:	return x-48;
	}
}
void Init() {
	for (int i = 0; i < MAX_SUM; i++) {
		Temp[i] = 0;
		sum[i] = 0;
	}
}
int Pow(int x, int y) {
	int result=1;
	for (int i = 0; i < y; i++)
		result *= x;
	return result;
}
void MergeSort(int data[], int start, int end) {
	int i = start;
	int k = start;
	int mid = (start + end) / 2;
	int j = mid + 1;
	if (start >= end) return;			//쪼갤수 없음, 되돌아가기
	MergeSort(data, start, mid);		//start ~mid 분할
	MergeSort(data, mid + 1, end);		//mid+1 ~ end 분할
	//시작점이 병합 절반 보다 작거나 같고, 중간지점+1이 마지막 보다 작거나 같으면 병합
	while ((i <= mid) && (j <= end))		
	{
		if (data[i] > data[j]) Temp[k++] = data[i++];
		else Temp[k++] = data[j++];
	}
	while (i <= mid) Temp[k++] = data[i++];//나머지 병합
	while (j <= end) Temp[k++] = data[j++];//나머지 병합
	for (i = start; i <= end; i++)data[i] = Temp[i];//복사
}
void CalDec(int t,int n) //16진수 10진수의 합으로 계산
{	
	int d = n / 4;		//한 변의 숫자 개수
	int val;
	for (int i = 0; i < MAX_SUM; i++)
	{
		if (i > 0 && i % 4 == 0)Rotate(t, n);
		for (int j = (i % 4)*d; j < (i % 4 + 1)*d; j++) {
			//cout << S[t][j];
			val = ChangeDec(S[t][j]);
			sum[i] += val *Pow(16, d - ((j%d) + 1));
		}
		//cout << " ";
		//cout << sum[i] << " ";
		
	}
	//cout << endl;
}
void Process(int t,int pN, int pK) {
	int c = 1;
	Init();
	CalDec(t, pN);
	MergeSort(sum, 0, pN - 1);
	/*for (int i = 1; i < MAX_SUM; i++) {
		if (sum[i - 1] == sum[i])sum[i - 1] = 0;
		Temp[i] = 0;
	}
	MergeSort(sum, 0, pN - 1);*/
	//for (int i = 0; i < MAX_SUM; i++)
	//	cout << sum[i] << " ";
	//cout << endl;
	for (int i = 1; i < MAX_SUM; i++) {
		if (sum[i - 1] != sum[i])c++;
		if (c == K[t]) cout << "#" << t + 1 << " " <<sum[i];
		
	}
	/*cout << "#" << t + 1 << " " << sum[pK-1];*/
	cout << endl;
}
void Solution() {
	Input();
	for (int i = 0; i < T; i++) {
		Process(i, N[i], K[i]);		
	}
}
int main() {
	Solution();
	return 0;
}
#include<iostream>
#include<fstream>
using namespace std;

/*����
�� ���� ������ ���� 16���� ����(0~F)�� ���� �ִ� �������ڰ� �ִ�.

���� ������ �Ѳ��� �ð�������� ���� �� �ְ�, �� �� ���� ������ ���ڰ� �ð�������� �� ĭ�� ȸ���Ѵ�.



�� ������ ������ ������ ���ڰ� �ְ�, �ð���� ������ ���� �ڸ� ���ڿ� �ش��ϸ� �ϳ��� ���� ��Ÿ����.

���� ��� [Fig.1]�� ���� 1A3, B54, 8F9, D66�̰�, [Fig.2]�� ���� 61A, 3B5, 48F, 9D6�̴�.

�������ڿ��� �ڹ��谡 �ɷ��ִµ�, �� �ڹ����� ��й�ȣ�� ���� ���ڿ� ���� ���ڷ� ���� �� �ִ� ��� �� ��, 

K��°�� ū ���� 10�� ���� ���� ���̴�.

N���� ���ڰ� �Է����� �־����� ��, ���������� ��� ��ȣ�� ����ϴ� ���α׷��� ������.

(���� �ٸ� ȸ�� Ƚ������ ������ ���� �ߺ����� ������ �� �ִ�. ũ�� ������ �� �� ���� ���� �ߺ����� ���� �ʵ��� �����Ѵ�.)



[���� ����]


N�� 4�� ����̰�, 8�̻� 28������ �����̴�. (8 �� N �� 28)
N���� ���ڴ� ���� 0�̻� F���Ϸ� �־�����. (A~F�� ���ĺ� �빮�ڷθ� �־�����.)
K�� ���� ������ ���� �������� ũ�� �־����� �ʴ´�.

*/
const int MAX_N = 29;
const int MAX_T = 100;
const int MAX_SUM = 3*28;
int T;
int N[MAX_T], K[MAX_T];			//T : �׽�Ʈ ���̽� ����, 8 <= N <= 28 , K <= N
char S[MAX_T][MAX_N];			//�׽�Ʈ ���̽� ��ŭ ���ڿ� ����
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
	if (start >= end) return;			//�ɰ��� ����, �ǵ��ư���
	MergeSort(data, start, mid);		//start ~mid ����
	MergeSort(data, mid + 1, end);		//mid+1 ~ end ����
	//�������� ���� ���� ���� �۰ų� ����, �߰�����+1�� ������ ���� �۰ų� ������ ����
	while ((i <= mid) && (j <= end))		
	{
		if (data[i] > data[j]) Temp[k++] = data[i++];
		else Temp[k++] = data[j++];
	}
	while (i <= mid) Temp[k++] = data[i++];//������ ����
	while (j <= end) Temp[k++] = data[j++];//������ ����
	for (i = start; i <= end; i++)data[i] = Temp[i];//����
}
void CalDec(int t,int n) //16���� 10������ ������ ���
{	
	int d = n / 4;		//�� ���� ���� ����
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
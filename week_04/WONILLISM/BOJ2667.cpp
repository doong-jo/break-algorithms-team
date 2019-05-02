#include<iostream>

using namespace std;

const int MAX_N = 25;
const int MAX_Q = 625;

int N, area;
int dx[5] = { 0,1,0,-1,0 };
int dy[5] = { 0,0,1,0,-1 };
int Map[MAX_N + 2][MAX_N + 2];
bool visitCheck[MAX_N + 2][MAX_N + 2];

int NumBuilding[MAX_Q];

typedef struct Position{
	int x, y;
}Pos;

Pos Q[MAX_Q]; int f, r;
void push(int x, int y) {
	if (r==MAX_Q) r = 0;
	Q[r].x = x;
	Q[r].y = y;
	r++;
}
Pos pop() {
	Pos d;
	if (f==MAX_Q) f = 0;
	d.x = Q[f].x;
	d.y = Q[f].y;
	f++;
	return d;
}

void Input() {
	//FILE *In = fopen("NumberingComplex(1).txt", "r");
	//fscanf(In, "%d", &N);
	//for (int i = 1; i <= N; i++){
	//	for (int j = 1; j <= N; j++){
	//		fscanf(In, "%1d", &Map[i][j]);
	//		//cout << Map[i][j];
	//	}
	//	//cout << endl;
	//}
	//fclose(In);
	cin >> N;
	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= N; j++) {
			//cin >> Map[i][j];
			scanf("%1d", &Map[i][j]);
		}
	}
}
int BFS(int x, int y) {
	Pos cur;
	cur.x = x;
	cur.y = y;
	int cnt = 1;
	push(cur.x, cur.y);
	visitCheck[cur.x][cur.y] = true;

	int nx, ny;
	while (f != r) {
		cur = pop();

		for (int i = 1; i <= 5; i++) {
			nx = cur.x + dx[i];
			ny = cur.y + dy[i];

			if (!visitCheck[nx][ny]) {
				if (Map[nx][ny] == 1) {
					cnt+=1;
					push(nx, ny);
					visitCheck[nx][ny] = true;
				}
			}
		}
	}
	return cnt;
}

void MergeSort(int data[], int start, int end) {
	int tmp[MAX_Q];
	int i = start;
	int k = start;
	int m = (start + end) / 2;
	int j = m + 1;
	if (start >= end) return; //쪼갤수 없음, 되돌아가기
	MergeSort(data, start, m); //분할
	MergeSort(data, m + 1, end); //분할
	while ((i <= m) && (j <= end)) { //병합
		if (data[i] < data[j]) tmp[k++] = data[i++];
		else tmp[k++] = data[j++];
	}
	while (i <= m) tmp[k++] = data[i++]; //나머지 병합
	while (j <= end)  tmp[k++] = data[j++]; //나머지 병합
	for (i = start; i <= end; i++) data[i] = tmp[i]; //복사
}
void Process() {
	area = 0;
	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= N; j++) {
			if(!visitCheck[i][j]&&Map[i][j] == 1)	NumBuilding[area++] = BFS(i, j);
		}
	}
	MergeSort(NumBuilding, 0, area-1);
	cout << area << endl;
	for (int i = 0; i < area; i++)
		cout << NumBuilding[i] << endl;

}
void Solution() {
	Input();
	Process();
}
int main() {
	Solution();
	return 0;
}
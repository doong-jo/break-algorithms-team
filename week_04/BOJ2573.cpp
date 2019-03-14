#include<iostream>

using namespace std;

const int MAX_Q = 1 << 17;  
const int MAX_N_M = 300;    //3<=N,M<=300
static int N, M, Year, highst;
static int dy[5] = { 0,0,1,0,-1 };
static int dx[5] = { 0,1,0,-1,0 };

int Map[MAX_N_M + 2][MAX_N_M + 2];  //each element 0~10
int waterCheck[MAX_N_M + 2][MAX_N_M + 2];
bool Check[MAX_N_M + 2][MAX_N_M + 2];

typedef struct Position{
	int x;
	int y;
}Pos;

Pos meltQ[MAX_Q]; int mf, mr;
void m_push(int x, int y) {
	if (mr&MAX_Q) mr = 0;
	meltQ[mr].x = x;
	meltQ[mr].y = y;
	mr++;
}
Pos m_pop() {
	Pos d;
	if (mf&MAX_Q) mf = 0;
	d.x = meltQ[mf].x;
	d.y = meltQ[mf].y;
	
	mf++;
	return d;
}
void Input() {
	FILE *In = fopen("IceBerg(2).txt", "r");
	fscanf(In, "%d %d", &N, &M);
	for (int i = 1; i <= N; i++)
	{
		for (int j = 1; j <= M; j++)
		{
			fscanf(In, "%d", &Map[i][j]);
		}
	}
	fclose(In);
	
	//cin >> N >> M;			//N is column, M is row

	//for (int i = 1; i <= N; i++) {
	//	for (int j = 1; j <= M; j++) {
	//		cin >> Map[i][j];
	//	}
	//}
}
//BFS를 돌면서 4방면 중 몇 방면이 물인지 체크해서 waterCheck에 넣어준다
void IcebergBFS(int x, int y) {
	Pos cur;
	cur.x = x;
	cur.y = y;
	
	m_push(cur.x, cur.y);
	Check[cur.x][cur.y] = true;
	
	int nx, ny;
	while (mf != mr) {
		cur = m_pop();
		for (int i = 1; i <= 4; i++) {
			nx = cur.x + dx[i];
			ny = cur.y + dy[i];
			
			if (!Check[nx][ny]) {
				if (Map[nx][ny] != 0) {
					m_push(nx, ny);
					Check[nx][ny] = true;
				}
				if (Map[nx][ny] == 0) {
					waterCheck[cur.x][cur.y] += 1;
				}
			}
		}
	}
}
//방문체크 초기화하며 빙산을 녹인다
void init_Iceberg() {
	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= M; j++) {
			Check[i][j] = false;
			if (Map[i][j] != 0) {
				Map[i][j] -= waterCheck[i][j];
				waterCheck[i][j] = 0;
				if (Map[i][j] < 0) Map[i][j] = 0;
			}
		}
	}
}
//BFS와 빙산을 녹이는것을 반복
void Process() {
	while (1) {
		int cnt = 0;
		init_Iceberg();
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				if (!Check[i][j] && Map[i][j] != 0) {
					cnt++;
					IcebergBFS(i, j);
				}
			}
		}
		if (cnt >= 2) {
			cout << Year << endl;
			break;
		}
		if (cnt == 0) {
			cout << "0" << endl;
			break;
		}
		Year++;

	}
}
void Solution() {
	Input();
	Process();
}
int main() {
	Solution();
	return 0;
}

#include<iostream>

using namespace std;

const int MAX_N_M = 50;
const int MAX_Q = 1 << 8;
int N, M;
char Map[MAX_N_M + 1][MAX_N_M + 1];
bool Check[MAX_N_M + 1][MAX_N_M + 1];
bool StartCheck[MAX_N_M + 1][MAX_N_M + 1];
int cStep[MAX_N_M + 1][MAX_N_M + 1];
int dx[4] = { 0,1,0,-1 };
int dy[4] = { 1,0,-1,0 };

typedef struct Position {
	int x, y, step;
}Pos;

Pos Q[MAX_Q]; int f, r;
void push(int x, int y, int s) {
	if (r&MAX_Q) r = 0;
	Q[r].x = x;
	Q[r].y = y;
	Q[r].step = s;
	r++;
}
Pos pop() {
	Pos d;
	if (f&MAX_Q) f = 0;
	d.x = Q[f].x;
	d.y = Q[f].y;
	d.step = Q[f].step;
	f++;
	return d;
}
void Input() {
	/*FILE *In = fopen("TreasureIsland(1).txt", "r");
	fscanf(In, "%d %d", &N, &M);
	for (int i = 0; i < N; i++){
		for (int j = 0; j < M; j++){
			fscanf(In, "%c", &Map[i][j]);
			cout << Map[i][j];
		}
		cout << endl;
	}
	fclose(In);*/

	cin >> N >> M;
	for (int i = 0; i < N; i++) {
		cin >> Map[i];
	}

}

int BFS(int x, int y) {
	Pos cur;
	cur.x = x;
	cur.y = y;
	cur.step = 0;

	push(cur.x, cur.y, cur.step);
	Check[cur.x][cur.y] = true;

	int nx, ny, nstep;
	while (f != r) {
		cur = pop();
		for (int i = 0; i < 4; i++) {
			nx = cur.x + dx[i];
			ny = cur.y + dy[i];
			if (nx >= 0 && nx < N&&ny >= 0 && ny < M) {
				if (!Check[nx][ny] && Map[nx][ny] == 'L') {
					nstep = cur.step + 1;
					push(nx, ny, nstep);
					Check[nx][ny] = true;
					cStep[nx][ny] = nstep;
				}
			}
		}
	}
	return cur.step;
}
void initVisitCheck() {
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			Check[i][j] = false;
		}
	}
}
void Process() {
	int dis = 0;
	int tmp = 0;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			if (!StartCheck[i][j] && !Check[i][j] && Map[i][j] == 'L') {
				dis = BFS(i, j);
				StartCheck[i][j] = true;
				initVisitCheck();

			}

			if (tmp < dis) tmp = dis;
		}
	}
	cout << tmp;
}
void Solution() {
	Input();
	Process();
}
int main() {
	Solution();
	return 0;
}
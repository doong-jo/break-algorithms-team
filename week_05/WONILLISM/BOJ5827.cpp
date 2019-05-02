#include<iostream>
using namespace std;

const int MAX_N_M = 500;
const int MAX_Q = 250000;
int N, M;
bool FIND = false;

char Map[MAX_N_M][MAX_N_M];
bool Check[MAX_N_M][MAX_N_M];
int c_step[MAX_N_M][MAX_N_M];
int dx[3] = { 1,-1,0 };

typedef struct Position {
	int x, y;
	int step;
	int dir;
}Pos;

void MergeSort(Pos data[], int start, int end) {
	Pos tmp[MAX_Q];
	int i = start;
	int k = start;
	int m = (start + end) / 2;
	int j = m + 1;
	if (start >= end) return; //쪼갤수 없음, 되돌아가기
	MergeSort(data, start, m); //분할
	MergeSort(data, m + 1, end); //분할
	while ((i <= m) && (j <= end)) { //병합
		if (data[i].step > data[j].step) tmp[k++] = data[i++];
		else tmp[k++] = data[j++];
	}
	while (i <= m) tmp[k++] = data[i++]; //나머지 병합
	while (j <= end)  tmp[k++] = data[j++]; //나머지 병합
	for (i = start; i <= end; i++) data[i] = tmp[i]; //복사
}

Pos Q[MAX_Q]; int f, r;
void push(int x, int y, int s, int d) {
	if (r == MAX_Q) r = 0;
	Q[r].x = x;
	Q[r].y = y;
	Q[r].step = s;
	Q[r].dir = d;
	r++;

	if (r - f > 2) MergeSort(Q, f, r);
}
Pos pop() {
	Pos d;
	if (f == MAX_N_M) f = 0;
	d.x = Q[f].x;
	d.y = Q[f].y;
	d.step = Q[f].step;
	d.dir = Q[f].dir;
	f++;
	return d;
}

void Input() {
	cin >> N >> M;
	for (int i = 0; i < N; i++)
		cin >> Map[i];
}
Pos fall(Pos c) {
	Pos cur = c;
	while (1) {
		if (Map[cur.y + cur.dir][cur.x] == '#') break;
		if (Map[cur.y + cur.dir][cur.x] == 'D') {
			FIND = true;
			break;
		}
		if (cur.y + cur.dir < 0 || cur.y + cur.dir >= N)return { -1,-1 };
		cur.y += cur.dir;
	}
	return cur;
}
int BFS(int y, int x) {
	Pos cur;
	cur.x = x;
	cur.y = y;
	cur.step = 0;
	cur.dir = 1;

	cur = fall(cur);
	push(cur.x, cur.y, cur.step, cur.dir);
	Check[cur.y][cur.x] = true;

	if (cur.y<0 || cur.y>N) return -1;

	Pos next;

	while (f != r) {
		cur = pop();

		for (int i = 0; i < 3; i++) {
			next.x = cur.x + dx[i];
			next.y = cur.y;
			next.step = cur.step;
			next.dir = cur.dir;

			if (next.x >= 0 && next.x < M&&next.y >= 0 && next.y < N) {
				if (i < 2 && Map[next.y][next.x] != '#') {
					if (Map[next.y][next.x] == 'D') return next.step;
					next = fall(next);
					if (!Check[next.y][next.x]) {
						push(next.x, next.y, next.step, next.dir);
						Check[next.y][next.x] = true;
						c_step[next.y][next.x] = next.step;
					}
				}
				if (i == 2) {
					next.dir = -1 * cur.dir;
					next = fall(next);
					next.step = cur.step + 1;
					if (FIND) return next.step;
					if (!Check[next.y][next.x]) {
						push(next.x, next.y, next.step, next.dir);
						Check[next.y][next.x] = true;
						c_step[next.y][next.x] = next.step;
					}
				}
			}
		}
	}
	return -1;
}

void Process() {
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			if (Map[i][j] == 'C')
				cout << BFS(i, j);
		}
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
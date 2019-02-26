#include<iostream>

using namespace std;

/*
N×M크기의 배열로 표현되는 미로가 있다.

★□■■■■
■□■□■□
■□■□■■
■■■□■★

1	0	1	1	1	1
1	0	1	0	1	0
1	0	1	0	1	1
1	1	1	0	1	1
미로에서 1은 이동할 수 있는 칸을 나타내고, 0은 이동할 수 없는 칸을 나타낸다.
이러한 미로가 주어졌을 때, (1, 1)에서 출발하여 (N, M)의 위치로 이동할 때 지나야 하는 최소의 칸 수를 구하는 프로그램을 작성하시오.
한 칸에서 다른 칸으로 이동할 때, 서로 인접한 칸으로만 이동할 수 있다.

위의 예에서는 15칸을 지나야 (N, M)의 위치로 이동할 수 있다. 칸을 셀 때에는 시작 위치와 도착 위치도 포함한다.
*/
//입력: 첫째 줄에 두 정수(2~100)이 주어짐
//다음 N개의 줄에는 M개의 정수로 미로가 주어진다.

//출력:  첫째 줄에 지나야 하는 칸 수를 출력한다. 항상 도착위치로 이동할 수 있는 경우만 입력으로 주어진다.

/******Queue*******/
typedef struct Pos {
	int x, y;
	int step;
}Pos;
const int MAX_QUEUE_SIZE = 128;
Pos queue[MAX_QUEUE_SIZE];
int front, rear;

void push(int y, int x, int step) {
	if (rear&MAX_QUEUE_SIZE) rear = 0;
	queue[rear].y = y;
	queue[rear].x = x;
	queue[rear].step = step;
	rear++;
}
Pos pop() {
	Pos d;
	if (front&MAX_QUEUE_SIZE) front = 0;
	d.y = queue[front].y;
	d.x = queue[front].x;
	d.step = queue[front].step;
	front++;
	return d;
}
/******Queue*******/
const int MAX = 101;
int N, M;

//int maze[MAX][MAX];
char maze[MAX][MAX];
//int check[MAX][MAX];
bool check[MAX][MAX];
int c_step[MAX][MAX];
int dx[4] = { 0,-1,0,1 };
int dy[4] = { -1,0,1,0 };



int search(int y, int x) {
	Pos cur;
	cur.y = y;
	cur.x = x;
	cur.step = 1;
	push(cur.y, cur.x, cur.step);

	check[y][x] = 1;

	while (front != rear) {
		/*cur.y = queue[front].y;
		cur.x = queue[front].x;
		cur.step = queue[front].step;
		int z = cur.step;*/
		cur = pop();
		//int z = cur.step;

		if (cur.y == N - 1 && cur.x == M - 1) return cur.step;


		for (int i = 0; i < 4; i++) {
			int ny = cur.y + dy[i];
			int nx = cur.x + dx[i];
			int nstep = cur.step;

			if (ny >= 0 && nx >= 0 && ny < N&&nx < M) {
				if (!check[ny][nx]) {
					if (maze[ny][nx] == '1') {

						nstep = cur.step + 1;
						push(ny, nx, nstep);
						check[ny][nx] = 1;
						c_step[cur.y][cur.x] = nstep;
					}
				}
			}
			/*if (ny >= N || nx >= M || ny < 0 || nx < 0)
				continue;
			if (check[ny][nx])
				continue;
			if (maze[ny][nx] != '1')
				continue;
*/

		}

	}
}


int main() {
	cin >> N >> M;
	/*for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			scanf("%1d", &maze[i][j]);
		}
	}*/
	for (int i = 0; i < N; i++) {
		cin >> maze[i];
	}


	cout << search(0, 0);
	return 0;
}

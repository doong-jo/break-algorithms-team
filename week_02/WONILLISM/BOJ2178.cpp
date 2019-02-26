#include<iostream>

using namespace std;

/*
N��Mũ���� �迭�� ǥ���Ǵ� �̷ΰ� �ִ�.

�ڡ�����
�������
�������
�������

1	0	1	1	1	1
1	0	1	0	1	0
1	0	1	0	1	1
1	1	1	0	1	1
�̷ο��� 1�� �̵��� �� �ִ� ĭ�� ��Ÿ����, 0�� �̵��� �� ���� ĭ�� ��Ÿ����.
�̷��� �̷ΰ� �־����� ��, (1, 1)���� ����Ͽ� (N, M)�� ��ġ�� �̵��� �� ������ �ϴ� �ּ��� ĭ ���� ���ϴ� ���α׷��� �ۼ��Ͻÿ�.
�� ĭ���� �ٸ� ĭ���� �̵��� ��, ���� ������ ĭ���θ� �̵��� �� �ִ�.

���� �������� 15ĭ�� ������ (N, M)�� ��ġ�� �̵��� �� �ִ�. ĭ�� �� ������ ���� ��ġ�� ���� ��ġ�� �����Ѵ�.
*/
//�Է�: ù° �ٿ� �� ����(2~100)�� �־���
//���� N���� �ٿ��� M���� ������ �̷ΰ� �־�����.

//���:  ù° �ٿ� ������ �ϴ� ĭ ���� ����Ѵ�. �׻� ������ġ�� �̵��� �� �ִ� ��츸 �Է����� �־�����.

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

	for (int i = 0; i < N; i++) {
		cin >> maze[i];
	}


	cout << search(0, 0);
	return 0;
}

#include<fstream>

#include<iostream>

using namespace std;

const int MAX_SIZE = 1001;
int N, M;
int day;
int tomato[MAX_SIZE][MAX_SIZE];
bool check[MAX_SIZE][MAX_SIZE];
int c_step[MAX_SIZE][MAX_SIZE];
int dir_y[4] = { -1,0,1,0 };
int dir_x[4] = { 0,-1,0,1 };

typedef struct Pos {
	int x;
	int y;
	int step;
}Pos;

/*---------------QUEUE---------------*/
const int MAX_QUEUE_SIZE = 1024;
Pos queue[MAX_QUEUE_SIZE];
int front = 0;
int rear = 0;

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

/*---------------QUEUE---------------*/

void search() {
	Pos cur;

	while (front != rear) {
		
		cur = pop();
		day = cur.step;
		c_step[cur.y][cur.x] = 1;


		for (int i = 0; i < 4; i++) {
			int ny = cur.y + dir_y[i];
			int nx = cur.x + dir_x[i];
			
			if (ny >= 0 && nx >= 0 && ny < M&&nx < N) {
				if (c_step[ny][nx]<=cur.step) {
					if (tomato[ny][nx] == 0) {
						//check[ny][nx] = true;
						c_step[ny][nx] = cur.step + 1;
						tomato[ny][nx] = 1;
						push(ny, nx, cur.step + 1);

					}
				}
			}
		}
	}
}

void testcase() {
	ifstream f;
	f.open("tomato_testcase(1).txt");
	for (int i = 1; i <= M; i++) {
		for (int j = 1; j <= N; j++) {
			f >> tomato[i][j];
			//cout << tomato[i][j];
		}
		//cout << endl;
	}
}

int main() {
	cin >> N >> M;
	//testcase();
	for (int i = 0; i < M; i++) {
		for (int j = 0; j < N; j++) {
			cin >> tomato[i][j];
		}
	}

	for (int i = 0; i < M; i++) {
		for (int j = 0; j < N; j++) {
			if (tomato[i][j] == 1) {
				push(i, j, 0);
			}
		}
	}

	search();

	for (int i = 0; i < M; i++) {
		for (int j = 0; j < N; j++) {
			if (tomato[i][j] == 0) {
				cout << "-1";
				return 0;
			}
		}
	}
	cout << day;

	return 0;
}
#include<fstream>
#include<iostream>

using namespace std;

typedef struct Pos {
	int y, x;
};
/*-------------QUEUE----------------*/
const int MAX_QUEUE_SIZE = 128;

Pos queue[MAX_QUEUE_SIZE];
int front, rear = 0;

void push(int y, int x) {
	if (rear&MAX_QUEUE_SIZE) rear = 0;
	queue[rear].y = y;
	queue[rear].x = x;
	rear++;
}

Pos pop() {
	Pos d;
	if (front&MAX_QUEUE_SIZE) front = 0;
	d.y = queue[front].y;
	d.x = queue[front].x;
	front++;
	return d;
}
/*-------------QUEUE----------------*/
const int MAX_SIZE = 1501;
int R, C;
char map[MAX_SIZE][MAX_SIZE];
bool check[MAX_SIZE][MAX_SIZE];
int diry[4] = { 1,0,-1,0 };
int dirx[4] = { 0,1,0,-1 };

int solve(int y, int x) {
	Pos cur;
	cur.y = y;
	cur.x = x;
	int num_wolf = 0;
	int num_sheep = 0;

	push(cur.y, cur.x);
	check[y][x] = true;

	int nx, ny;
	while (front != rear) {
		cur = pop();

		if (map[cur.y][cur.x] == 'o') num_sheep++;
		if (map[cur.y][cur.x] == 'v') num_wolf++;

		for (int i = 0; i < 4; i++) {
			ny = cur.y + diry[i];
			nx = cur.x + dirx[i];

			if (ny >= 0 && nx >= 0 && ny < R&&nx < C) {
				if (!check[ny][nx] && map[ny][nx] != '#') {
					push(ny, nx);
					check[ny][nx] = true;
				}
			}
		}
	}

	if (num_sheep > num_wolf) return num_sheep;
	else if (num_wolf >= num_sheep) return -1 * num_wolf;

}
void testcase() {
	ifstream f;
	f.open("testcase(1).txt");
	for (int i = 0; i < R; i++) {
		f >> map[i];
	}
	/*for (int i = 0; i < R; i++) {
		for (int j = 0; j < C; j++) {
			cout << map[i][j];
		}
		cout << endl;
	}*/
}
int main() {
	int total_sheep = 0;
	int total_wolf = 0;

	cin >> R >> C;

	testcase();
	for (int i = 0; i < R; i++) {
		for (int j = 0; j < C; j++) {
			if (!check[i][j]&&map[i][j] != '#') {
				int result = solve(i, j);
				
				if (result > 0) {
					total_sheep += result;
				}
				else {
					total_wolf += result * -1;
				}
			}
		}
	}


	cout << total_sheep << " " << total_wolf;
	return 0;
}
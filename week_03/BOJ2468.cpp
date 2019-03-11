#include<iostream>
using namespace std;

const int MAX_N = 500;
const int MAX_Q = 250000;

static int N;
static int Map[MAX_N + 2][MAX_N + 2];
static int highst, Max;
static int dx[5] = { 0, 0, 1, 0, -1 };
static int dy[5] = { 0, 1, 0, -1, 0 };
static bool Check[MAX_N + 2][MAX_N + 2];

typedef struct Position {
	int x;
	int y;
}Pos;

static Pos Q[MAX_Q]; int f, r;

void push(int x, int y) {
	if (r == MAX_Q) r = 0;
	Q[r].x = x;
	Q[r].y = y;
	r++;
}

Pos pop() {
	Pos d;
	if (f == MAX_Q) f = 0;
	d.x = Q[f].x;
	d.y = Q[f].y;
	f++;
	return d;
}
void Input()
{
	//FILE *In = fopen("SafeArea(2).txt", "r");
	//fscanf(In, "%d", &N);
	//for (int i = 1; i <= N; i++)
	//{
	//	for (int j = 1; j <= N; j++)
	//	{
	//		fscanf(In, "%d", &Map[i][j]);
	//		if (Map[i][j] > highst)			
	//			highst = Map[i][j];
	//	}
	//}
	//fclose(In);
	cin >> N;
	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= N; j++) {
			cin >> Map[i][j];
			if (Map[i][j] > highst)	//find highst area
				highst = Map[i][j];
		}
	}
}
void BFS(int Limit, int y, int x)
{
	Pos cur;
	cur.x = x;
	cur.y = y;
	push(cur.x, cur.y);

	int nx, ny;
	while (f != r)
	{
		cur = pop();
		for (int i = 1; i <= 4; i++)
		{
			ny = cur.y + dy[i];
			nx = cur.x + dx[i];
			if (!Check[ny][nx])
			{
				if (Map[ny][nx] >= Limit)
				{
					push(nx, ny);
					Check[ny][nx] = true;
				}
			}
		}
	}
}
void Process(void)
{
	for (int Loop = 1; Loop <= highst; Loop++)
	{
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				Check[i][j] = false;
			}
		}
		int t = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (!Check[i][j] && Map[i][j] >= Loop)
					BFS(Loop, i, j), t++;
			}
		}
		if (t > Max) Max = t;
	}
}
void Solution() {
	Input();
	Process();
	cout << Max;
}
int main()
{
	Solution();

	return 0;
}
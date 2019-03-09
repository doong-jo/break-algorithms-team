#include<iostream>
#include<queue>

const int MAX = 1501;
using namespace std;

int R, C;
bool Find;
char MAP[MAX][MAX];
bool Visit[MAX][MAX];

int dx[] = { 0, 0, 1, -1 };
int dy[] = { 1, -1, 0, 0 };

typedef struct Position {
	int x;
	int y;
}Pos;

/*------------Queue-----------------*/
//const int MAX_Q = 1 << 20;
const int MAX_Q = 2250001;

Pos cur_swan_Q[MAX_Q]; int csf, csr;
void cur_swan_push(int x, int y) {
	if (csr == MAX_Q) csr = 0;
	cur_swan_Q[csr].y = y;
	cur_swan_Q[csr].x = x;
	csr++;
}
Pos cur_swan_pop() {
	Pos d;
	if (csf == MAX_Q) csf = 0;
	d.y = cur_swan_Q[csf].y;
	d.x = cur_swan_Q[csf].x;
	csf++;
	return d;
}

Pos next_swan_Q[MAX_Q]; int nsf, nsr;
void next_swan_push(int x, int y) {
	if (nsr == MAX_Q) nsr = 0;
	next_swan_Q[nsr].y = y;
	next_swan_Q[nsr].x = x;
	nsr++;
}
Pos next_swan_pop() {
	Pos d;
	if (nsf == MAX_Q) nsf = 0;
	d.y = next_swan_Q[nsf].y;
	d.x = next_swan_Q[nsf].x;
	nsf++;
	return d;
}

Pos cur_water_Q[MAX_Q]; int cwf, cwr;
void cur_water_push(int x, int y) {
	if (cwr == MAX_Q) cwr = 0;
	cur_water_Q[cwr].y = y;
	cur_water_Q[cwr].x = x;
	cwr++;
}
Pos cur_water_pop() {
	Pos d;
	if (cwf == MAX_Q) cwf = 0;
	d.y = cur_water_Q[cwf].y;
	d.x = cur_water_Q[cwf].x;
	cwf++;
	return d;
}

Pos next_water_Q[MAX_Q]; int nwf, nwr;
void next_water_push(int x, int y) {
	if (nwr == MAX_Q) nwr = 0;
	next_water_Q[nwr].y = y;
	next_water_Q[nwr].x = x;
	nwr++;
}
Pos next_water_pop() {
	Pos d;
	if (nwf == MAX_Q) nwf = 0;
	d.y = next_water_Q[nwf].y;
	d.x = next_water_Q[nwf].x;
	nwf++;
	return d;
}
/*------------Queue-----------------*/
Pos swan_pos[2];

void Input()
{
	Find = false;
	cin >> R >> C;
	int c = 0;
	for (int i = 0; i < R; i++)
	{
		for (int j = 0; j < C; j++)
		{
			cin >> MAP[i][j];
			if (MAP[i][j] != 'X') cur_water_push(i, j);
			if (MAP[i][j] == 'L')
			{
				swan_pos[c].x = i;
				swan_pos[c].y = j;
			}
		}
	}
}

void Swan_BFS()
{
	int nx, ny;
	while (csf != csr && Find == false)
	{
		Pos cur = cur_swan_pop();

		for (int i = 0; i < 4; i++)
		{
			nx = cur.x + dx[i];
			ny = cur.y + dy[i];

			if (nx >= 0 && ny >= 0 && nx < R && ny < C)
			{
				if (Visit[nx][ny] == false)
				{
					if (MAP[nx][ny] == '.')
					{
						Visit[nx][ny] = true;
						cur_swan_push(nx, ny);
					}
					else if (MAP[nx][ny] == 'L')
					{
						Visit[nx][ny] = true;
						Find = true;
						break;
					}
					else if (MAP[nx][ny] == 'X')
					{
						Visit[nx][ny] = true;
						next_swan_push(nx, ny);
					}
				}
			}
		}
	}
}

void Water_BFS()
{
	int nx, ny;
	while (cwf != cwr)
	{
		Pos cur = cur_water_pop();

		for (int i = 0; i < 4; i++)
		{
			nx = cur.x + dx[i];
			ny = cur.y + dy[i];

			if (nx >= 0 && ny >= 0 && nx < R && ny < C)
			{
				if (MAP[nx][ny] == 'X')
				{
					MAP[nx][ny] = '.';
					next_water_push(nx, ny);
				}
			}
		}
	}
}

void Solution()
{
	int Day = 0;
	cur_swan_push(swan_pos[0].x, swan_pos[0].y);
	Visit[swan_pos[0].x][swan_pos[0].y] = true;

	Pos waterQ;
	Pos swanQ;
	while (Find == false)
	{
		Swan_BFS();

		if (Find == false)
		{
			Water_BFS();

			while (nwf != nwr) {
				waterQ = next_water_pop();
				cur_water_push(waterQ.x, waterQ.y);
			}
			while (nsf != nsr) {
				swanQ = next_swan_pop();
				cur_swan_push(swanQ.x,swanQ.y);
			}
			Day++;
		}
	}
	cout << Day << endl;
}

void Solve()
{
	Input();
	Solution();
}

int main(void)
{
	
	Solve();

	return 0;
}
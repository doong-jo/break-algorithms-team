#include<iostream>
#include<queue>

using namespace std;

const int MAX_QUEUE_SIZE = 16;

int que[MAX_QUEUE_SIZE];
int front, rear;

int N, M, V;
int matrix[1001][1001];
int visit[1001];//방문했는지 안했는지 check

void enqueue(int val) {
	if (rear & MAX_QUEUE_SIZE) {
		rear = 0;
	}
	que[rear++] = val;

}
int dequeue() {
	if (front & MAX_QUEUE_SIZE) {
		front = 0;
	}
	return que[front++];
}

void dfs(int start) {

	cout << start << " ";
	visit[start] = 1;	//방문한 정점을 1로 변경
	for (int i = 1; i <= N; i++) {
        //방문을 했는지 안했는지 체크
		if (visit[i] == 1 || matrix[start][i] == 0) {
			continue;
		}
		dfs(i);
	}
}

void bfs(int start) {
	//queue<int> q;
	//q.push(start);
	enqueue(start);

	visit[start] = 0;//방문한 노드를 0으로 변경
	while (rear != front) {
		start = que[front];
		cout << que[front] << " ";
		dequeue();
		/*start = q.front();
		cout << q.front() << " ";
		q.pop();*/
		for (int i = 1; i <= N; i++) {
			if (visit[i] == 0 || matrix[start][i] == 0) {
				continue;
			}
			q.push(i);
			enqueue(i);
			visit[i] = 0;
		}

	}

}



int main() {
	int x, y;

	cin >> N >> M >> V;

	for (int i = 0; i < M; i++) {
		cin >> x >> y;
		matrix[x][y] = 1;
		matrix[y][x] = 1;
	}
	dfs(V);
	cout << endl;
	bfs(V);

	return 0;
}
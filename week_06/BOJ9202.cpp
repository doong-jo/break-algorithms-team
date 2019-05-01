#include<iostream>
using namespace std;


const int MAX_APBT = 26;	//대문자 개수
const int MAX_LEN = 9;		//문자열 최대길이

char Word[MAX_LEN];
char Board[4][4];
bool visit[4][4];

int dx[8] = { 1,0,-1,0,1,1,-1,-1 };
int dy[8] = { 0,-1,0,1,1,-1,1,-1 };
int score[] = { 0,0,0,1,1,2,3,5,11 };

int maxLen;
int total;
char compare[MAX_LEN];
char longest[MAX_LEN];
int words;


class TrieNode {
public:
	int size;
	bool flag;
	bool find;
	TrieNode *child[MAX_APBT];

	TrieNode() : size(0), flag(false), find(false)
	{
		for (int i = 0; i < MAX_APBT; i++)
			child[i] = NULL;
	}
	~TrieNode() {
		for (int i = 0; i < MAX_APBT; i++)
			if (child[i])
				delete child[i];
	}
	void Insert(const char *key, int s) {
		int next = *key - 'A';
		size = s;
		if (!*key) {
			flag = true;
			return;
		}
		else {
			if (!child[next]) child[next] = new TrieNode();
			child[next]->Insert(key + 1, s + 1);
		}
	}
	void InitFind(int pos) {
		for (int i = 0; i < MAX_APBT; i++) {
			if (child[i]) {
				child[i]->find = false;
				child[i]->InitFind(pos + 1);
			}
		}
	}
	void Search(int y, int x, int s) {
		int next = Board[y][x] - 'A';

		if (!child[next]) {
			visit[y][x] = false;
			return;
		}

		visit[y][x] = true;
		compare[s] = Board[y][x];

		if (child[next]->flag) {
			if (!child[next]->find) {
				child[next]->find = true;
				if (maxLen < s + 1) {
					maxLen = s + 1;
					for (int i = 0; i < 9; i++)
						longest[i] = compare[i];
				}
				//AAAABCCC
				//AAAABBCC
				bool checkC = false;
				if (maxLen == s + 1) {
					for (int i = 0; i < MAX_LEN; i++) {
						if (longest[i] == compare[i])
							continue;
						if (longest[i] > compare[i])
							checkC = true; break;
					}
				}
				if (checkC)
				{
					for (int i = 0; i < 9; i++)
						longest[i] = compare[i];
				}
				
				words++;
				total += score[s + 1];
			}			
		}

		for (int i = 0; i < 8; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx >= 0 && nx < 4 && ny >= 0 && ny < 4) {
				if (!visit[ny][nx]) {
					child[next]->Search(ny, nx, s + 1);
				}
			}
		}
		visit[y][x] = false;
	}

};
void InitFind() {
	for (int i = 0; i < MAX_LEN; i++) {
		compare[i] = NULL;
	}
}
void InitAnswer() {
	total = 0;
	maxLen = 0;
	words = 0;
	for (int i = 0; i < MAX_LEN; i++) {
		longest[i] = NULL;
	}
}

void Process() {
	freopen("large_hand.in", "r", stdin);
	TrieNode *root = new TrieNode();

	int w, b;
	cin >> w;
	while (w--) {
		cin >> Word;
		root->Insert(Word, 0);
	}
	cin >> b;
	while (b--) {
		for (int i = 0; i < 4; i++) {
			cin >> Board[i];
		}
		InitAnswer();
		root->InitFind(0);
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				InitFind();
				root->Search(i, j, 0);
			}
		}
		cout << total << " " << longest << " " << words << endl;
	}
	delete root;
}
void Solution() {
	Process();
}
int main() {
	Solution();
	return 0;
}





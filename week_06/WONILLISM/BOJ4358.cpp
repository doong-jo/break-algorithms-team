#include<iostream>

using namespace std;

const int MAX_LEN = 30+2;
const int MAX_CHAR = 128;

char Trees[MAX_LEN + 2];
int allTrees;

class TrieNode {
public:
	bool flag;
	int tree;
	TrieNode *child[MAX_CHAR];
	
	TrieNode() : flag(false), tree(0) {
		for (int i = 0; i < MAX_CHAR; i++)
			child[i] = NULL;
	}
	~TrieNode() {
		for (int i = 0; i < MAX_CHAR; i++)
			if (child[i])
				delete child[i];
	}
	void Insert(const char *key) {
		if (!*key) {
			allTrees++;
			flag = true;
			tree++;
		}
		else {
			int pos = *key;
			if (!child[pos]) child[pos] = new TrieNode();
			child[pos]->Insert(key + 1);
		}
	}
	void ShowAll(int pos) {
		if (tree > 0) {
			Trees[pos] = NULL;
			printf("%s %.4f\n", Trees, (tree / (double)allTrees) * 100);
		}
		for (int i = 0; i < MAX_CHAR; i++) {
			if (child[i]) {
				Trees[pos] = i;
				child[i]->ShowAll(pos + 1);
			}
		}
	}
}; 

void Process() {
	TrieNode *root = new TrieNode();
	freopen("1(4358).in.txt", "r", stdin);
	
	while (cin.getline(Trees, MAX_LEN, '\n')) {
		root->Insert(Trees);
	}
	root->ShowAll(0);
	delete root;
}
int main() {
	Process();
	return 0;
}
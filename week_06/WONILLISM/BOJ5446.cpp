#include<iostream>

using namespace std;

const int MAX_LEN = 20;
const int MAX_CHAR = 128;

char files[MAX_LEN + 2];

int answer;

class TrieNode {
public:
	bool Exist;			//존제여부
	bool Del, Flag;	//지울 수 있음, 지울 수 없음
	TrieNode *child[MAX_CHAR];

	TrieNode() : Exist(false), Del(false), Flag(false){
		for (int i = 0; i < MAX_CHAR; i++)
			child[i] = NULL;
	}
	~TrieNode() {
		for (int i = 0; i < MAX_CHAR; i++)
			if(child[i])
				delete child[i];
	}
	void Insert(const char* key, bool Check) 
	{
		//만약 현재 노드가 가리키는 값이 NULL이면 Flag에 마지막부분임을 check
		//입력값들은 지워야하는 값들이므로 모두 Del = true
		//두 번째 입력시 지우지 말아야할 노드값들은 모두 Del=false
		//Flag역시 false로 둬서 앞서 넣어준 Flag가 변하지 않게함
		//만약 마지막부분이 false가 된다면 그 문자는 와일드카드를 이용해 지우기가능
		if (!*key){
			Del = Check;
			Flag = Check;
			return;
		}
		//만약 현재 노드가 가리키는 값이 NULL이 아니라면 
		//해당 문자가 없으면 새로이 동적할당 해준다
		//문자가 있으면 존제 여부를 true
		//지워도 되는 문자이므로 Del = true
		//문자열의 끝부분인 NULL값을 찾을때 까지 dfs
		else {
			int pos = *key;
			if (child[pos]==NULL) child[pos] = new TrieNode();
			Exist = true;
			Del = Check;
			child[pos]->Insert(key + 1, Check);
		}
	}
	//지우면 안되는 이름을 가진 파일들을 찾아
	void Search() 
	{
		//현재 노드의 Del이 참이라면 지워도 되므로 
		//와일드카드를 사용해 지운다 생각하고 탐색을 종료한다
		if (Del) {
			answer++; 
			return;
		}
		//문자열의 끝부분을 찾았으므로 완전한 문자가 있으니 지워도 됨
		else if (Flag) answer++;
		for (int i = 0; i < MAX_CHAR; i++) {
			if (child[i]) child[i]->Search();
		}
	}
};

void Process() {
	int T, N1, N2;
	cin >> T;
	while(T--) {
		cin >> N1;
		TrieNode *root = new TrieNode();
		//지워야 하는 파일의 문자열들의 노드에 모드 true를 걸어준다
		for (int i = 0; i < N1; i++) {
			cin >> files;
			root->Insert(files, true);	//del이 참일때 삭제
										//와일드카드 사용
		}
		cin >> N2;
		for (int j = 0; j < N2; j++) {
			cin >> files;
			root->Insert(files, false);	//del이 거짓이어도
										//문자열 마지막까지 탐색하여 참이면 삭제
		}
		answer = 0;
		root->Search();
		cout << answer << endl;
		delete root;			
	}
}
void Solution() {
	Process();
}
int main() {
	Solution();
	return 0;
}
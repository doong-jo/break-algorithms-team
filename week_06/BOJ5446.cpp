#include<iostream>

using namespace std;

const int MAX_LEN = 20;
const int MAX_CHAR = 128;

char files[MAX_LEN + 2];

int answer;

class TrieNode {
public:
	bool Exist;			//��������
	bool Del, Flag;	//���� �� ����, ���� �� ����
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
		//���� ���� ��尡 ����Ű�� ���� NULL�̸� Flag�� �������κ����� check
		//�Է°����� �������ϴ� �����̹Ƿ� ��� Del = true
		//�� ��° �Է½� ������ ���ƾ��� ��尪���� ��� Del=false
		//Flag���� false�� �ּ� �ռ� �־��� Flag�� ������ �ʰ���
		//���� �������κ��� false�� �ȴٸ� �� ���ڴ� ���ϵ�ī�带 �̿��� ����Ⱑ��
		if (!*key){
			Del = Check;
			Flag = Check;
			return;
		}
		//���� ���� ��尡 ����Ű�� ���� NULL�� �ƴ϶�� 
		//�ش� ���ڰ� ������ ������ �����Ҵ� ���ش�
		//���ڰ� ������ ���� ���θ� true
		//������ �Ǵ� �����̹Ƿ� Del = true
		//���ڿ��� ���κ��� NULL���� ã���� ���� dfs
		else {
			int pos = *key;
			if (child[pos]==NULL) child[pos] = new TrieNode();
			Exist = true;
			Del = Check;
			child[pos]->Insert(key + 1, Check);
		}
	}
	//����� �ȵǴ� �̸��� ���� ���ϵ��� ã��
	void Search() 
	{
		//���� ����� Del�� ���̶�� ������ �ǹǷ� 
		//���ϵ�ī�带 ����� ����� �����ϰ� Ž���� �����Ѵ�
		if (Del) {
			answer++; 
			return;
		}
		//���ڿ��� ���κ��� ã�����Ƿ� ������ ���ڰ� ������ ������ ��
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
		//������ �ϴ� ������ ���ڿ����� ��忡 ��� true�� �ɾ��ش�
		for (int i = 0; i < N1; i++) {
			cin >> files;
			root->Insert(files, true);	//del�� ���϶� ����
										//���ϵ�ī�� ���
		}
		cin >> N2;
		for (int j = 0; j < N2; j++) {
			cin >> files;
			root->Insert(files, false);	//del�� �����̾
										//���ڿ� ���������� Ž���Ͽ� ���̸� ����
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
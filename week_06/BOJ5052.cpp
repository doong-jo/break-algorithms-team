#include<iostream>

using namespace std;
/*
����
��ȭ��ȣ ����� �־�����.
�̶�, �� ����� �ϰ����� �ִ��� ��������
���ϴ� ���α׷��� �ۼ��Ͻÿ�.

��ȭ��ȣ ����� �ϰ����� �����Ϸ���,
�� ��ȣ�� �ٸ� ��ȣ�� ���ξ��� ��찡 ����� �Ѵ�.

���� ���, ��ȭ��ȣ ����� �Ʒ��� ���� ��츦 �����غ���

�����ȭ: 911
���: 97 625 999
����: 91 12 54 26
�� ��쿡 �����̿��� ��ȭ�� �� �� �ִ� ����� ����.
��ȭ�⸦ ��� ������ ��ȣ�� ó�� �� �ڸ��� ������ ����
�ٷ� �����ȭ�� �ɸ��� �����̴�.
����, �� ����� �ϰ����� ���� ����̴�.

�Է�
ù° �ٿ� �׽�Ʈ ���̽��� ���� t�� �־�����. (1 �� t �� 50)
�� �׽�Ʈ ���̽��� ù° �ٿ��� ��ȭ��ȣ�� �� n�� �־�����. (1 �� n �� 10000)
���� n���� �ٿ��� ��Ͽ� ���ԵǾ� �ִ� ��ȭ��ȣ�� �ϳ��� �־�����.
��ȭ��ȣ�� ���̴� ���� 10�ڸ��̸�,
��Ͽ� �ִ� �� ��ȭ��ȣ�� ���� ���� ����.

���
�� �׽�Ʈ ���̽��� ���ؼ�, �ϰ��� �ִ� ����� ��쿡�� YES, �ƴ� ��쿡�� NO�� ����Ѵ�.

*/


const int MAX_LEN = 10;	//��ȭ��ȣ�� �ִ� ����
const int MAX_N = 10000;//�ִ� ��ȭ��ȣ�� ��
int T;
int N;
char phone[MAX_N][MAX_LEN + 1];


class TrieNode
{
public:
	bool flag;
	TrieNode *child[MAX_LEN];

	TrieNode() : flag(false)		//������ �ʱ�ȭ
	{
		for (int i = 0; i < MAX_LEN; i++)
			child[i] = NULL;		//��� child���� NULL���� �ʱ�ȭ
	}
	~TrieNode() {
		for (int i = 0; i < MAX_LEN; i++)
			if (child[i])		//child�� 0�� �ƴ϶�� ����
				delete child[i];
	}
	void Insert(const char* key) //���ڿ� ����
	{									
		if (*key == NULL)		//���� Ű���� NULL�̶�� , ���ڿ��� �����°��
		{
			flag = true;	//������ ��ġ ǥ��
		}
		else {
			int next = *key - '0';		//key���� ��ȭ��ȣ �����̱� ������ 
										//key ������ 0�� ���� ������ ��带 ã�� �� �ִ�
			if (child[next] == NULL)		//key���� �ٷ� null�̶�� Ž���� ó��
				child[next] = new TrieNode();	//���ο� Ʈ���� ��� �����Ҵ�
			child[next]->Insert(key + 1);		//���� key���� ������ ���� dfs			
		}
	}
	bool Search(const char* key)
	{
		int next = *key - '0';
		if (*key == NULL) return false;	//key���� null�̶�� ã�� ����
		if (this->flag) return true;			//�ش� Ʈ���̳���� flag�� true��� ã�� ���ڿ��� ����
		return child[next]->Search(key + 1);	//���� ���� Ž�� dfs
	}
};

void Process() {
	cin >> N;
	for (int i = 0; i < N; i++)
		cin >> phone[i];
	TrieNode *Root = new TrieNode;
	for (int i = 0; i < N; i++)
		Root->Insert(phone[i]);	//	��� ��ȭ��ȣ ����
	for (int i = 0; i < N; i++)
		if (Root->Search(phone[i])) {
			cout << "NO" << endl;	//�ϰ����� ������ NO
			return;
		}
	cout << "YES" << endl;
}
void Solution() {
	cin >> T;
	for (int tc = 1; tc <= T; tc++) {
		Process();
	}
}
int main() {
	Solution();
	return 0;
}
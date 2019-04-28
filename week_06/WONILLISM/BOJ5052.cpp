#include<iostream>

using namespace std;
/*
문제
전화번호 목록이 주어진다.
이때, 이 목록이 일관성이 있는지 없는지를
구하는 프로그램을 작성하시오.

전화번호 목록이 일관성을 유지하려면,
한 번호가 다른 번호의 접두어인 경우가 없어야 한다.

예를 들어, 전화번호 목록이 아래와 같은 경우를 생각해보자

긴급전화: 911
상근: 97 625 999
선영: 91 12 54 26
이 경우에 선영이에게 전화를 걸 수 있는 방법이 없다.
전화기를 들고 선영이 번호의 처음 세 자리를 누르는 순간
바로 긴급전화가 걸리기 때문이다.
따라서, 이 목록은 일관성이 없는 목록이다.

입력
첫째 줄에 테스트 케이스의 개수 t가 주어진다. (1 ≤ t ≤ 50)
각 테스트 케이스의 첫째 줄에는 전화번호의 수 n이 주어진다. (1 ≤ n ≤ 10000)
다음 n개의 줄에는 목록에 포함되어 있는 전화번호가 하나씩 주어진다.
전화번호의 길이는 길어야 10자리이며,
목록에 있는 두 전화번호가 같은 경우는 없다.

출력
각 테스트 케이스에 대해서, 일관성 있는 목록인 경우에는 YES, 아닌 경우에는 NO를 출력한다.

*/


const int MAX_LEN = 10;	//전화번호의 최대 길이
const int MAX_N = 10000;//최대 전화번호의 수
int T;
int N;
char phone[MAX_N][MAX_LEN + 1];


class TrieNode
{
public:
	bool flag;
	TrieNode *child[MAX_LEN];

	TrieNode() : flag(false)		//생성자 초기화
	{
		for (int i = 0; i < MAX_LEN; i++)
			child[i] = NULL;		//모든 child값을 NULL으로 초기화
	}
	~TrieNode() {
		for (int i = 0; i < MAX_LEN; i++)
			if (child[i])		//child가 0이 아니라면 삭제
				delete child[i];
	}
	void Insert(const char* key) //문자열 삽입
	{									
		if (*key == NULL)		//만약 키값이 NULL이라면 , 문자열이 끝나는경우
		{
			flag = true;	//마지막 위치 표시
		}
		else {
			int next = *key - '0';		//key값이 전화번호 숫자이기 때문에 
										//key 값에서 0을 빼면 마지막 노드를 찾을 수 있다
			if (child[next] == NULL)		//key값이 바로 null이라면 탐색이 처음
				child[next] = new TrieNode();	//새로운 트라이 노드 동적할당
			child[next]->Insert(key + 1);		//현재 key값의 다음값 삽입 dfs			
		}
	}
	bool Search(const char* key)
	{
		int next = *key - '0';
		if (*key == NULL) return false;	//key값이 null이라면 찾지 못함
		if (this->flag) return true;			//해당 트라이노드의 flag가 true라면 찾는 문자열이 있음
		return child[next]->Search(key + 1);	//다음 문자 탐색 dfs
	}
};

void Process() {
	cin >> N;
	for (int i = 0; i < N; i++)
		cin >> phone[i];
	TrieNode *Root = new TrieNode;
	for (int i = 0; i < N; i++)
		Root->Insert(phone[i]);	//	모든 전화번호 삽입
	for (int i = 0; i < N; i++)
		if (Root->Search(phone[i])) {
			cout << "NO" << endl;	//일관성이 없으면 NO
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
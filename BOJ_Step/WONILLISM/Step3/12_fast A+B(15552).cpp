
//iostream을 이용한 풀이

//#include<iostream>
//using namespace std;
//int main() {
//	cin.tie(NULL);
//	ios::sync_with_stdio(false);
//	freopen("15552.in.txt", "r", stdin);
//	int n, a, b;
//	cin >> n;
//	while (n--) {
//		cin >> a >> b;
//		cout << a + b << "\n";
//	}
//	return 0;	
//}

//cstdio를 이용한 풀이
#include<cstdio>
int main() {
	freopen("15552.in.txt", "r", stdin);
	int n, a, b;
	scanf("%d", &n);
	while (n--) {
		scanf(" %d %d", &a, &b);
		printf("%d", a + b);
		printf("\n");
	}	
	return 0;
}


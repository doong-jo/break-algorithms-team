#include<iostream>
using namespace std;
int main() {
	freopen("11718.in.txt", "r", stdin);
	char str[101];
	//cin.getline(str, 101, '\n')
	while (fgets(str,101,stdin)) {
		cout << str;
	}
	return 0;
}
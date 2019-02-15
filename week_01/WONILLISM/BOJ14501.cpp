//#include <iostream>
//
//using namespace std;
//
//int n, ans;
//int t[16], p[16];
//
//void solve(int day, int profit) {
//	if (day == n + 1) {
//		if (ans < profit) ans = profit;
//		return;
//	}
//	if (day > n + 1) return;
//	solve(day + t[day], profit + p[day]);
//	solve(day + 1, profit);
//}
//void run() {
//	cin >> n;
//	for (int i = 1; i <= n; i++) {
//		cin >> t[i] >> p[i];
//
//	}
//	solve(1, 0);
//	cout << ans;
//	
//
//}




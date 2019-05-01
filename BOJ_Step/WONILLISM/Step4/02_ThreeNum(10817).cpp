#include<cstdio>
void MergeSort(int *data, int start, int end) {
	int tmp[3];
	int i = start;
	int k = start;
	int m = (start + end) / 2;
	int j = m + 1;
	if (start >= end)return;
	MergeSort(data, start, m);
	MergeSort(data, m + 1, end);
	while (i <= m && j <= end) {
		if (data[i] < data[j])tmp[k++] = data[i++];
		else tmp[k++] = data[j++];
	}
	while (i <= m)tmp[k++] = data[i++];
	while (j <= end)tmp[k++] = data[j++];
	for (i = start; i <= end; i++)	data[i] = tmp[i];
}
int main() {
	int n[3];
	for (int i = 0; i < 3; i++)
		scanf("%d", &n[i]);
	MergeSort(n, 0, 2);
	printf("%d", n[1]);
	return 0;
}
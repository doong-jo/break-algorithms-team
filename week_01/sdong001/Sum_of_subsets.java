/**
 * url : https://www.acmicpc.net/problem/1182
 *
 * 문제
 * N개의 정수로 이루어진 집합이 있을 때, 이 집합의 공집합이 아닌 부분집합 중에서 그 집합의 원소를 다 더한 값이 S가 되는 경우의 수를 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 정수의 개수를 나타내는 N과 정수 S가 주어진다. (1≤N≤20, |S|≤1,000,000) 둘째 줄에 N개의 정수가 빈 칸을 사이에 두고 주어진다. 주어지는 정수의 절댓값은 100,000을 넘지 않는다. 같은 수가 여러 번 주어질 수도 있다.
 *
 * 출력
 * 첫째 줄에 합이 S가 되는 부분집합의 개수를 출력한다.
 */

package brute_force;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Sum_of_subsets {
    static int[] nums;
    static int N;
    static int S;

    static int sum = 0;
    static int result = 0;

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final String config = br.readLine();
        StringTokenizer st = new StringTokenizer(config, " ");

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        final String arrNum = br.readLine();
        st = new StringTokenizer(arrNum, " ");

        if( N == 0 ) {
            System.out.println(result);
            return;
        }

        nums = new int[N];

        for(int i=0; i<N; i++) { nums[i] = (Integer.parseInt(st.nextToken())); }

        findSubset(0, 0, 0);

        System.out.println(result);
    }

    static void findSubset(final int sum, final int cnt, final int length) {
        if( cnt == N ) {
            result = sum == S && length != 0 ? result + 1 : result;
            return;
        }

        // except itself
        findSubset(sum, cnt + 1, length);

        // include itself
        findSubset(sum + nums[cnt], cnt + 1, length + 1);
    }
}

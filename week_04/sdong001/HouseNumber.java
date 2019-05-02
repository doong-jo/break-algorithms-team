/**
 *
 * url : https://www.acmicpc.net/problem/2667
 *
 * <그림 1>과 같이 정사각형 모양의 지도가 있다. 1은 집이 있는 곳을, 0은 집이 없는 곳을 나타낸다. 철수는 이 지도를 가지고 연결된 집들의 모임인 단지를 정의하고, 단지에 번호를 붙이려 한다. 여기서 연결되었다는 것은 어떤 집이 좌우, 혹은 아래위로 다른 집이 있는 경우를 말한다. 대각선상에 집이 있는 경우는 연결된 것이 아니다. <그림 2>는 <그림 1>을 단지별로 번호를 붙인 것이다. 지도를 입력하여 단지수를 출력하고, 각 단지에 속하는 집의 수를 오름차순으로 정렬하여 출력하는 프로그램을 작성하시오.
 *
 *
 *
 * 입력
 * 첫 번째 줄에는 지도의 크기 N(정사각형이므로 가로와 세로의 크기는 같으며 5≤N≤25)이 입력되고, 그 다음 N줄에는 각각 N개의 자료(0혹은 1)가 입력된다.
 *
 * 출력
 * 첫 번째 줄에는 총 단지수를 출력하시오. 그리고 각 단지내 집의 수를 오름차순으로 정렬하여 한 줄에 하나씩 출력하시오.
 *
 * testcase 1 :
 * 7
 * 0110100
 * 0110101
 * 1110101
 * 0000111
 * 0100000
 * 0111110
 * 0111000
 *
 * output:
 * 3
 * 7
 * 8
 * 9
 */

package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class HouseNumber {
    static int N;
    static int[][] map;
    static boolean[][] visited;

    static int[] xDir = { -1, 1, 0, 0 };
    static int[] yDir = { 0, 0, -1, 1};

    static class Step {
        int x;
        int y;

        Step(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        final String config = br.readLine();
        N = Integer.valueOf(config);

        visited = new boolean[N][N];
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            final String mapLine = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.valueOf(String.valueOf(mapLine.charAt(j)));
            }
        }

        solve();
    }

    static void solve() {
        ArrayList<Integer> apt = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int houseCnt = bfs(i, j);
                if( houseCnt != 0 ) { apt.add(houseCnt); }
            }
        }

        System.out.println(apt.size());

        Collections.sort(apt);
        for (Integer i : apt) { System.out.println(i); }
    }

    static int bfs(int y, int x) {
        if( map[y][x] == 0 || visited[y][x] ) { return 0; }

        Queue<Step> q = new LinkedList<>();
        q.offer(new Step(y, x));

        int cnt = 1;
        visited[y][x] = true;

        while( !q.isEmpty() ) {
            Step pop = q.poll();

            for (int i = 0; i < 4; i++) {
                final int mx = pop.x + xDir[i], my = pop.y + yDir[i];
                if( isNotRange(my, mx) || visited[my][mx] || map[my][mx] == 0 ) { continue; }

                cnt++;
                visited[my][mx] = true;

                q.offer(new Step(my, mx));
            }
        }

        return cnt;
    }

    static boolean isNotRange(int y, int x) {
        return x < 0 || y < 0 || x >= N || y >= N;
    }
}

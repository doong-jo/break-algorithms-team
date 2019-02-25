/**
 * url : https://www.acmicpc.net/problem/2178
 *
 * 문제
 * N×M크기의 배열로 표현되는 미로가 있다.
 *
 * 1	0	1	1	1	1
 * 1	0	1	0	1	0
 * 1	0	1	0	1	1
 * 1	1	1	0	1	1
 * 미로에서 1은 이동할 수 있는 칸을 나타내고, 0은 이동할 수 없는 칸을 나타낸다. 이러한 미로가 주어졌을 때, (1, 1)에서 출발하여 (N, M)의 위치로 이동할 때 지나야 하는 최소의 칸 수를 구하는 프로그램을 작성하시오. 한 칸에서 다른 칸으로 이동할 때, 서로 인접한 칸으로만 이동할 수 있다.
 *
 * 위의 예에서는 15칸을 지나야 (N, M)의 위치로 이동할 수 있다. 칸을 셀 때에는 시작 위치와 도착 위치도 포함한다.
 *
 * 입력
 * 첫째 줄에 두 정수 N, M(2 ≤ N, M ≤ 100)이 주어진다. 다음 N개의 줄에는 M개의 정수로 미로가 주어진다. 각각의 수들은 붙어서 입력으로 주어진다.
 *
 * 출력
 * 첫째 줄에 지나야 하는 최소의 칸 수를 출력한다. 항상 도착위치로 이동할 수 있는 경우만 입력으로 주어진다.
 *
 * test case 1:
 * 4 6
 * 101111
 * 101010
 * 101011
 * 111011
 *
 * : 15
 *
 * test case 2:
 * 4 6
 * 110110
 * 110110
 * 111111
 * 111101
 *
 * : 9
 *
 * test case 3:
 * 2 25
 * 1011101110111011101110111
 * 1110111011101110111011101
 *
 * : 38
 *
 * test case 4:
 * 7 7
 * 1011111
 * 1110001
 * 1000001
 * 1000001
 * 1000001
 * 1000001
 * 1111111
 *
 * : 13
 */

package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Maze {
    static int N;
    static int M;
    static int[][] Maze;
    static boolean[][] visited;
    static int result;

    //LEFT, RIHGT, UP, DOWN
    static int[] ydir = { 0, 0, 1, -1 };
    static int[] xdir = { -1, 1, 0, 0 };

    static class Step {
        int y;
        int x;
        int step;

        Step(int y, int x, int step) {
            this.y = y;
            this.x = x;
            this.step = step;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String config = br.readLine();

        StringTokenizer tokenizer = new StringTokenizer(config, " ");
        N = Integer.valueOf(tokenizer.nextToken());
        M = Integer.valueOf(tokenizer.nextToken());

        Maze = new int[N][];
        visited = new boolean[N][];

        for (int i=0; i<N; i++){
            Maze[i] = new int[M];
            visited[i] = new boolean[M];

            String mazeLine = br.readLine();

            for (int j=0; j<M; j++) {
                Maze[i][j] = Integer.valueOf(
                        String.valueOf(mazeLine.charAt(j))
                );
            }
        }

        bfs();
    }

    static void bfs() {
        Queue<Step> q = new LinkedList<>();
        q.offer(new Step(0, 0, 1));

        while( !q.isEmpty() ) {
            Step popStep = q.poll();

            if( popStep.y == N - 1 && popStep.x == M - 1 ) {
                System.out.println(popStep.step);
                return;
            }

            for(int i=0; i<4; i++) {
                final int moveY = popStep.y + ydir[i];
                final int moveX = popStep.x + xdir[i];

                if( moveY == N || moveX == M ||
                        moveY == -1 || moveX == -1 ||
                        Maze[moveY][moveX] == 0 ||
                        visited[moveY][moveX] ) { continue; }

                visited[moveY][moveX] = true;
                q.offer(new Step(moveY, moveX, popStep.step + 1));
            }
        }
    }

//    static void dfs(int y, int x, int cnt) {
//        visited[y][x] = true;
//        cnt = cnt + 1;
//
//        for(int i=0; i<4; i++) {
//            final int moveY = y + ydir[i];
//            final int moveX = x + xdir[i];
//
//            if( moveY == N || moveX == M ||
//                    moveY == -1 || moveX == -1 ||
//                    Maze[moveY][moveX] == 0 ||
//                    visited[moveY][moveX]) { continue; }
//
//            if( moveY == N - 1 && moveX == M - 1 ) {
//                result = result > cnt + 1 || result == 0 ? cnt + 1 : result;
//                visited[y][x] = false;
//                return;
//            }
//            if ( !visited[moveY][moveX] && cnt + 1 < result ) {
//                dfs(moveY, moveX, cnt + 1);
//            }
//        }
//        visited[y][x] = false;
//    }
}

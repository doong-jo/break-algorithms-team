/**
 * url : https://www.acmicpc.net/problem/7576
 *
 * 문제
 * 철수의 토마토 농장에서는 토마토를 보관하는 큰 창고를 가지고 있다. 토마토는 아래의 그림과 같이 격자 모양 상자의 칸에 하나씩 넣어서 창고에 보관한다.
 *
 * 창고에 보관되는 토마토들 중에는 잘 익은 것도 있지만, 아직 익지 않은 토마토들도 있을 수 있다. 보관 후 하루가 지나면, 익은 토마토들의 인접한 곳에 있는 익지 않은 토마토들은 익은 토마토의 영향을 받아 익게 된다. 하나의 토마토의 인접한 곳은 왼쪽, 오른쪽, 앞, 뒤 네 방향에 있는 토마토를 의미한다. 대각선 방향에 있는 토마토들에게는 영향을 주지 못하며, 토마토가 혼자 저절로 익는 경우는 없다고 가정한다. 철수는 창고에 보관된 토마토들이 며칠이 지나면 다 익게 되는지, 그 최소 일수를 알고 싶어 한다.
 *
 * 토마토를 창고에 보관하는 격자모양의 상자들의 크기와 익은 토마토들과 익지 않은 토마토들의 정보가 주어졌을 때, 며칠이 지나면 토마토들이 모두 익는지, 그 최소 일수를 구하는 프로그램을 작성하라. 단, 상자의 일부 칸에는 토마토가 들어있지 않을 수도 있다.
 *
 * 입력
 * 첫 줄에는 상자의 크기를 나타내는 두 정수 M,N이 주어진다. M은 상자의 가로 칸의 수, N 은 상자의 세로 칸의 수를 나타낸다. 단, 2 ≤ M,N ≤ 1,000 이다. 둘째 줄부터는 하나의 상자에 저장된 토마토들의 정보가 주어진다. 즉, 둘째 줄부터 N개의 줄에는 상자에 담긴 토마토의 정보가 주어진다. 하나의 줄에는 상자 가로줄에 들어있는 토마토의 상태가 M개의 정수로 주어진다. 정수 1은 익은 토마토, 정수 0은 익지 않은 토마토, 정수 -1은 토마토가 들어있지 않은 칸을 나타낸다.
 *
 * 출력
 * 여러분은 토마토가 모두 익을 때까지의 최소 날짜를 출력해야 한다. 만약, 저장될 때부터 모든 토마토가 익어있는 상태이면 0을 출력해야 하고, 토마토가 모두 익지는 못하는 상황이면 -1을 출력해야 한다.
 *
 * test case 1 :
 * 6 4
 * 0 0 0 0 0 0
 * 0 0 0 0 0 0
 * 0 0 0 0 0 0
 * 0 0 0 0 0 1
 *
 * output : 8
 *
 * test case 2 :
 * 6 4
 * 0 -1 0 0 0 0
 * -1 0 0 0 0 0
 * 0 0 0 0 0 0
 * 0 0 0 0 0 1
 *
 * output : -1
 *
 * test case 3 :
 * 6 4
 * 1 -1 0 0 0 0
 * 0 -1 0 0 0 0
 * 0 0 0 0 -1 0
 * 0 0 0 0 -1 1
 *
 * output : 6
 *
 * test case 4 :
 * 5 5
 * -1 1 0 0 0
 * 0 -1 -1 -1 0
 * 0 -1 -1 -1 0
 * 0 -1 -1 -1 0
 * 0 0 0 0 0
 *
 * output : 14
 *
 * test case 5 :
 * 2 2
 * 1 -1
 * -1 1
 *
 * output : 0
 */

package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Tomato {
    static int N;
    static int M;
    static int[][] Tomato;
    static boolean[][] visited;
    static int NotGrowCnt;
    static Queue<Step> q;

    static final int G = 1;
    static final int NG = 0;
    static final int E = -1;

    //LEFT, RIHGT, UP, DOWN
    static int[] ydir = { 0, 0, 1, -1 };
    static int[] xdir = { -1, 1, 0, 0 };

    static class Step {
        int x, y, depth;

        Step(int x, int y, int depth) {
            this.x = x;
            this.y = y;
            this.depth = depth;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String config = br.readLine();

        StringTokenizer tokenizer = new StringTokenizer(config, " ");
        M = Integer.valueOf(tokenizer.nextToken());
        N = Integer.valueOf(tokenizer.nextToken());

        Tomato = new int[N][];
        visited = new boolean[N][];
        q = new LinkedList<>();

        for (int i=0; i<N; i++){
            Tomato[i] = new int[M];
            visited[i] = new boolean[M];

            String FieldLine = br.readLine();
            StringTokenizer tomatoTokenizer = new StringTokenizer(FieldLine, " ");

//            System.out.println(FieldLine);
            for (int j=0; j<M; j++) {
                Tomato[i][j] = Integer.valueOf(tomatoTokenizer.nextToken());
                if( Tomato[i][j] == G ) {
                    q.offer(new Step(j, i, 0));
                } else if( Tomato[i][j] == NG) {
                    NotGrowCnt++;
                }
            }
        }

        bfs();
    }

    static void bfs() {
        int day = 0;
        int growCnt = 0;

        while (!q.isEmpty()) {
            Step popStep = q.poll();
            day = day < popStep.depth ? popStep.depth : day;

            for (int i = 0; i < 4; i++) {
                final int moveY = popStep.y + ydir[i];
                final int moveX = popStep.x + xdir[i];

                if (moveY == N || moveX == M ||
                        moveY == -1 || moveX == -1 ||
                        Tomato[moveY][moveX] != NG) {
                    continue;
                }

                Tomato[moveY][moveX] = G;
                growCnt++;
                q.offer(new Step(moveX, moveY, popStep.depth + 1));
            }
        }

        day = NotGrowCnt != growCnt ? -1 : day;
        System.out.println(day);
    }
}

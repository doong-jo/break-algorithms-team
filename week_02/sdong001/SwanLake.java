/**
 * url : https://www.acmicpc.net/problem/3197
 *
 * 문제
 * 두 마리의 백조가 호수에서 살고 있었다. 그렇지만 두 마리는 호수를 덮고 있는 빙판으로 만나지 못한다.
 *
 * 호수는 가로로 R, 세로로 C만큼의 직사각형 모양이다. 어떤 칸은 얼음으로 덮여있다.
 *
 * 호수는 차례로 녹는데, 매일 물 공간과 접촉한 모든 빙판 공간은 녹는다. 두 개의 공간이 접촉하려면 가로나 세로로 닿아 있는 것만 (대각선은 고려하지 않는다) 생각한다.
 *
 * 아래에는 세 가지 예가 있다.
 *
 * ...XXXXXX..XX.XXX ....XXXX.......XX .....XX..........
 * ....XXXXXXXXX.XXX .....XXXX..X..... ......X..........
 * ...XXXXXXXXXXXX.. ....XXX..XXXX.... .....X.....X.....
 * ..XXXXX..XXXXXX.. ...XXX....XXXX... ....X......XX....
 * .XXXXXX..XXXXXX.. ..XXXX....XXXX... ...XX......XX....
 * XXXXXXX...XXXX... ..XXXX.....XX.... ....X............
 * ..XXXXX...XXX.... ....XX.....X..... .................
 * ....XXXXX.XXX.... .....XX....X..... .................
 * in the beginning   after first day   after second day
 * 백조는 오직 물 공간에서 세로나 가로로만(대각선은 제외한다) 움직일 수 있다.
 *
 * 며칠이 지나야 백조들이 만날 수 있는 지 계산하는 프로그램을 작성한다.
 *
 * 입력
 * 입력의 첫째 줄에는 R과 C가 주어진다. 단, 1 ≤ R, C ≤ 1500.
 *
 * 각 R줄 동안 C만큼의 문자열이 주어진다.
 *
 * '.'은 물 공간, 'X'는 빙판 공간, 'L'은 백조가 있는 공간으로 나타낸다.
 *
 * 출력
 * 첫째 줄에 문제에서 주어진 걸리는 날을 출력한다.
 */

package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SwanLake {
    static int R;
    static int C;
    static char[][] Lake;
    static boolean[][] visited;

    static final char ICE = 'X';
    static final char WATER = '.';
    static final char SWAN = 'L';

    //LEFT, RIHGT, UP, DOWN
    static int[] ydir = { 0, 0, 1, -1 };
    static int[] xdir = { -1, 1, 0, 0 };

    static class Step {
        int x, y;

        Step(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String config = br.readLine();

        StringTokenizer tokenizer = new StringTokenizer(config, " ");
        R = Integer.valueOf(tokenizer.nextToken());
        C = Integer.valueOf(tokenizer.nextToken());

        Lake = new char[R][];
        visited = new boolean[R][];

        for (int i=0; i<R; i++){
            Lake[i] = new char[C];
            visited[i] = new boolean[C];

            String FieldLine = br.readLine();

            for (int j=0; j<C; j++) {
                Lake[i][j] = FieldLine.charAt(j);
            }
        }

        bfs();
    }

    static void bfs() {
        int dayCnt = 0;
        Queue<Step> q = new LinkedList<>();

        while( true ) {
            for (int y=0; y<R; y++) {
                for (int x=0; x<C; x++) {
                    if( visited[y][x] || Lake[y][x] == ICE ) { continue; }

                    q.offer(new Step(x, y));
                    int swanCnt = 0;

                    while( !q.isEmpty() ) {
                        Step popStep = q.poll();
                        visited[popStep.y][popStep.x] = true;

                        for(int i=0; i<4; i++) {
                            final int moveY = popStep.y + ydir[i];
                            final int moveX = popStep.x + xdir[i];

                            if( moveY == R || moveX == C ||
                                    moveY == -1 || moveX == -1 ||
                                    visited[moveY][moveX] ) { continue; }

                            if( Lake[moveY][moveX] == ICE ) {
                                Lake[moveY][moveX] = WATER;
                                visited[moveY][moveX] = true;
                                continue;
                            }
                            else if( Lake[moveY][moveX] == SWAN ) {
                                swanCnt++;
                                visited[moveY][moveX] = true;
                                if( swanCnt == 2 ) {
                                    System.out.println(dayCnt);
                                    return;
                                }
                            }

                            q.offer(new Step(moveX, moveY));
                        }
                    }
//                    printLake();
//                    System.out.println();
                }
            }
            for (int y=0; y<R; y++) {
                for (int x = 0; x < C; x++) {
                    visited[y][x] = false;
                }
            }
            dayCnt++;
        }

    }

//    static void printLake() {
//        for (int y=0; y<R; y++) {
//            for (int x=0; x<C; x++) {
//                System.out.print(Lake[y][x]);
//            }
//            System.out.println();
//        }
//    }
}

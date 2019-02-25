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

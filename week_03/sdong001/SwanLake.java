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
 *
 * testcase 1 :
 * 8 17
 * ...XXXXXX..XX.XXX
 * ....XXXXXXXXX.XXX
 * ...XXXXXXXXXXXX..
 * ..XXXXX.LXXXXXX..
 * .XXXXXX..XXXXXX..
 * XXXXXXX...XXXX...
 * ..XXXXX...XXX....
 * ....XXXXX.XXXL...
 *
 * output : 2
 */

package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class SwanLake {
    static int R, C;
    static char[][] Lake;
    static boolean[][] visited;
    static int[][] dayCnt;
    static Queue<Step> warmQ;

    static Step[] swan;
    static final int START_IND = 0;
    static final int DEST_IND = 1;

    static final char ICE = 'X';
    static final char WATER = '.';
    static final char SWAN = 'L';

    static int[] ydir = { 0, 0, 1, -1 };
    static int[] xdir = { -1, 1, 0, 0 };

    static class Step implements Comparable<Step> {
        int x, y, day;

        Step(int x, int y, int day) {
            this.x = x;
            this.y = y;
            this.day = day;
        }

        public boolean equals(Step obj) {
            return obj.x == x && obj.y == y;
        }

        @Override
        public int compareTo(Step o) {
            return this.day - o.day;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String config = br.readLine();

        StringTokenizer tokenizer = new StringTokenizer(config, " ");
        R = Integer.valueOf(tokenizer.nextToken());
        C = Integer.valueOf(tokenizer.nextToken());

        Lake = new char[R][C];
        dayCnt = new int[R][C];

        swan = new Step[2];
        int swanCnt = 0;

        warmQ = new LinkedList<>();

        for (int i=0; i<R; i++){

            String FieldLine = br.readLine();

            for (int j=0; j<C; j++) {
                final char charAt = FieldLine.charAt(j);

                if( charAt == SWAN ) { swan[swanCnt++] = new Step(j, i, 0); }
                if( charAt == WATER || charAt == SWAN ) { warmQ.offer(new Step(j, i, 0)); }

                Lake[i][j] = charAt;
            }
        }

        makeWaterArr();
        System.out.println(bfs());
    }

    static int bfs() {
        visited = new boolean[R][C];

        // 우선순위 큐를 사용하여 녹이는 데 걸리는 시간이 큰(깊숙히) 방향을 우선 처리. 깊이 움직이고 퍼지는
        PriorityQueue<Step> q = new PriorityQueue<>();
        q.offer(swan[START_IND]);
        visited[swan[START_IND].y][swan[START_IND].x] = true;

        while( !q.isEmpty() ) {
            Step popStep = q.poll();

            if( popStep.equals(swan[DEST_IND]) ) { return popStep.day; }

            for(int i=0; i<4; i++) {
                final int mY = popStep.y + ydir[i], mX = popStep.x + xdir[i];

                if( isRange(mX, mY) || visited[mY][mX] ) { continue; }

                // 최대 water 마킹 수를 push한다.
                //          "L 0 1 2 3 2 1 0 L" 일 때 3 push를 유지
                // day ->    0 0 1 2 3 3 3 3 3
                visited[mY][mX] = true;
                q.offer(new Step(mX, mY, Math.max(dayCnt[mY][mX], popStep.day)));
            }
        }

        return 0;
    }

    static void makeWaterArr() {
        visited = new boolean[R][C];

        int day = 0;
        // 모든 호수
        while( !warmQ.isEmpty() ) {
            int i = warmQ.size();

            // 하루 단위로 녹이는
            while( i-- > 0 ) {
                Step popStep = warmQ. poll();
                dayCnt[popStep.y][popStep.x] = day;

                for(int j=0; j<4; j++) {
                    final int mX = popStep.x + xdir[j], mY = popStep.y + ydir[j];

                    if( isRange(mX, mY) || visited[mY][mX]) { continue; }

                    visited[mY][mX] = true;

                    // 첫번째 반복 이후로는 테두리를 push
                    warmQ.offer(new Step(mX, mY, day));
                }
            } day++;
        } warmQ = null;
    }

    static boolean isRange(int x, int y) {
        return x < 0 || y < 0 || x >= C || y >= R;
    }
}

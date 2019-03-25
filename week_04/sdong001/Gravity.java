/**
 *
 * url : https://www.acmicpc.net/problem/5827
 * url2 : http://www.usaco.org/index.php?page=open13problems
 *
 * 문제
 * Captain Bovidian is on an adventure to rescue her crew member, Doctor Beefalo. Like all great adventures, this story plays out in a two dimensional N by M grid (1 <= N, M <= 500), representing a side view of the captain's world. Some grid cells are empty while others are blocked and cannot be traversed.
 *
 * Unfortunately, Captain Bovidian cannot jump. She must obey the following rules of physics while traversing her world.
 *
 * 1) If there is no cell directly underneath Captain Bovidian (that is, if she is at the edge of the grid), then she flies out into space and fails her mission. 2) If the cell directly underneath Captain Bovidian is empty, then she falls into that cell. 3) Otherwise: a) Captain Bovidian may move left or right if the corresponding cell exists and is empty. b) Or, Captain Bovidian may flip the direction of gravity.
 *
 * When Captain Bovidian changes the direction of gravity, the cell that's 'underneath' her (as mentioned in rules 1 and 2) toggles between the cell with one higher row index and the cell with one lower row index (the first row in the input has index 1, and the last row has index N). Initially, the cells with one higher row index are underneath Captain Bovidian.
 *
 * Doctor Beefalo is lost somewhere in this world. Help Captain Bovidian arrive at her cell using the least number of gravity flips as possible. If it is impossible to reach Doctor Beefalo, please output -1.
 *
 * 입력
 * Line 1: Two space-separated integers N and M.
 * Lines 2..1+N: Line i+1 describes the ith row of Captain Bovidian's world: '.' indicates an empty cell, '#' indicates a blocked cell, 'C' indicates Captain Bovidian's starting position, and 'D' indicates Doctor Beefalo's starting position.
 *
 * 출력
 * Line 1: A single integer indicating the minimum number of times Captain Bovidian must flip gravity to reach Doctor Beefalo, or -1 if it is impossible to reach Doctor Beefalo.
 *
 * testcase 1:
 * 5 5
 * #####
 * #...#
 * #...D
 * #C...
 * ##.##
 *
 * output:
 * 3
 */

package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Gravity {
    static int N;
    static int M;
    static char[][] map;
    static boolean[][] visited;
    static Step cap;
    static Step doc;
    static int result;

    static int[] xDir = { -1, 1, 0, 0 };

    final static int MV_LEFT = 0;
    final static int MV_RIGHT = 1;
    final static int MV_DOWN = 2;
    final static int MV_UP = 3;

    final static char BLOCK = '#';
    final static char EMPTY = '.';
    final static char CAP = 'C';
    final static char DOC = 'D';

    static class Step {
        int x, y, gCnt, gDir;

        Step(int y, int x, int gCnt, int gDir) {
            this.y = y;
            this.x = x;
            this.gCnt = gCnt;
            this.gDir = gDir;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        final String config = br.readLine();
        StringTokenizer stringTokenizer = new StringTokenizer(config, " ");

        N = Integer.valueOf(stringTokenizer.nextToken());
        M = Integer.valueOf(stringTokenizer.nextToken());

        result = N;
        map = new char[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            final String mapLine = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = mapLine.charAt(j);

                if( map[i][j] == CAP ) { cap = new Step(i, j, 0, MV_DOWN); }
                else if( map[i][j] == DOC ) { doc = new Step(i, j, 0, MV_DOWN); }
            }
        }

        System.out.println(bfs());
    }

    static int bfs() {
        Queue<Step> q = new LinkedList<>();
        q.offer(cap);
        visited[cap.y][cap.x] = true;

        while( !q.isEmpty() ) {
            Step pop = q.poll();
            if( result != N && result <= pop.gCnt ) { continue; }
            if( !gravity(pop.gDir, pop) ) { continue; }

            for (int i = 0; i < 4; i++) {
                int gDir = i == MV_UP || i == MV_DOWN ? i : pop.gDir;
                int gCnt = i == MV_UP || i == MV_DOWN ? 1 : 0;
                int mx = pop.x + xDir[i], my = getEndY(pop, i);

                if( isNotRange(my, mx) || visited[my][mx] || map[my][mx] == BLOCK ) { continue; }

                if( isArrive(my, mx) ) { result = Math.min(result, pop.gCnt + gCnt); }
                q.offer(new Step(my, mx, pop.gCnt + gCnt, gDir));
            }
        }

        if( result != N ) { return result; }
        return -1;
    }

    static int getEndY(Step curStep, int dir) {
        if( dir == MV_LEFT || dir == MV_RIGHT ) { return curStep.y; }

        int add = dir == MV_UP ? -1 : 1;
        int endY = curStep.y;

        if( isNotRange(endY + add, curStep.x) ) { return endY; }
        while( map[endY + add][curStep.x] != BLOCK ) {
            endY += add;
            if( isArrive(endY, curStep.x) ) {
                result = Math.min(result, curStep.gCnt + 1);
            }
            if( isNotRange(endY + add, curStep.x) ) { return endY; }
        }

        return endY;
    }

    static boolean gravity(int dir, Step obj) {
        int add = dir == MV_UP ? -1 : 1;

        if( isNotRange(obj.y + add, obj.x) ) { return false; }

        while( map[obj.y + add][obj.x] != BLOCK ) {
            obj.y += add;

            if( isArrive(obj.y, obj.x) ) {
                result = Math.min(result, obj.gCnt);
                return true;
            }

            if( isNotRange(obj.y + add, obj.x) ) { return false; }
        }
        visited[obj.y][obj.x] = true;
        return true;
    }

    static boolean isArrive(int y, int x) { return doc.y == y && doc.x == x; }

    static boolean isNotRange(int y, int x) { return x < 0 || y < 0 || x >= M || y >= N; }
}

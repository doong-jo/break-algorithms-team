/**
 * url : https://www.acmicpc.net/problem/3184
 *
 * 문제
 * 미키의 뒷마당에는 특정 수의 양이 있다. 그가 푹 잠든 사이에 배고픈 늑대는 마당에 들어와 양을 공격했다.
 *
 * 마당은 행과 열로 이루어진 직사각형 모양이다. 글자 '.' (점)은 빈 필드를 의미하며, 글자 '#'는 울타리를, 'o'는 양, 'v'는 늑대를 의미한다.
 *
 * 한 칸에서 수평, 수직만으로 이동하며 울타리를 지나지 않고 다른 칸으로 이동할 수 있다면, 두 칸은 같은 영역 안에 속해 있다고 한다. 마당에서 "탈출"할 수 있는 칸은 어떤 영역에도 속하지 않는다고 간주한다.
 *
 * 다행히 우리의 양은 카라테 검은 띠 보유자여서 늑대에게 싸움을 걸 수 있고 영역 안의 양의 수가 늑대의 수보다 많다면 이기게 된다. 그렇지 않다면 늑대가 그 지역 안의 모든 양을 먹는다.
 *
 * 맨 처음 모든 양과 늑대는 마당 안 영역에 존재한다.
 *
 * 아침이 도달했을 때 살아남은 양과 늑대의 수를 출력하는 프로그램을 작성하라.
 *
 * 입력
 * 첫 줄에는 두 정수 R과 C가 주어지며(3 ≤ R, C ≤ 250), 각 수는 마당의 행과 열의 수를 의미한다.
 *
 * 다음 R개의 줄은 C개의 글자를 가진다. 이들은 마당의 구조(울타리, 양, 늑대의 위치)를 의미한다.
 *
 * 출력
 * 하나의 줄에 아침까지 살아있는 양과 늑대의 수를 의미하는 두 정수를 출력한다.
 *
 * test case 1:
 * 6 6
 * ...#..
 * .##v#.
 * #v.#.#
 * #.o#.#
 * .###.#
 * ...###
 *
 * result : 0 2
 *
 * test case 2:
 * 8 8
 * .######.
 * #..o...#
 * #.####.#
 * #.#v.#.#
 * #.#.o#o#
 * #o.##..#
 * #.v..v.#
 * .######.
 *
 * result : 3 1
 *
 * test case 3:
 * 9 12
 * .###.#####..
 * #.oo#...#v#.
 * #..o#.#.#.#.
 * #..##o#...#.
 * #.#v#o###.#.
 * #..#v#....#.
 * #...v#v####.
 * .####.#vv.o#
 * .......####.
 *
 * result : 3 5
 *
 */

package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Sheep {
    static int N;
    static int M;
    static char[][] Field;
    static boolean[][] visited;

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
        N = Integer.valueOf(tokenizer.nextToken());
        M = Integer.valueOf(tokenizer.nextToken());

        Field = new char[N][];
        visited = new boolean[N][];

        for (int i=0; i<N; i++){
            Field[i] = new char[M];
            visited[i] = new boolean[M];

            String FieldLine = br.readLine();

            for (int j=0; j<M; j++) {
                Field[i][j] = FieldLine.charAt(j);
            }
        }

        bfs();
    }

    static void bfs() {
        int wolfCnt = 0;
        int sheepCnt = 0;

        Queue<Step> q = new LinkedList<>();

        for (int y=0; y<N; y++) {
            for (int x=0; x<M; x++) {
                if( visited[y][x] || Field[y][x] == '#') {
                    continue;
                }

                int wolfNum = 0;
                int sheepNum = 0;

                visited[y][x] = true;
                q.offer(new Step(x, y));

                while( !q.isEmpty() ) {
                    Step popStep = q.poll();

                    wolfNum = Field[popStep.y][popStep.x] == 'v' ? wolfNum + 1 : wolfNum;
                    sheepNum = Field[popStep.y][popStep.x] == 'o' ? sheepNum + 1 : sheepNum;

                    for(int i=0; i<4; i++) {
                        final int moveY = popStep.y + ydir[i];
                        final int moveX = popStep.x + xdir[i];

                        if( moveY == N || moveX == M ||
                                moveY == -1 || moveX == -1 ||
                                Field[moveY][moveX] == '#' ||
                                visited[moveY][moveX]) { continue; }

                        visited[moveY][moveX] = true;
                        q.offer(new Step(moveX, moveY));
                    }
                }

                wolfNum = wolfNum >= sheepNum ? wolfNum : 0;
                sheepNum = sheepNum > wolfNum ? sheepNum : 0;

                wolfCnt += wolfNum;
                sheepCnt += sheepNum;
            }
        }

        System.out.println(sheepCnt + " " + wolfCnt);
    }
}

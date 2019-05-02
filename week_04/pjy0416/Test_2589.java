package dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/** 보물섬 지도
 *  육지 : L, 바다 : W          land, water
 *  상하좌우 육지로만 이동 가능, 한칸 이동시 한시간 증가              => 스텝 필요하다 이번거는
 *  보물은 최단거리로 이동하는 경우에 최장 시간 걸리는 육지 두 곳에 나누어져 있음
 *  두번 이상 지나가거나, 멀리 돌아가면 안됨.
 */
/*
Test Case
5 7
WLLWWWL
LLLWLLL
LWLWLWW
LWLWLLL
WLLWLWW
 */

public class Test_2589 {
    private static int maxX, maxY;
    private static String[] map;
    private static boolean[][] isVisited;
    private static Queue_2589 queue;
    private static int[] wayX = {1,-1,0,0}, wayY = {0,0,1,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        maxY = Integer.parseInt(st.nextToken());
        maxX = Integer.parseInt(st.nextToken());
        map = new String[maxY];

        for(int y=0; y<maxY; y++) {
            map[y] = br.readLine();
        }

        bfs();

        br.close();
    }

    private static void bfs() {
        int result =0;
        queue = new Queue_2589();

        for(int y=0; y<maxY; y++) {
            for(int x=0; x<maxX; x++) {
                if(map[y].charAt(x) =='L') {        // 땅이면 탐색 하셔야죠
                    queue.offer(new Dot_2589(x, y, 0)); // step 0 부터 시작
                    result = Math.max(search(), result);
                }
            }
        }
        System.out.println(result);
    }

    private static int search() {
        isVisited = new boolean[maxY][maxX];
//        Dot_2589 dot = null;
        Dot_2589 dot = new Dot_2589(0,0,0);         // BOJ에서는 null로 초기화하면 컴파일 에러뜸 ;;;

        while(!queue.isEmpty()) {
            dot = queue.poll();
            isVisited[dot.y][dot.x] = true;

            for(int i=0; i<4; i++) {
                int posX = dot.x + wayX[i];
                int posY = dot.y + wayY[i];

                if(isRange(posX, posY) && !isVisited[posY][posX] && map[posY].charAt(posX) == 'L') {         // 땅이고 들른적 없으면
                    queue.offer(new Dot_2589(posX, posY, dot.step+1));
                    isVisited[posY][posX] = true;
                }
            }
        }

        return dot.step;
    }

    private static boolean isRange(int x, int y) {
        return x>=0 && x<maxX && y>=0 && y<maxY ? true : false;
    }
}

class Queue_2589 {
    private final static int MAX_QUEUE_SIZE = 2500;     // 최대 50 * 50;
    private Dot_2589[] dot;
    private int front;
    private int rear;

    Queue_2589() {
        dot = new Dot_2589[MAX_QUEUE_SIZE];
        front =0;
        rear =0;
    }

    void offer(Dot_2589 pos) {
        if(rear == MAX_QUEUE_SIZE) {
            rear =0;
        }
        dot[rear++] = pos;
    }

    Dot_2589 poll() {
        if(front == MAX_QUEUE_SIZE) {
            front =0;
        }
        return dot[front++];
    }

    boolean isEmpty() {
        return front == rear ? true : false;
    }
}

class Dot_2589 {
    int x;
    int y;
    int step;

    Dot_2589(int x, int y, int step) {
        this.x = x;
        this.y = y;
        this.step = step;
    }
}
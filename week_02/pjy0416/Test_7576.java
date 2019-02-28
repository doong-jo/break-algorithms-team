package dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Test_7576 {
    private static int[][] map;                          // 토마토 상자 정보
    private static int maxX, maxY;                       // 박스 크기 정보
    private static boolean[][] isVisited;                // 방문 기록
    private static int[] wayX = {-1, 1, 0, 0};           // 좌 우
    private static int[] wayY = {0, 0, -1, 1};           // 상 하
    private static int unripe =0, cnt =0;                // 안익은 갯수와, 익게 만든 횟수
//    private static LightQueue movePos;                   // 간단 queue
    private static Queue<Dot> movePos;

    public static void main(String[] args) throws IOException {
        BufferedReader br=  new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();
//        LightQueue queue = new LightQueue();
        Queue<Dot> queue = new LinkedList<>();

        maxX = Integer.parseInt(str.split(" ")[0]);
        maxY = Integer.parseInt(str.split(" ")[1]);

        map = new int[maxY][maxX];

        for(int y=0; y<maxY; y++) {             // 여기서 초반의 맵에서 1인 부분을 모두 queue 에 집어 넣고 동시 다발적으로
            // 탐색을 시작하는 코드가 중요!
            String[] strArr = br.readLine().split(" ");
            for(int x=0; x<maxX; x++) {
                int info = Integer.parseInt(strArr[x]);
                map[y][x] = info;
                if(info == 1) {              // 익은 토마토 좌표 저장
                    queue.offer(new Dot(x,y,0));
                } else if(info == 0) {
                    unripe++;
                }
            }
        }

        bfs(queue);

        br.close();
    }

    private static void bfs(Queue<Dot> ripeTmt) {
//        movePos = new LightQueue();
        movePos = new LinkedList<>();
        isVisited = new boolean[maxY][maxX];

        while(!ripeTmt.isEmpty()) {
            Dot pos = ripeTmt.poll();
            movePos.offer(pos);
            isVisited[pos.y][pos.x] = true;
        }

        int day = 0;

        while(!movePos.isEmpty()) {
            Dot pos = movePos.poll();
            day = day < pos.step ? pos.step : day;
            search(pos);
        }

        printResult(day);
    }

    private static void search(Dot pos) {
        for(int direction=0; direction<4; direction++) {                // 4방향 검색
            int x = pos.x + wayX[direction];
            int y = pos.y + wayY[direction];

            if( x>=0 && x<maxX && y>=0 && y<maxY ) {                // out of index 방지
                if(map[y][x] == 0 && !isVisited[y][x]) {       // 비어있는 공간이 아니면
                    movePos.offer(new Dot(x,y, pos.step +1));
                    cnt++;
                }
                isVisited[y][x] = true;
            }
        }
    }

    private static void printResult(int day) {
        int result = unripe == cnt ? day : -1;

        System.out.println(result);
    }
}

class LightQueue {
    private static final int MAX_QUEUE_SIZE = 128;
    private Dot[] pos;
    private int front;
    private int rear;

    LightQueue() {
        pos = new Dot[MAX_QUEUE_SIZE];
        front =0;
        rear =0;
    }

    void offer(Dot dot) {
        if(rear == MAX_QUEUE_SIZE) {
            rear =0;
        }
        pos[rear++] = dot;
    }

    Dot poll() {
        if(front == MAX_QUEUE_SIZE) {
            front =0;
        }
        return pos[front++];
    }

    int getSize() {
        return rear-front;
    }

    boolean isEmpty() {
        return getSize() == 0;
    }

}

class Dot {
    int x;
    int y;
    int step;

    Dot(int x, int y, int step) {
        this.x = x;
        this.y = y;
        this.step = step;
    }
}
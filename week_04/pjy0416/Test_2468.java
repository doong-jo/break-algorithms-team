package dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Test_2468 {
    private static int line;
    private static int[][] map;
    private static boolean[][] isVisited;
    private static int[] wayX = {1, -1, 0, 0};
    private static int[] wayY = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        line = Integer.parseInt(br.readLine());
        map = new int [line][line];
        int maxHeight =0;

        for(int i=0; i<line; i++) {
            String input = br.readLine();
            StringTokenizer st = new StringTokenizer(input, " ");

            for(int idx =0; idx<line; idx++) {
                int height = Integer.parseInt(st.nextToken());
                map[i][idx] = height;
                maxHeight = Math.max(height, maxHeight);
            }
        }

        bfs(maxHeight);

        br.close();
    }

    private static void bfs(int maxHeight) {
        int safeArea =0;
        Queue_2468 queue = new Queue_2468();
        for(int i =0; i<maxHeight; i++) {          // 비가 안올 때 부터 ~ 최대 전까지 (왜냐하면 최고 높이만큼 비오면 다 잠기니까 ~> 0개임)
            isVisited = new boolean[line][line];    // height 마다 방문기록 초기화
            safeArea = Math.max(getSafeArea(i, queue), safeArea);        // 가장 안전구역이 많을 때를 저장
            queue.initalize();      // height 마다 queue 초기화
        }

        System.out.println(safeArea);
    }

    private static int getSafeArea(int rain, Queue_2468 queue) {     // safeArea 갯수 return 메소드
        int result =0;      // result 초기화
        for(int x=0; x<line; x++ ) {                // map 돌면서
            for(int y=0; y<line; y++) {
                if(!isVisited[x][y] && isSafeArea(x, y, rain, queue, isVisited)) {      // 방문한 적 없는 안전 지역이면
                    result += 1;    // 안전영역 갯수 늘려준다.
                }
            }
        }

        return result;
    }

    private static boolean isSafeArea(int x, int y, int rain, Queue_2468 queue, boolean[][] isVisited) {    // 안전영역 탐색
        isVisited[x][y] = true;             // 시작 위치 방문 체크
        if(map[x][y] <= rain) { // 안전지역 아니면
            return false;       // 끝내자
        }

        // 안전 지역이면
        queue.offer(new Dot_2468(x,y));     // Queue 에 안전지역 좌표 집어넣음
        while(!queue.isEmpty()) {           // queue가 빌 때 까지 ~> 안전영역 일 때 까지 진행
            Dot_2468 dot = queue.poll();    // 좌표 꺼내서
            for(int i=0; i<4; i++) {        // 4방향 탐색
                int posX = dot.x + wayX[i];
                int posY = dot.y + wayY[i];

                if (isInRange(posX, posY)) {         // out of range 방지
                    if (!isVisited[posX][posY] && map[posX][posY] > rain) {   // 4방향이 강수량 보다 고지역 && 들른적 없으면
                        queue.offer(new Dot_2468(posX, posY));                  // queue 에 집어넣음
                        isVisited[posX][posY] = true;                           // 방문체크
                    }
                }
            }
        }

        return true;
    }

    private static boolean isInRange(int posX, int posY) {
        if(posX >=0 && posX <line && posY >=0 && posY < line) {
            return true;
        }

        return false;
    }
}

class Queue_2468 {
    private static final int MAX_QUEUE_SIZE = 10001;
    private Dot_2468[] queue;
    private int rear;
    private int front;

    Queue_2468() {
        queue = new Dot_2468[MAX_QUEUE_SIZE];
        rear =0;
        front =0;
    }

    void offer(Dot_2468 dot) {
        if(rear == MAX_QUEUE_SIZE) {
            rear =0;
        }
        queue[rear++] = dot;
    }

    Dot_2468 poll() {
        if(front == MAX_QUEUE_SIZE) {
            front =0;
        }
        return queue[front++];
    }

    boolean isEmpty() {
        return rear == front ? true : false;
    }

    void initalize() {
        front =0;
        rear =0;
    }
}

class Dot_2468 {
    int x;
    int y;

    Dot_2468(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

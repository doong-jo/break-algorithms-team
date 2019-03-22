package dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Test_2667 {
    private static String[] map;
    private static boolean[][] isVisited;
    private static Queue_2667 queue;
    private static int[] wayX = {1,-1,0,0}, wayY = {0,0,1,-1};      // 4 방향 좌표
    private static int estate;          // 단지 수
    private static LightQueue_2667 houses;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int line = Integer.parseInt(br.readLine());
        map = new String[line];
        isVisited = new boolean[line][line];

        for(int i=0; i<line; i++) {
            map[i] = br.readLine();
        }

        bfs(line);

        br.close();
    }

    private static void bfs(int line) {
        queue = new Queue_2667();
        houses = new LightQueue_2667();
        estate =0;

        for(int y=0; y<line; y++) {
            for(int x=0; x<line; x++) {
                if(map[y].charAt(x) == '1' && !isVisited[y][x]) {       // 주택이 있고 들른적 없으면
                    queue.offer(new Dot_2667(x,y));                     // queue에 넣자
                    search(line);
                }
            }
        }
        printResult();
    }

    private static void printResult() {
        int[] housesArr = new int[estate];     // 단지 수 만큼 만들자

        System.out.println(estate);
        int cnt =0;

        while(!houses.isEmpty()) {
            housesArr[cnt++] = houses.poll();
        }
        Arrays.sort(housesArr);

        for(int i=0; i<estate; i++) {
            System.out.println(housesArr[i]);
        }
    }

    private static void search(int max) {
        int cnt =0;

        while(!queue.isEmpty()) {
            Dot_2667 dot = queue.poll();
            isVisited[dot.y][dot.x] = true;     // 시작점 방문 체크
            cnt++;          // 주택 수 증가

            for(int i=0; i<4; i++) {
                int posX = dot.x + wayX[i];
                int posY = dot.y + wayY[i];

                if(isInRange(posX, posY, max) && map[posY].charAt(posX) == '1' && !isVisited[posY][posX]) {            // 범위 안에 있고, 집이고 들린적 없으면
                    queue.offer(new Dot_2667(posX, posY));
                    isVisited[posY][posX] = true;       // 이어진 집 방문 체크
                }
            }
        }
        houses.offer(cnt);
        estate++;           // 단지 수 증가 및 주택 수 저장

    }

    private static boolean isInRange(int x, int y, int max) {
        return x >=0 && x <max && y >=0 && y <max ? true : false ;
    }
}

class Queue_2667 {
    private final static int MAX_QUEUE_SIZE = 625;
    private Dot_2667[] dot;
    private int front;
    private int rear;

    Queue_2667() {
        dot = new Dot_2667[MAX_QUEUE_SIZE];
        front =0;
        rear =0;
    }

    void offer(Dot_2667 pos) {
        if(rear == MAX_QUEUE_SIZE) {
            rear =0;
        }
        dot[rear++] = pos;
    }

    Dot_2667 poll() {
        if(front == MAX_QUEUE_SIZE) {
            front =0;
        }
        return dot[front++];
    }

    boolean isEmpty() {
        return front == rear ? true : false;
    }
}

class LightQueue_2667 {
    private final static int MAX_QUEUE_SIZE = 313;      // 625/2 반올림
    private int[] houses;
    private int front, rear;

    LightQueue_2667() {
        houses = new int[MAX_QUEUE_SIZE];
        front =0;
        rear =0;
    }
    void offer(int house) {
        if(rear == MAX_QUEUE_SIZE) {
            rear =0;
        }
        houses[rear++] = house;
    }

    int poll() {
        if(front == MAX_QUEUE_SIZE) {
            front =0;
        }
        return houses[front++];
    }

    boolean isEmpty() {
        return front == rear ? true : false;
    }

}
class Dot_2667 {
    int x;
    int y;

    Dot_2667(int x, int y) {
        this.x = x;
        this.y = y;
    }
}


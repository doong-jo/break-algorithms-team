package dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test_3184 {
    private static int[] directionX = {1, -1, 0, 0};             // 동 서 남 북
    private static int[] directionY = {0, 0, 1, -1};             // 동 서 남 북
    private static Boolean[][] isVisited;
    private static MyQueue wayPos;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();
        int size = Integer.parseInt(str.split(" ")[0]);
        int depth = Integer.parseInt(str.split(" ")[1]);

        String[] ways = new String[size];
        for(int i=0; i<size; i++) {
            ways[i] = br.readLine();
        }

        bfs(size, depth, ways);

        br.close();
    }

    private static void bfs(int size, int depth, String[] ways) {
        //포지션 0,0 부터 증가하면서 울타리 만나면 스탑
        isVisited= initBool(size, depth);       // 방문기록 초기화
        wayPos = new MyQueue();                 // 길이 있는 Queue의

        int[] animals = new int[2];
        animals[0] = 0;     // 양
        animals[1] = 0;     // 늑대

        for(int y=0; y<size; y++) {                 // 전체 좌표를 도는
            for(int x=0; x<depth; x++) {            // 반복문
                char ch = ways[y].charAt(x);        // 현재 위치
                if(ch != '#' && !isVisited[y][x]) { // 울타리가 아니고 방문한 적이 없으면
                    wayPos.init();                  // 길 초기화
                    wayPos.push(x,y);               // 현재 좌표 push
                    isVisited[y][x] = true;         // 방문 기록 남기기
                    if(ch == 'o') {
                        wayPos.addSheep();          // 양일 경우 양 증가
                    } else if(ch == 'v') {
                        wayPos.addWolf();           // 늑대면 늑대증가
                    }
                    animals = search(size, depth, ways, animals);           // searching
                }
            }
        }

        System.out.println(animals[0] + " " + animals[1]);
    }


    private static int[] search(int size, int depth, String[] ways, int[] animals) {        // 검색 메소드
        int[] result = new int[2];

        while(true) {
            if(wayPos.getSize() ==0) {      // 울타리 안에서 길을 다 돌았다면
                // 울타리 위치 꺼내서 주변 탐색
                if(wayPos.getSheep() > wayPos.getWolf()) {      // 더 큰거를 더해주고, 아닌거는 그대로 값을 줘서 return
                    result[0] = animals[0] + wayPos.getSheep();
                    result[1] = animals[1];
                } else {
                    result[0] = animals[0];
                    result[1] = animals[1] + wayPos.getWolf();
                }

                return result;
            }

            int[] pos = wayPos.pop();           // queue의 포지션 pop
            int posX = pos[0];
            int posY = pos[1];

            for (int i = 0; i < 4; i++) {       // 4방향 탐색
                int x = posX + directionX[i];             // 다음 좌표
                int y = posY + directionY[i];             // 다음 좌표

                if (x >= 0 && x < depth && y >= 0 && y < size) {        // out of index 방지
                    if (!isVisited[y][x]) {                      // 들린적이 없을 때만
                        char ch = ways[y].charAt(x);
                        switch (ch) {
                            case '.':
                                wayPos.push(x, y);
                                break;
                            case 'v':
                                wayPos.push(x, y);
                                wayPos.addWolf();
                                break;
                            case 'o':
                                wayPos.push(x, y);
                                wayPos.addSheep();
                                break;
                            default:
                                break;
                        }
                        isVisited[y][x] = true;
                    }
                }
            }
        }
    }
    private static Boolean[][] initBool(int size, int depth) {      // Bool 초기화
        Boolean[][] result= new Boolean[size][depth];

        for(int i=0; i<size; i++) {
            for(int j=0; j<depth; j++) {
                result[i][j] = false;
            }
        }

        return result;
    }
}

class MyQueue {
    private static final int MAX_QUEUE_SIZE = 128;
    private int[] x;
    private int[] y;
    private int rear;
    private int front;
    private int wolf;
    private int sheep;

    MyQueue() {
        x = new int[MAX_QUEUE_SIZE];
        y = new int[MAX_QUEUE_SIZE];
        front =0;
        rear =0;
        wolf =0;
        sheep =0;
    }

    void push(int posX, int posY) {         // 원형 큐 push
        if(rear == MAX_QUEUE_SIZE) {
            rear =0;
        }
        x[rear] = posX;
        y[rear] = posY;
        addSize();
    }

    private void addSize() {
        this.rear++;
    }

    int getSize() {                         // 현재 큐에 몇개 남았는지 return
        return this.rear-this.front;
    }

    int[] pop() {                           // 원형 큐 pop
        if(front == MAX_QUEUE_SIZE) {
            front =0;
        }
        int[] result = new int[2];
        result[0] = x[front];
        result[1] = y[front];
        front ++;

        return result;
    }

    void addWolf() {
        this.wolf++;
    }

    void addSheep() {
        this.sheep++;
    }

    int getWolf() {
        return this.wolf;
    }

    int getSheep() {
        return this.sheep;
    }

    void init() {           // 동물 초기화
        wolf =0;
        sheep =0;
    }



}
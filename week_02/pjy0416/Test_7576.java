package dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test_7576 {
    private static String[][] map;                          // 토마토 상자 정보
    private static int maxX, maxY;                          // 박스 크기 정보
    private static boolean[][] isVisited;                   // 방문 기록
    private static int blank;                               // 비어있는 갯수
    private static int[] wayX = {-1, 1, 0, 0};                         // 좌 우
    private static int[] wayY = {0, 0, -1, 1};                         // 상 하

    private static LightQueue[] movePos;
    private static int step,cnt;


    private static void bfs(LightQueue ripeTmt) {
        step =0;                    // 확산 스텝 counting 변수
        cnt =0;                      // 변하는 갯수 counting 변수

        /*if(ripeTmt.getSize() ==0) { // 익은게 아예 없으면
            System.out.println("-1");
            return;
        }*/

        int size = ripeTmt.getSize();
        movePos = new LightQueue[size];             // 익은 갯수 만큼 Queue 생성
        isVisited = initBool();

        for(int i=0; i<size; i++) {                 // 각각의 익은 좌표에서 탐색을 위해 좌표를 넣어줌
            movePos[i] = new LightQueue();
            int[] pos = ripeTmt.pop();
            movePos[i].push(pos[0],pos[1]);
            isVisited[pos[1]][pos[0]] = true;
        }

        while(true) {
            boolean isDone = false;

            for (int i = 0; i < size; i++) {            // queue 갯수만큼 search 실행
                int posSize = movePos[i].getSize();     // 각각 queue 에 있는 좌표들 size를 받아줌
                for(int j=0; j<posSize; j++) {          // 그 좌표 만큼
                    if(search(i, movePos[i].pop())) {   // 탐색
                        isDone = true;                  // 다음 좌표가 있다면 true
                    }
                }
            }

            if (isDone) {       // 주변을 익게했다면 step 증가
                step++;
            } else {            // 익게한 좌표가 없으면 종료
                break;
            }
        }

        printResult(step, cnt, size);
    }

    private static boolean search(int idx,int[] pos) {
        boolean isDone =false;

        for(int direction=0; direction<4; direction++) {                // 4방향 검색
            int x = pos[0] + wayX[direction];
            int y = pos[1] + wayY[direction];

            if( x>=0 && x<maxX && y>=0 && y<maxY ) {                // out of index 방지
                if(map[y][x].equals("0") && !isVisited[y][x]) {       // 비어있는 공간이 아니면
                    movePos[idx].push(x,y);
                    cnt++;
                    isDone = true;
                    map[y][x] = String.valueOf(Integer.parseInt(map[pos[1]][pos[0]])+1);    // 인터넷보고 추가한 코드
                }
                isVisited[y][x] = true;
            }
        }
        return isDone;
    }

    private static void printResult(int step, int cnt, int ripeSize) {
        // 작업 후 변한 갯수가 토마토의 갯수와 같은지 아닌지 판단
        if(cnt ==0) {                                       // 한번도 익게 안했을 때
            System.out.println("0");
        } else if(maxX*maxY == cnt + blank + ripeSize) {      // 전체 박스와 (익게 만든 것 + 빈 공간 + 익어 있던 것 갯수) 가 같으면
//            System.out.println(step);                       // 몇 번에 걸쳐서 익게했는지 print

            // 인터넷보고 추가한 코드 시작
            int max =1;
            for(int y=0; y<maxY; y++) {
                for(int x=0; x<maxX; x++) {
                    if(map[y][x].equals("0")) {
                        System.out.println(-1);
                        return;
                    }
                    max = Math.max(max, Integer.parseInt(map[y][x]));
                }
            }
            System.out.println(max-1);
            //끝 나중에 지울것

        } else {
            System.out.println("-1");                       // 다르면 -1 print (모든 상자가 익게될 수 없을 경우)
        }
    }

    private static boolean[][] initBool() {
        boolean[][] result = new boolean[maxY][maxX];
        for(int y=0; y<maxY; y++) {
            for(int x=0; x<maxX; x++) {
                result[y][x] = false;
            }
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br=  new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();
        LightQueue queue = new LightQueue();

        maxX = Integer.parseInt(str.split(" ")[0]);
        maxY = Integer.parseInt(str.split(" ")[1]);

        map = new String[maxY][maxX];

        for(int y=0; y<maxY; y++) {             // 여기서 초반의 맵에서 1인 부분을 모두 queue 에 집어 넣고 동시 다발적으로
            // 탐색을 시작하는 코드가 중요!
            String[] strArr = br.readLine().split(" ");
            for(int x=0; x<maxX; x++) {
                String info = strArr[x];
                map[y][x] = info;
                if(info.equals("1")) {              // 익은 토마토 좌표 저장
                    queue.push(x,y);
                } else if(info.equals("-1")) {      // 안익은 토마토 갯수 저장
                    blank++;
                }
            }
        }

        bfs(queue);

        br.close();
    }

}

class LightQueue {
    private static final int MAX_QUEUE_SIZE = 128;
    int[] posX;
    int[] posY;
    int front;
    int rear;

    LightQueue() {
        posX = new int[MAX_QUEUE_SIZE];
        posY = new int[MAX_QUEUE_SIZE];
        front =0;
        rear =0;
    }

    void push(int x, int y) {
        if(rear == MAX_QUEUE_SIZE) {
            rear =0;
        }
        posX[rear] =x;
        posY[rear] =y;
        rear++;
    }

    int[] pop() {
        int[] result = new int[2];
        if(front == MAX_QUEUE_SIZE) {
            front =0;
        }

        result[0] = posX[front];
        result[1] = posY[front];
        front++;

        return result;
    }

    int[] peek() {
        int[] result = new int[2];
        result[0] =posX[front];
        result[1] = posY[front];

        return result;
    }

    int getSize() {
        return rear-front;
    }

    void init() {
        front =0;
        rear =0;
    }
}
/**              3197번 백조의 호수 (Swan Lake)
 *                호수의 크기 : 가로 R * 세로 C
 *                백조 두 마리, 만나는 길이 물이어야함 (빙판 X)
 *                얼어있는 구간이 있는데 물과 인접해 있으면 하루만에 녹음
 *                . : 물공간,   X : 빙판 공간, L : 백조가 있는 공간
 */

/* Hint
*  1. 하루동안 물 근처 빙하 녹이고 다음날로 진입
*  2. 백조 만날수 있는지 검증
*  1과 2 반복하며 만나면 끝내는
*  코드가 많더라...
*  근데 그럼 너무 오래걸리지 않을까
*/
package dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test_3197 {
    private static boolean[][] isVisited;
    private static String[][] map;
    private static Queue_3197 queue;
    private static Dot_3197 swanL, swanR;

    private static int[] wayX = {1, -1, 0, 0};
    private static int[] wayY = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String lakeInfo = br.readLine();

        int maxX = Integer.parseInt(lakeInfo.split(" ")[0]);
        int maxY = Integer.parseInt(lakeInfo.split(" ")[1]);
        map = new String[maxX][maxY];

        boolean isAdd = false;
        for(int x=0; x<maxX; x++) {
            String st = br.readLine();
            String[] stArr = st.split("");
            for(int y=0; y<maxY; y++) {
                map[x][y] = stArr[y];
                if(stArr[y].equals("L")) {
                    if(!isAdd) {
                        swanL = new Dot_3197(x, y);
                        isAdd = true;
                    } else {
                        swanR = new Dot_3197(x,y);
                    }
                }
            }
        }

        bfs(maxX, maxY);

        br.close();
    }

    private static void bfs(int maxX, int maxY) {
        queue = new Queue_3197();
        isVisited = new boolean[maxX][maxY];        // 방문기록 초기화

        int day =0;
        while(true) {
            if(search(maxX, maxY)) {    // 검색하면서 만났으면 끝내고
                break;
            }
//            for(int x=0; x<maxX; x++) {
//                for(int y=0; y<maxY; y++) {
//                    System.out.print(map[x][y]);
//                }
//                System.out.println();
//            }

            day++;                      // 아니면 날자 더하자
//            System.out.println(day+" 경과!");
        }

        System.out.println(day);
    }

    private static boolean search(int maxX, int maxY) {
        isVisited = new boolean[maxX][maxY];
        for(int x=0; x<maxX; x++) {
            for(int y=0; y<maxY; y++) {
                if(map[x][y].equals(".")) {     // 물이면
                    if(!isVisited[x][y]) {  // 들른적 없으면
                        queue.offer(new Dot_3197(x, y));        // queue에 넣어서 검사하자
                        if( meetEach(maxX, maxY) ) {       // 만나니?
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private static boolean meetEach(int maxX, int maxY) {
        boolean isLeft =false;          //좌 백조
        boolean isRight =false;         //우 백조

        while(!queue.isEmpty()) {
            Dot_3197 dot = queue.poll();
            isVisited[dot.x][dot.y] = true;             // 방문 체크

            if(swanVisit(dot, swanL)) {                 // 좌백조 체크
                isLeft = true;
            } else if(swanVisit(dot, swanR)) {          // 우백조 체크
                isRight = true;
            }

            meltIce(maxX, maxY, dot);   // 아니면 녹이면서 보자
        }

        if(isLeft && isRight) {     // 둘다 들렀으면
            return true;
        }

        return false;
    }

    private static boolean swanVisit(Dot_3197 dot, Dot_3197 swan) {    // 백조 들렀는지 체크
        return dot.x==swan.x && dot.y==swan.y ? true : false;
    }

    private static void meltIce(int maxX, int maxY, Dot_3197 dot) {
        for(int i=0; i<4; i++) {                // 4방향 체크
            int posX = dot.x + wayX[i];
            int posY = dot.y + wayY[i];

            if(isRange(posX, posY, maxX, maxY) && !isVisited[posX][posY]) {        //들른 적 없으면
                if (map[posX][posY].equals("X")) {      // 얘 얼음이면
                    map[posX][posY] = ".";              // 녹여주고
                } else {    // 길이거나 백조면
                    queue.offer(new Dot_3197(posX, posY));      // queue에 추가
                }
                isVisited[posX][posY] = true;                       // 방문체크
            }
        }
    }

    private static boolean isRange(int x, int y, int maxX, int maxY) {
        return x >=0 && x <maxX && y >=0 && y <maxY ? true : false ;
    }

}

class Dot_3197 {
    int x;
    int y;

    Dot_3197(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Queue_3197 {
    private static final int MAX_QUEUE_SIZE = 1501;
    private int front;
    private int rear;
    private Dot_3197[] dot;

    Queue_3197() {
        front =0;
        rear =0;
        dot = new Dot_3197[MAX_QUEUE_SIZE];
    }

    public void offer(Dot_3197 point) {
        if(rear == MAX_QUEUE_SIZE) {
            rear =0;
        }
        dot[rear++] = point;
    }

    public Dot_3197 poll() {
        if(front ==MAX_QUEUE_SIZE) {
            front =0;
        }

        return dot[front++];
    }

    public boolean isEmpty() {
        return front == rear ? true : false;
    }

}
/*
8 17
...XXXXXX..XX.XXX
....XXXXXXXXX.XXX
...XXXXXXXXXXXX..
..XXXXX.LXXXXXX..
.XXXXXX..XXXXXX..
XXXXXXX...XXXX...
..XXXXX...XXX....
....XXXXX.XXXL...
*/

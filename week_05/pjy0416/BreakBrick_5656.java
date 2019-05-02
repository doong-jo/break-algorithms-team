package sw_ea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BreakBrick_5656 {
    private static Brick[] brick;
    private static int[] results;
    private static final int MAX_REMAIN = 600;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCase = Integer.parseInt(br.readLine());
        brick = new Brick[testCase];
        results = initResult(testCase);

        // testCase 마다 입력 정보 저장.
        for(int i=0; i<testCase; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int n = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());

            brick[i] = new Brick(n,w,h);

            for(int y=0; y<h; y++) {
                StringTokenizer mapToken = new StringTokenizer(br.readLine(), " ");
                for(int x=0; x<w; x++) {
                    brick[i].saveMap(x, y, Integer.parseInt(mapToken.nextToken()));
                }
            }
        }

        solve(testCase);
        printResult(testCase);

        br.close();
    }

    private static int[] initResult(int testCase) {
        int[] result = new int[testCase];
        for(int i=0; i<testCase; i++) {
            result[i] = MAX_REMAIN;
        }
        return result;
    }

    private static void printResult(final int testCase) {
        for(int i=0; i<testCase; i++) {
            System.out.printf("#%d %d", i, results[i]);
            System.out.println();
        }
    }

    private static void solve(final int testCase) {
        for(int i=0; i<testCase; i++) { // testCase 만큼 풀자
            // i 번째 테스트 케이스에 대해서 벽돌 깨고 찾기...
            findBestCase(i, 0,0, brick[i]);       // 0,0 부터 탐색
        }
    }

    private static void findBestCase(int idx, int x, int bead, Brick major) {
        Brick copy = cloneBrick(major);

        if(bead == major.n) {
            results[idx] = Math.min(copy.remainBrick(), results[idx]);
            System.out.println(idx + " \t" + copy.remainBrick());
            return;
        }

        //공 n번 튕기기
        for (int posX = x; posX < copy.w; posX++) {
            // 좌표에서 부시고, 안부시고가 중요함

            //부실 때
            int y = firstY(copy, posX, 0);
            copy.breakAround(posX, y);
            copy.sortingMap();
            findBestCase(idx, posX, bead + 1, copy);    // posX에서 부시고 다음으로 넘어감
        }

    }

    private static int firstY(Brick brick, int x, int y) {
        if(brick.isRange(x,y) && brick.map[y][x] !=0) {
            return y;
        }
        if(brick.isRange(x,y+1)) {
            firstY(brick, x, y + 1);
        }
        return 0;
    }

    private static Brick cloneBrick(Brick brick) {
        Brick copy = new Brick(brick.n, brick.w, brick.h);

        for(int y=0; y<brick.h; y++) {   // map 복사
            System.arraycopy(brick.map[y], 0, copy.map[y], 0, copy.w);
            copy.visitInit();
        }

        return copy;
    }
}

class Brick {
    int n;
    int w;
    int h;
    int[][] map;
    boolean[][] isVisited;

    Brick(int n, int w, int h) {
        this.n = n;
        this.w = w;
        this.h = h;
        this.map = new int[h][w];
        isVisited = new boolean[h][w];
    }

    void saveMap(int x, int y, int value) {
        map[y][x] = value;
    }

    // 주변 벽돌 깨기
    void breakAround(int x, int y) {
        int value = map[y][x] -1;
        map[y][x] =0;
        for(int addPos = -value; addPos<=value; addPos++) {
            if(addPos!=0) {
                int posY = y+addPos;
                int posX = x+addPos;

                if(isRange(x, posY) && !this.isVisited[posY][x]) {  // y축으로 벽돌 깨기
                    isVisited[posY][x] = true;
                    breakAround(x, posY);     // 깨지는 곳에서도 부셔야함
                    this.map[posY][x] =0;
                }
                if(isRange(posX, y) && !this.isVisited[y][posX]) {  // x축으로 벽돌 깨기
                    isVisited[y][posX] = true;
                    breakAround(posX, y);     // 깨지는 곳에서도 부셔야함
                    this.map[y][posX] =0;
                }
            }
        }
    }

    // 벽돌 깬 후, 위에 있는 벽돌 내리기
    void sortingMap() {
        for (int x = 0; x < w; x++) {     // 맵의 가로축을 돌면서
            for (int y = 0; y < h; y++) { // 맨위에 까지 맵 탐색하면서
                int posY = y+1;
                if(isRange(x, posY) && isVisited[y][x] && map[posY][x] ==0) {    // out of idx 방지
                    this.map[posY][x] = this.map[y][x];
                    this.map[y][x] = 0;
                }
            }
        }
    }

    int remainBrick() {
        int cnt =0;
        for(int y=0; y<h; y++) {
            for(int x=0; x<w; x++) {
                if(this.map[y][x] !=0) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    // out of range 방지
    boolean isRange(int posX, int posY) {
        return posX >=0 && posX <w && posY >=0 && posY <h ? true : false;
    }

    void visitInit() {
        this.isVisited = new boolean[this.h][this.w];
    }
}
/*
Test Case
5
3 10 10
0 0 0 0 0 0 0 0 0 0
1 0 1 0 1 0 0 0 0 0
1 0 3 0 1 1 0 0 0 1
1 1 1 0 1 2 0 0 0 9
1 1 4 0 1 1 0 0 1 1
1 1 4 1 1 1 2 1 1 1
1 1 5 1 1 1 1 2 1 1
1 1 6 1 1 1 1 1 2 1
1 1 1 1 1 1 1 1 1 5
1 1 7 1 1 1 1 1 1 1
2 9 10
0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0
0 1 0 0 0 0 0 0 0
0 1 0 0 0 0 0 0 0
1 1 0 0 1 0 0 0 0
1 1 0 1 1 1 0 1 0
1 1 0 1 1 1 0 1 0
1 1 1 1 1 1 1 1 0
1 1 3 1 6 1 1 1 1
1 1 1 1 1 1 1 1 1
3 6 7
1 1 0 0 0 0
1 1 0 0 1 0
1 1 0 0 4 0
4 1 0 0 1 0
1 5 1 0 1 6
1 2 8 1 1 6
1 1 1 9 2 1
4 4 15
0 0 0 0
0 0 0 0
0 0 0 0
1 0 0 0
1 0 0 0
1 0 0 0
1 0 0 0
1 0 5 0
1 1 1 0
1 1 1 9
1 1 1 1
1 6 1 2
1 1 1 5
1 1 1 1
2 1 1 2
4 12 15
9 9 9 9 9 9 9 9 9 9 9 9
9 9 9 9 9 9 9 9 9 9 9 9
9 9 9 9 9 9 9 9 9 9 9 9
9 9 9 9 9 9 9 9 9 9 9 9
9 9 9 9 9 9 9 9 9 9 9 9
9 9 9 9 9 9 9 9 9 9 9 9
9 9 9 9 9 9 9 9 9 9 9 9
9 9 9 9 9 9 9 9 9 9 9 9
9 9 9 9 9 9 9 9 9 9 9 9
9 9 9 9 9 9 9 9 9 9 9 9
9 9 9 9 9 9 9 9 9 9 9 9
9 9 9 9 9 9 9 9 9 9 9 9
9 9 9 9 9 9 9 9 9 9 9 9
9 9 9 9 9 9 9 9 9 9 9 9
9 9 9 9 9 9 9 9 9 9 9 9
 */
package dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test_2178 {
    private static int n,m;
    private static String[] map;
    private static boolean[][] isVisited;

    //BFS 문제다..
    private static int minRoot() {
        isVisited = initBool();
        int cnt =0;
        int[] queueX = new int[n*m];        // queue의 모든 좌표당 4개의 좌표가 들어갈 수 있다는 가정하에 최대가 40000이 됨
        int[] queueY = new int[n*m];        // 따라서 n*m개의 갯수만큼 부여하는 것이 메모리가 좀더 효율적이라는 판단
        int[] wayX = {0, 1, -1, 0};       // 남 동 서 북    Array로 값 저장
        int[] wayY = {1, 0, 0, -1};       // 남 동 서 북

        int first =0, size =0; // queue 에 쓰일 변수 초기화

        queueX[size] =0;        // queue에 처음 좌표인 0,0을 넣어줌
        queueY[size] =0;
        size++;                 // queue 사이즈 증가
        isVisited[first][first] = true; // 시작지점이니까 방문 했던것으로 기록

        while(true) {
            if(isVisited[n-1][m-1]) {   // 마지막 좌표를 갔다면
                cnt++;                  // 카운트 증가
                return cnt;             // 리턴
            }

            int addSize =0;             // 큐에 몇개가 들어갔는지 판단하기 위한 변수
//            System.out.println(" ");
//            System.out.println(first + " \t" + size);
            for(int idx=first; idx<size; idx++) {   // queue에서 꺼내지 않은 index부터 size까지 꺼냄. <- 모든 경우 탐색
                int nx = queueX[idx];
                int ny = queueY[idx];
//                System.out.println(idx+"번 째 \t" + ny + "," + nx + "가 큐에 있음");
                for (int i = 0; i < 4; i++) {   // 동서남북 검증
                    int y = ny + wayY[i];   // 동서남북에 맞게
                    int x = nx + wayX[i];   // 좌표를 증가 or 감소

//                    System.out.println(ny +"," +nx +" 꺼내서 씀\t" + y + "," +x + "로 변환");

                    if (y <= n - 1 && y >= 0 && x >= 0 && x <= m - 1) {             // out of index 방지
                        if (!isVisited[y][x] && map[y].charAt(x) == '1') {          // 방문한 적이 없고 길이 있으면 go!
//                            System.out.println(y + "," + x + "가자");
                            queueX[size+addSize] = x;       //  queue 에 좌표를 저장
                            queueY[size+addSize] = y;
                            addSize++;                      // queue 에 넣을때마다 증가
                            isVisited[y][x] = true;         // 방문기록 남김
                        }
                    }
                }
                first++;                                    // 마지막으로 꺼낸 index 값 증가
            }

            size += addSize;                                // queue 전체 사이즈를 넣은 갯수만큼 증가
            cnt++;                                          // step count
        }
    }

    private static boolean[][] initBool() {
        boolean[][] isVisited = new boolean[n][m];

        for(int y=0; y<n; y++) {
            for(int x=0; x<m; x++) {
                isVisited[y][x] = false;
            }
        }
        return isVisited;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String inputStr = br.readLine();

        n = Integer.parseInt(inputStr.split(" ")[0]);
        m = Integer.parseInt(inputStr.split(" ")[1]);

        map = new String[n];

        for(int i=0; i<n; i++) {
            map[i] = br.readLine();
        }

        System.out.println(minRoot());

        br.close();
    }
}

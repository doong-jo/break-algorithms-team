package me.uyoo.GroupPractice.Week_03_Graph_Bfs_Dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Dot_2573 {
    public int y, x;

    public Dot_2573(int y, int x) {
        this.y = y;
        this.x = x;
    }
}

public class Iceberg_2573 {
    /*
     * 기존 빙하의 구역 개수를 카운팅(cnt)하면서 녹인 상태로 변환, 이후 year++
     * */
    static int n, m;
    static int[][] iceBergs;
    static int[][] adjSea;  //인접한 바다의 개수 카운트
    static boolean[][] checked;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    static int year = 0;

    private static void doBfs(int y, int x) {
        checked[y][x] = true;
        Queue<Dot_2573> queue = new LinkedList<>();
        queue.offer(new Dot_2573(y, x));

        Dot_2573 q;
        while (!queue.isEmpty()){
            q = queue.poll();

            for(int i=0; i<4; i++){
                int adj_Y = q.y + dy[i];
                int adj_X = q.x + dx[i];

                if(isRanged(adj_Y, adj_X)){
                    //인접한 곳이 0이면 현재 빙하가 녹을만큼의 개수 카운트
                    if(!checked[adj_Y][adj_X] && iceBergs[adj_Y][adj_X] == 0){
                        adjSea[q.y][q.x]++;
                    }
                    //인접한 곳에 값이 존재하면 기존 bfs처럼 진행
                    else if(!checked[adj_Y][adj_X] && iceBergs[adj_Y][adj_X] != 0 ){
                        checked[adj_Y][adj_X] = true;
                        queue.offer(new Dot_2573(adj_Y, adj_X));
                    }
                }
            }
        }
    }

    private static int findFieldCnt() {
        checked = new boolean[n][m];

        int cnt = 0;
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(!checked[i][j] && iceBergs[i][j] != 0){
                    doBfs(i, j);
                    cnt++;
                }
            }
        }
        return cnt;
    }

    private static void melting() {
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                iceBergs[i][j] -= adjSea[i][j];
                if(iceBergs[i][j] <= 0) iceBergs[i][j] = 0;

                adjSea[i][j] = 0;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String size = br.readLine();
        st = new StringTokenizer(size, " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        iceBergs = new int[n][m];
        adjSea = new int[n][m];

        for(int i=0; i<n; i++){
            String input = br.readLine();
            st = new StringTokenizer(input, " ");
            for(int j=0; j<m; j++){
                iceBergs[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int count = 0;
        while (true){
            //구역 카운트 -> 녹이기 -> 1년
            count = findFieldCnt();
            if(count >= 2){
                System.out.println(year);
                return;
            } else if( count == 0){
                System.out.println(0);
                return;
            }

            melting();
            year++;
        }
    }

    private static boolean isRanged(int adj_y, int adj_x) {
        if(adj_y >= 0 && adj_y < n && adj_x >= 0 && adj_x < m) return true;
        return false;
    }
}
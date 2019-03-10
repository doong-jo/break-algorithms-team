//package me.uyoo.GroupPractice.Week_03_Graph_Bfs_Dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Dot_2573 {
    public int y;
    public int x;
    public int depth;

    public Dot_2573(int y, int x, int depth) {
        this.y = y;
        this.x = x;
        this.depth = depth;
    }
}

public class Iceberg_2573 {
    /*
     * 기존 빙하의 구역 개수를 카운팅(cnt)하면서 녹인 상태로 변환, 이후 year++
     * 해당 로직에서 cnt는 기존 빙하를 기준으로 하기 때문에 year가 한번 더 카운트 됨 -> year-1 적용
     * */
    static int n, m;
    static int[][] iceBergs;
    static boolean[][] checked;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    static int year = 0;

    private static void meltIceberg(int y, int x) {
        Queue<Dot_2573> queue = new LinkedList<>();

        checked[y][x] = true;
        queue.offer(new Dot_2573(y, x, 0));

        Dot_2573 q;
        while (!queue.isEmpty()){
            q = queue.poll();

            for(int i=0; i<4; i++){
                int next_Y = q.y + dy[i];
                int next_X = q.x + dx[i];

                if(next_Y >=0 && next_Y < n || next_X >=0 || next_X < m){

                    //인접한 곳이 0이면 현재 빙하의 값을 하나씩 줄임
                    if(!checked[next_Y][next_X] && iceBergs[next_Y][next_X] == 0){
                        iceBergs[q.y][q.x] -= 1;
                        if(iceBergs[q.y][q.x] <= 0) iceBergs[q.y][q.x] = 0;
                    }
                    //인접한 곳에 값이 존재하면 기존 bfs처럼 진행
                    else if(!checked[next_Y][next_X] && iceBergs[next_Y][next_X] != 0 ){
                        checked[next_Y][next_X] = true;
                        queue.offer(new Dot_2573(next_Y, next_X, q.depth+1));
                    }
                }
            }
        }
    }

    private static int findIceBergField() {
        checked = new boolean[n][m];

        int fieldCnt = 0;
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(!checked[i][j] && iceBergs[i][j] != 0){
                    meltIceberg(i, j);
                    //나뉜 구역의 개수 카운팅
                    fieldCnt++;
                }
            }
        }
        return fieldCnt;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String size = br.readLine();
        st = new StringTokenizer(size, " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        iceBergs = new int[n][m];

        for(int i=0; i<n; i++){
            String input = br.readLine();
            st = new StringTokenizer(input, " ");
            for(int j=0; j<m; j++){
                iceBergs[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int cnt = 0;
        while(true){
            //빙하가 모두 녹아있는 상태라면 cnt = 0을 반환
            //나뉜 구역 카운팅 -> 녹이기 -> 1년 경과 -> 나뉜 구역 카운팅...
            cnt = findIceBergField();
            year++;

            //cnt는 녹이기 전의 구역 개수를 카운팅하기 때문에 year-1을 해줘야함
            if(cnt >= 2){
                System.out.println(year-1);
                return;
            } else if(cnt == 0){
                System.out.println(0);
                return;
            }
        }
    }
}
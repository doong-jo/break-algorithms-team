//package me.uyoo.GroupPractice.Week_03_Graph_Bfs_Dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Dot_3197 implements Comparable<Dot_3197>{
    public int y, x;
    public int day;

    public Dot_3197(int y, int x, int day) {
        this.y = y;
        this.x = x;
        this.day = day;
    }

    //우선순위 큐에서 사용되기 위해 오름차순으로 정렬
    @Override
    public int compareTo(Dot_3197 o) {
        return this.day - o.day;
    }
}

public class SwanLake_3197 {
    /*
     * '.'은 물 공간, 'X'는 빙판 공간, 'L'은 백조
     * 기존 호수는 day = 0, 이후 호수의 테두리(빙판)를 녹이고 녹은 부분을 day = 1 방식으로 마킹
     * 우선순위 큐를 통해 백조의 위치부터 시작해서 Bfs 진행 -> 결국 도착했을 때의 day 값이 곧 소요된 날이기 때문
     * */

    static int r, c;
    static String[][] lakes;
    static int[][] lakes_day;   //각각 호수가 된 날짜를 마킹
    static boolean[][] checked;

    static Dot_3197[] swans = new Dot_3197[2];    //백조의 위치를 담은 배열
    static final int SWAN_SP = 0;
    static final int SWAN_DP = 1;
    static Queue<Dot_3197> queue;

    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    private static void meltIceberg() {
        checked = new boolean[r][c];

        Dot_3197 q;
        while (!queue.isEmpty()){
            int queueSize = queue.size();

            //하루씩 진행되는 로직
            for(int d=0; d<queueSize; d++){
                q = queue.poll();
                checked[q.y][q.x] = true;
                lakes_day[q.y][q.x] = q.day;    //녹은 날이 언제인지 명시

                for(int i=0; i<4; i++){
                    int adj_Y = q.y + dy[i];
                    int adj_X = q.x + dx[i];

                    if(adj_Y >= 0 && adj_Y < r && adj_X >= 0 && adj_X < c){
                        //인접한 곳이 빙판이면 녹이고 큐에 추가 (큐 추가 시 하루 뒤이기 때문에 기존 day에서 + 1
                        if(!checked[adj_Y][adj_X] && lakes[adj_Y][adj_X].equals("X")){
                            lakes[adj_Y][adj_X] = ".";
                            queue.offer(new Dot_3197(adj_Y, adj_X, q.day + 1));
                        }
                    }
                }
            }
        }
        queue = null;
    }

    private static int findSwan() {
        checked = new boolean[r][c];
        PriorityQueue<Dot_3197> priorityQueue = new PriorityQueue<>();

        checked[swans[SWAN_SP].y][swans[SWAN_SP].x] = true;
        priorityQueue.offer(swans[SWAN_SP]);

        Dot_3197 q;
        while (!priorityQueue.isEmpty()){
            q = priorityQueue.poll();

            if(q.y == swans[SWAN_DP].y && q.x == swans[SWAN_DP].x) return q.day;

            for(int i=0; i<4; i++){
                int adj_Y = q.y + dy[i];
                int adj_X = q.x + dx[i];

                if(adj_Y >= 0 && adj_Y < r && adj_X >= 0 && adj_X < c && !checked[adj_Y][adj_X]){
                    checked[adj_Y][adj_X] = true;
                    priorityQueue.offer(new Dot_3197(adj_Y, adj_X, Math.max(q.day, lakes_day[adj_Y][adj_X])));
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String size = br.readLine();
        st = new StringTokenizer(size, " ");
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        lakes = new String[r][c];
        lakes_day = new int[r][c];

        queue = new LinkedList<>();

        int swanIdx = SWAN_SP;
        for(int i=0; i<r; i++){
            String input = br.readLine();
            for(int j=0; j<c; j++){
                lakes[i][j] = input.substring(j, j+1);

                //백조들의 위치를 담음
                if(lakes[i][j].equals("L")){
                    swans[swanIdx++] = new Dot_3197(i, j, 0);
                }

                //백조 or 호수이면 해당 위치를 미리 큐에 저장
                if(!lakes[i][j].equals("X")){
                    queue.offer(new Dot_3197(i, j, 0));
                }
            }
        }

        //호수에 인접한 빙판(테두리)을 Bfs를 통해 녹이는 로직
        meltIceberg();

        //각각의 day 값을 부여한 뒤 백조의 시작 위치에서부터 Bfs 진행
        int result = findSwan();
        System.out.println(result);
    }
}
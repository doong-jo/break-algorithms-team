//package me.uyoo.GroupPractice.Week_03_Graph_Bfs_Dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Dot_2468{
    public int y;
    public int x;
    public int depth;

    public Dot_2468(int y, int x, int depth) {
        this.y = y;
        this.x = x;
        this.depth = depth;
    }
}

public class SafeZone_2468 {
    static int n;
    static int[][] zone;
    static boolean[][] check;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    private static void countSafeZone(int y, int x, int h) {
        Queue<Dot_2468> queue = new LinkedList<>();
        check[y][x] = true;
        queue.offer(new Dot_2468(y, x, 0));

        Dot_2468 q;
        while (!queue.isEmpty()){
            q = queue.poll();
            for(int i=0; i<4; i++){
                int next_Y = q.y + dy[i];
                int next_X = q.x + dx[i];

                if(next_Y >= 0 && next_Y < n && next_X >= 0 && next_X < n) {
                    if(!check[next_Y][next_X] && zone[next_Y][next_X] > h){
                        check[next_Y][next_X] = true;
                        queue.offer(new Dot_2468(next_Y, next_X, q.depth + 1));
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        zone = new int[n][n];

        //입력된 배열 내에서 최대 높이 구하기
        int maxHeight = 0;
        for(int i=0; i<n; i++){
            String nInput = br.readLine();
            st = new StringTokenizer(nInput, " ");

            for(int j=0; j<n; j++){
                zone[i][j] = Integer.parseInt(st.nextToken());
                maxHeight = Math.max(maxHeight, zone[i][j]);
            }
        }

        //최대높이까지 각 경우의 수 고려
        int h = 0;  //장마기간에 비가 안올 수도 있음
        int cnt = 0;
        int maxCnt = 0;
        while(h <= maxHeight){
            check = new boolean[n][n];
            for(int i=0; i<n; i++){
                for(int j=0; j<n; j++){
                    if(!check[i][j] && zone[i][j] > h){
                        countSafeZone(i, j, h);
                        cnt++;  //영역 구간에 대한 개수 카운트
                    }
                }
            }
            maxCnt = Math.max(maxCnt, cnt);
            cnt = 0;

            h++;
        }

        System.out.println(maxCnt);
    }
}
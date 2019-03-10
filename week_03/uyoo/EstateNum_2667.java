//package me.uyoo.GroupPractice.Week_03_Graph_Bfs_Dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Dot_2667{
    public int y;
    public int x;
    public int depth;

    public Dot_2667(int y, int x, int depth) {
        this.y = y;
        this.x = x;
        this.depth = depth;
    }
}

public class EstateNum_2667 {
    static int n;
    static int[][] estates;     //단지
    static boolean[][] checked;
    static int estateNum = 0;   //총 단지 수를 위한 카운트
    static int cnt = 0;         //구간에 존재하는 단지 별 카운트

    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    private static void postEstateNum(int y, int x) {
        Queue<Dot_2667> queue = new LinkedList<>();
        checked[y][x] = true;
        queue.offer(new Dot_2667(y, x, 0));
        cnt++;

        Dot_2667 q;
        while (!queue.isEmpty()){
            q = queue.poll();

            for(int i=0; i<4; i++){
                int next_Y = q.y + dy[i];
                int next_X = q.x + dx[i];

                if(next_Y >= 0 && next_Y < n && next_X >= 0 && next_X < n){
                    if(!checked[next_Y][next_X] && estates[next_Y][next_X] != 0){
                        checked[next_Y][next_X] = true;
                        queue.offer(new Dot_2667(next_Y, next_X, q.depth + 1));
                        cnt++;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        estates = new int[n][n];
        checked = new boolean[n][n];
        ArrayList<Integer> estateNums = new ArrayList<>();

        for(int i=0; i<n; i++){
            String input = br.readLine();
            for(int j=0; j<n; j++){
                estates[i][j] = Integer.parseInt(input.substring(j, j+1));
            }
        }

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(!checked[i][j] && estates[i][j] != 0){
                    postEstateNum(i, j);
                    estateNums.add(cnt);
                    cnt = 0;
                    estateNum++;
                }
            }
        }

        //총 단지수
        System.out.println(estateNum);

        //단지 별 집 개수 오름차순
        Collections.sort(estateNums);
        for(int res : estateNums)
            System.out.println(res);
    }
}
//package me.uyoo.GroupPractice.Week_02_Graph_Bfs_Dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//배열에 대한 index 정보
class Dots{
    public int x;
    public int y;

    public Dots(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Tomato7576 {

    static int n, m;
    static boolean[][] checked;
    static int[][] matrix;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    private static void deterMinimumDate() {
        int max = 0;
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                //아예 비어있던 토마토(-1)은 findRipeTomato() 과정을 거치면 0이되기 때문에 ex. -1 + 1 = 0
                if(matrix[i][j] == 0){
                    System.out.println(-1);
                    return;
                }

                max = Math.max(max, matrix[i][j]);
            }
        }
        //시작일이 1이기 때문에 누적할 때 +1이 되어있으므로
        System.out.println(max-1);
    }

    private static void findRipeTomato() {
        Queue<Dots> queue = new LinkedList<>();
        checked = new boolean[n][m];

        //익은 토마토(1)를 큐에 먼저 넣고 maze처럼 진행
        for(int i=0; i<n; i++)
            for(int j=0; j<m; j++)
                if(matrix[i][j] == 1)
                    queue.offer(new Dots(i, j));

        Dots q;
        while (!queue.isEmpty()){
            q = queue.poll();

            for(int i=0; i<4; i++){
                int next_X = q.x + dx[i];
                int next_Y = q.y + dy[i];

                if(next_X < 0 || next_X >= n || next_Y < 0 || next_Y >= m) continue;

                if(!checked[next_X][next_Y] && matrix[next_X][next_Y] == 0){
                    checked[next_X][next_Y] = true;
                    queue.offer(new Dots(next_X, next_Y));

                    //현재 진행중인 노드와 인접 노드의 누적 합
                    matrix[next_X][next_Y] = matrix[q.x][q.y] + 1;
                }
            }
        }
        deterMinimumDate();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        StringTokenizer st = new StringTokenizer(line, " ");

        m = Integer.parseInt(st.nextToken());   //열
        n = Integer.parseInt(st.nextToken());   //행
        matrix = new int[n][m];

        for(int i=0; i<n; i++){
            String nInput = br.readLine();
            st = new StringTokenizer(nInput, " ");

            for(int j=0; j<m; j++){
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //using bfs algorithm
        findRipeTomato();
    }
}
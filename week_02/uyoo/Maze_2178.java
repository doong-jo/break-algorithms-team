//package me.uyoo.GroupPractice.Week_02_Graph_Bfs_Dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//배열의 index 관리
class Dot{
    public int x;
    public int y;

    public Dot(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Maze_2178 {
    /*
     * Bfs
     * (1,1) 시작점
     * 큐 생성 -> 시작점 마킹 -> 시작점 큐 삽입 -> { 큐 꺼냄 -> 인접 노드 탐색 -> 인접 배열 마킹 -> 큐에 삽입 -> 인접배열 = 인접배열 + 1 (이전 값에 누적되는 개념) }
     * 시계방향으로 인접노드를 탐색
     * 누적하면서 최소 카운트 측정
     * */

    static int n, m;
    static boolean[][] checked;
    static int[][] matrix;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    private static void findMaze(int sp_X, int sp_Y) {
        checked = new boolean[n+1][m+1];

        Queue<Dot> queue = new LinkedList<>();
        checked[sp_X][sp_Y] = true;
        queue.offer(new Dot(sp_X, sp_Y));

        Dot q;
        while (!queue.isEmpty()){
            q = queue.poll();

            //시계 방향으로 인접 노드 탐색
            for(int i=0; i<4; i++){
                int next_X = q.x + dx[i];
                int next_Y = q.y + dy[i];

                if(next_X <= 0 || next_X > n || next_Y <= 0 || next_Y > m ){
                    continue;
                }

                if(!checked[next_X][next_Y] && matrix[next_X][next_Y] == 1){
                    checked[next_X][next_Y] = true;
                    queue.offer(new Dot(next_X, next_Y));

                    //현재 진행중인 노드와 인접 노드의 누적 합
                    matrix[next_X][next_Y] = matrix[q.x][q.y] + 1;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        StringTokenizer st = new StringTokenizer(line, " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        matrix = new int[n+1][m+1];

        for(int i=1; i<n+1; i++){
            String nInput = br.readLine();
            for(int j=1; j<m+1; j++){
                matrix[i][j] = Integer.parseInt(nInput.substring(j-1, j));
            }
        }

        // using bfs algorithm
        int sp_X = 1;
        int sp_Y = 1;
        findMaze(sp_X, sp_Y);

        //종점의 누적 값을 출력
        System.out.println(matrix[n][m]);

    }
}
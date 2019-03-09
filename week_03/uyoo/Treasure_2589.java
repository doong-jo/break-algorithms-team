//package me.uyoo.GroupPractice.Week_03_Graph_Bfs_Dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Dot_2589{
    public int y;
    public int x;
    public int depth;

    public Dot_2589(int y, int x, int depth) {
        this.y = y;
        this.x = x;
        this.depth = depth;
    }
}

public class Treasure_2589 {
    /*
     * 전체를 돌면서 -> 'L'인 부분을 찾고 -> 해당 위치에서의 bfs 수행 -> max값(depth) 얻고 -> 최대값 구함
     * */

    static int r, c;
    static String[][] map;
    static boolean[][] checked;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0 ,-1};

    private static int findTreasure(int y, int x) {
        checked = new boolean[r][c];
        Queue<Dot_2589> queue = new LinkedList<>();
        checked[y][x] = true;
        queue.offer(new Dot_2589(y, x, 0));

        Dot_2589 q;
        int depth = 0;
        while (!queue.isEmpty()){
            q = queue.poll();

            for(int i=0; i<4; i++){
                int next_Y = q.y + dy[i];
                int next_X = q.x + dx[i];

                if(next_Y >= 0 && next_Y < r && next_X >= 0 && next_X < c){
                    if(!checked[next_Y][next_X] && map[next_Y][next_X].equals("L")){
                        checked[next_Y][next_X] = true;
                        queue.offer(new Dot_2589(next_Y, next_X, q.depth + 1));

                        depth = q.depth + 1;
                    }
                }
            }
        }

        return depth;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        String size = br.readLine();
        st = new StringTokenizer(size, " ");

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        map = new String[r][c];

        for(int i=0; i<r; i++){
            String input = br.readLine();
            for(int j=0; j<c; j++){
                map[i][j] = input.substring(j, j+1);
            }
        }

        int max = 0;
        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                if(map[i][j].equals("L"))
                    max = Math.max(max, findTreasure(i, j));
            }
        }

        System.out.println(max);
    }
}
//package me.uyoo.GroupPractice.Week_02_Graph_Bfs_Dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//배열의 index 관리
class Dot_3184{
    public int x;
    public int y;

    public Dot_3184(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Sheep_3184 {
    /*
     * '.': 빈 필드, '#': 울타리, 'o': 양, 'v': 늑대
     * 울타리(#)을 벗어날 수 x

     * Bfs -> 울타리 내의 양과 늑대의 수를 카운팅
     * fight -> 영역 내의 양과 늑대 수 비교
     * */

    static int r, c;
    static String[][] matrix;
    static boolean[][] checked;
    static int cnt_Sheep = 0, cnt_Wolf = 0;
    static int totalSheep = 0, totalWolf = 0;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    private static void findSheep_Wolf(int x, int y) {
        checked[x][y] = true;
        Queue<Dot_3184> queue = new LinkedList<>();
        queue.offer(new Dot_3184(x, y));

        //시작 배열에 바로 양 or 늑대가 있을 수 있기 때문에 사전에 카운팅
        if(matrix[x][y].equals("o"))  cnt_Sheep++;
        else if(matrix[x][y].equals("v")) cnt_Wolf++;

        Dot_3184 q;
        while(!queue.isEmpty()){
            q = queue.poll();

            //시계 방향으로 인접한 배열을 찾고 울타리 안에 있는 필드를 카운팅
            for(int i=0; i<4; i++){
                int next_X = q.x + dx[i];
                int next_Y = q.y + dy[i];

                if(next_X < 0 || next_X >= r || next_Y < 0 || next_Y >= c) continue;

                if(!checked[next_X][next_Y] && !matrix[next_X][next_Y].equals("#")) {
                    checked[next_X][next_Y] = true;
                    queue.offer(new Dot_3184(next_X, next_Y));   //빈 필드(.)도 인접하기 때문에 큐로 넣어줘야 계속해서 인접한 배열을 탐색할 수 있음

                    if (matrix[next_X][next_Y].equals("o")) cnt_Sheep++;
                    else if (matrix[next_X][next_Y].equals("v")) cnt_Wolf++;
                }
            }
        }
    }

    private static void fight() {
        if(cnt_Sheep > cnt_Wolf) totalSheep += cnt_Sheep;
        else totalWolf += cnt_Wolf;

        cnt_Sheep = cnt_Wolf = 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        String nInput = br.readLine();
        st = new StringTokenizer(nInput, " ");

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        matrix = new String[r][c];
        checked = new boolean[r][c];

        for(int i=0; i<r; i++){
            String line = br.readLine();
            for(int j=0; j<c; j++){
                matrix[i][j] = line.substring(j, j+1);
            }
        }

        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                if(!checked[i][j] && !matrix[i][j].equals("#")){
                    //using Bfs algorithm
                    findSheep_Wolf(i, j);

                    //울타리 내의 카운팅이 끝나면 영역 내의 양과 늑대 수 비교
                    fight();
                }
            }
        }

        System.out.println(totalSheep + " " + totalWolf);
    }
}
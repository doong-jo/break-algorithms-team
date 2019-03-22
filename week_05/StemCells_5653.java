import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
* 1시간 동안
*    1) 배양 가능한 상태(활성화)라면 상하좌우 배양 및 생명력 기간 동안 활성화 상태 체크
*    2) 활성화 상태가 아니라면 활성화 상태로 만듦
*
* time -= 1을 하는 이유
*   1) 활성화 된 시점(time = 0)부터 생명력 기간동안 활성화 상태 유지를 확인
*       ex. |-2| = 2 이면 die = true(종료) 전환, 큐에 삽입 x
*
*   2) 활성화 상태로 만들기 위해 -1씩 차감
* */


class Dot_5653 implements Comparable<Dot_5653>{
    public int y, x;
    public final int LIFE;  //생명력
    public int time;        //-1씩 차감시킬 수 있는 생명력(유동적)
    public boolean die;     //종료 설정

    public Dot_5653(int y, int x, int life, int time ,boolean die) {
        this.y = y;
        this.x = x;
        LIFE = life;
        this.time = time;
        this.die = die;
    }

    @Override
    public int compareTo(Dot_5653 o) {
        return o.LIFE - this.LIFE;
    }
}

public class StemCells_5653 {
    static int n, m, k;
    static int[][] cells;
    static boolean[][] checked;

    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    static Queue<Dot_5653> queue;
    static int hour = 0;

    private static void doBfs() {
        hour = 0;
        Dot_5653 q;
        while (!queue.isEmpty()){
            if(hour == k){
                return;
            }

            //우선순위 부여
            Collections.sort((List)queue);

            int size = queue.size();
            for(int a=0; a<size; a++){
                q = queue.poll();

                //활성화인 세포 & !종료 인 경우
                if(q.time <= 0){
                    q.time -= 1;

                    //상하좌우 배양
                    for(int i=0; i<4; i++){
                        int adj_Y = q.y + dy[i];
                        int adj_X = q.x + dx[i];

                        if(isRanged(adj_Y, adj_X) && !checked[adj_Y][adj_X]){
                            cells[adj_Y][adj_X] = q.LIFE;

                            checked[adj_Y][adj_X] = true;
                            queue.offer(new Dot_5653(adj_Y, adj_X, cells[adj_Y][adj_X], cells[adj_Y][adj_X] ,false));
                        }
                    }

                    //활성화 시점부터 생명력이 끝나면 죽은 상태로 전환
                    if(Math.abs(q.time) == q.LIFE){
                        q.die = true;
                    } else {
                        queue.offer(q);
                    }
                }

                //비활성화 상태인 경우 -1씩 차감
                else if(q.time > 0){
                    q.time -= 1;
                    queue.offer(q);
                }
            }
            hour++;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int testCase = Integer.parseInt(br.readLine());
        int t = 0;
        while (t < testCase) {
            String config = br.readLine();
            st = new StringTokenizer(config, " ");

            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            cells = new int[n+k][m+k];
            checked = new boolean[n+k][m+k];
            queue = new LinkedList<>();

            //초기 값 넣어줄 때 기존 값이 0이면 마킹
            for (int i = k/2; i < n+(k/2); i++) {
                String input = br.readLine();
                st = new StringTokenizer(input, " ");
                for (int j = k/2; j < m+(k/2); j++) {
                    cells[i][j] = Integer.parseInt(st.nextToken());
                    if(cells[i][j] > 0) {
                        checked[i][j] = true;
                        queue.offer(new Dot_5653(i, j, cells[i][j], cells[i][j] ,false));
                    }
                }
            }

            doBfs();

            //남아있는 큐에는 비활성 + 활성만 존재하므로
            System.out.println("#" + (t+1) + " " + queue.size());
            t++;
        }
    }

    private static boolean isRanged(int y, int x) {
        if(y >= 0 && y < n+k && x >= 0 && x < m+k) return true;
        return false;
    }
}
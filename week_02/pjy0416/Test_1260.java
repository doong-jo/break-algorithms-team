package dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test_1260 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String infoStr = br.readLine();

        Graph gr = new Graph(Integer.parseInt(infoStr.split(" ")[0]));
        int size = Integer.parseInt(infoStr.split(" ")[1]);
        int start = Integer.parseInt(infoStr.split(" ")[2]);

        for(int i=0; i<size; i++) {
            String inputStr = br.readLine();
            gr.add(Integer.parseInt(inputStr.split(" ")[0]), Integer.parseInt(inputStr.split(" ")[1]));
        }

        gr.printDFS(start);
        gr.printBFS(start);

        br.close();
    }
}

class Graph {
    private int nodeNum;                    // 노드의 갯수
    private int[][] adj;

    //Constructor
    Graph(int num) {
        nodeNum = num;
        adj = new int[num+1][num+1];        // index의 시작이 0이 아닌 1부터 시작하기 위해서
    }

    void add(int major, int target) {   // 양방향으로 간선 이어주기
        adj[major][target] =1;
        adj[target][major] =1;
    }

    void printDFS(int start) {          // DFS 실행 결과 print
        boolean[] isVisited = initBool();

        dfs(start, isVisited);
    }

    void printBFS(int start) {          // BFS 실행 결과 print
        System.out.println();       // DFS결과와 띄워주기 위한 프린트
        boolean[] isVisited = initBool();
        bfs(start, isVisited);
    }

    private void dfs(int start, boolean[] isVisited) {            // DFS 탐색
        System.out.print(start + " ");

        isVisited[start] = true;                                  // 방문기록 작성
        for(int i=1; i<=nodeNum; i++) {                           // loop 돌면서
            if(adj[start][i] ==1) {                               // 현재 들어와있는 Node에 간선 연결된 곳이 있을 때
                if(!isVisited[i]) {                               // 방문한 적이 없으면
                    dfs(i,isVisited);                             // 들어가즈아~
                }
            }
        }
    }

    private void bfs(int start, boolean[] isVisited) {                                           // BFS 탐색
        int[] queue = new int[nodeNum+1];                                                       // Queue Arr 생성
        int size =0;                 // queue의 실질적 사이즈
        int top =0;                 // 현재위치

        queue[size++] = start;      // 사이즈 증가하며 추가
        isVisited[start] = true;    // 현재 노드 방문

        while(true) {
            int num = queue[top++]; // 현재 위치인 top을 불러오면서 다음 idx로 이동

            if(num == 0) {          // queue 크기를 초과했다는 의미 이기 때문에
                break;              // 멈춰준다.
            }

            System.out.print(num + " ");        // queue에 노드가 남아있으면 print
            for(int i=1; i<=nodeNum; i++) {     // looping
                if(adj[num][i] == 1) {          // 현재 노드에서 연결된 간선이 있을 때
                    if(!isVisited[i]) {         // 탐색한 적이 없다면
                        queue[size++] = i;      // queue 사이즈를 증가시키며
                        isVisited[i] = true;    // 들렸다는 표시를 해줌
                    }
                }
            }
        }

        /*
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        isVisited[start] = true;

        while(!q.isEmpty()) {
            int num = q.poll();
            System.out.print(num + " ");
            for(int i=1; i<=nodeNum; i++) {
                if(adj[num][i] == 1) {
                    if(!isVisited[i]) {
                        q.add(i);
                        isVisited[i] = true;
                    }
                }
            }
        }
        */
    }

    private boolean[] initBool() {      // isVisited 배열을 생성, 반환
        boolean[] isVisited = new boolean[nodeNum+1];

        for(int i=1; i<=nodeNum; i++) {
            isVisited[i] = false;
        }

        return isVisited;
    }
}

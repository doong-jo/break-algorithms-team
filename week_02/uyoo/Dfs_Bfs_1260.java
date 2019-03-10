//package me.uyoo.GroupPractice.Week_02_Graph_Bfs_Dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//인접 행렬
class AdjMatrix{
    private int[][] matrix;
    private int size;

    public AdjMatrix(int size) {
        this.size = size;
        matrix = new int[size+1][size+1];
    }

    public void insertEdge(int v1, int v2){
        matrix[v1][v2] = 1;
        matrix[v2][v1] = 1;
    }

    public int[][] getMatrix() {
        return matrix;
    }
}

public class Dfs_Bfs_1260 {
    static boolean[] checked;
    static int vertexLen;

    //Dfs는 재귀를 통해 진행
    private static void doDfs(int start, int[][] matrix) {
        checked[start] = true;
        System.out.print(start + " ");

        for(int i=1; i<vertexLen+1; i++){
            if(!checked[i] && matrix[start][i] == 1){
                doDfs(i, matrix);
            }
        }
    }

    //Bfs : 큐 생성 -> 해당 노드 마킹 -> 큐에 삽입 -> { 큐 출력 -> 인접노드 탐색 -> 인접노드 마킹 -> 큐 삽입 }
    private static void doBfs(int start, int[][] matrix) {
        checked = new boolean[vertexLen + 1];

        Queue<Integer> queue = new LinkedList<>();
        checked[start] = true;
        queue.offer(start);

        int q;
        while (!queue.isEmpty()){
            q = queue.poll();
            System.out.print(q + " ");

            for(int i=1; i<vertexLen+1; i++){
                if(!checked[i] && matrix[q][i] == 1){
                    checked[i] = true;
                    queue.offer(i);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String nInput = br.readLine();
        StringTokenizer st = new StringTokenizer(nInput, " ");

        vertexLen = Integer.parseInt(st.nextToken());
        int edgeLen = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(st.nextToken());

        AdjMatrix adjMatrix = new AdjMatrix(vertexLen);
        for(int i=0; i<edgeLen; i++){
            String edges = br.readLine();
            st = new StringTokenizer(edges, " ");
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());

            adjMatrix.insertEdge(v1, v2);
        }

        //System.out.println("===== Dfs =====");
        checked = new boolean[vertexLen + 1];
        doDfs(start, adjMatrix.getMatrix());

        System.out.println();

        //System.out.println("===== Bfs =====");
        doBfs(start, adjMatrix.getMatrix());

        br.close();
    }
}

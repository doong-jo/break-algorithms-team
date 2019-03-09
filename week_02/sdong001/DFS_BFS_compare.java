/**
 * url : https://www.acmicpc.net/problem/1260
 *
 * 문제
 * 그래프를 DFS로 탐색한 결과와 BFS로 탐색한 결과를 출력하는 프로그램을 작성하시오. 단, 방문할 수 있는 정점이 여러 개인 경우에는 정점 번호가 작은 것을 먼저 방문하고, 더 이상 방문할 수 있는 점이 없는 경우 종료한다. 정점 번호는 1번부터 N번까지이다.
 *
 * 입력
 * 첫째 줄에 정점의 개수 N(1 ≤ N ≤ 1,000), 간선의 개수 M(1 ≤ M ≤ 10,000), 탐색을 시작할 정점의 번호 V가 주어진다. 다음 M개의 줄에는 간선이 연결하는 두 정점의 번호가 주어진다. 어떤 두 정점 사이에 여러 개의 간선이 있을 수 있다. 입력으로 주어지는 간선은 양방향이다.
 *
 * 출력
 * 첫째 줄에 DFS를 수행한 결과를, 그 다음 줄에는 BFS를 수행한 결과를 출력한다. V부터 방문된 점을 순서대로 출력하면 된다.
 */

package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DFS_BFS_compare {
    static class Graph {
        int[][] vertices;
        boolean[] visited;
        int visitCnt;

        Graph(int N) {
            vertices = new int[N][];
            visited = new boolean[N];

            for (int i = 0; i < N; i++) {
                vertices[i] = new int[N];
            }
        }

        void addEdge(int startInd, int endInd) {
            vertices[startInd][endInd] = 1;
            vertices[endInd][startInd] = 1;
        }

        void reset() {
            for (int i = 0; i < N; i++) {
                visited[i] = false;
            }
            visitCnt = 0;
        }

        void visit(int V) {
            if( !visited[V] ) {
                visited[V] = true;
                visitCnt++;

                if ( visitCnt < N ) { System.out.print(V + 1 + " "); }
                else { System.out.print(V + 1); }
            }
        }
    }

    static class Queue {
        int[] list;
        int size;

        public Queue(int N) {
            list = new int[N];
        }

        void push(int v) {
            size++;
            for(int i=size-1; i>0; i--) {
                list[i] = list[i-1];
            }
            list[0] = v;
        }

        int pop() {
            size--;
            return list[size];
        }
    }

    static int N;
    static int M;
    static Graph graph;
    static Queue queue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String config = br.readLine();
        StringTokenizer tokenizer = new StringTokenizer(config, " ");

        N = Integer.valueOf(tokenizer.nextToken());
        M = Integer.valueOf(tokenizer.nextToken());
        final int V = Integer.valueOf(tokenizer.nextToken()) - 1;

        graph = new Graph(N);

        for (int i = 0; i < M; i++) {
            String edge = br.readLine();
            tokenizer = new StringTokenizer(edge, " ");
            final int start =  Integer.valueOf(tokenizer.nextToken()) - 1;
            final int end = Integer.valueOf(tokenizer.nextToken()) - 1;

            graph.addEdge(start, end);
        }

        dfs(V);
        System.out.println();

        graph.reset();

        queue = new Queue(N);
        graph.visit(V);
        bfs(V);
    }

    static void dfs(int V) {
        graph.visit(V);

        for (int i = 0; i < N; i++) {
            if (graph.vertices[V][i] == 1 && !graph.visited[i]) {
                dfs(i);
            }
        }
    }

    static void bfs(int V) {
        for (int i = 0; i < N; i++) {
            if( graph.vertices[V][i] == 1 && !graph.visited[i]) {
                queue.push(i);
                graph.visit(i);
            }
        }

        if( queue.size > 0) {
            bfs(queue.pop());
        }
    }
}

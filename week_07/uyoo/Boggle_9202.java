import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

class Trie_9202 {
    Node_9202 root;

    public Trie_9202() {
        this.root = new Node_9202(' ');
    }

    public void insert(String input){
        Node_9202 current = root;
        for(int i=0; i<input.length(); i++){
            char data = input.charAt(i);
            int index = input.charAt(i) - 'A';

            if(current.child[index] == null)
                current.child[index] = new Node_9202(data);

            current = current.child[index];
        }
        current.isFinished = true;
        current.word = input;
    }
}

class Node_9202 {
    public char data;
    public Node_9202[] child;
    public boolean isFinished;
    public String word;

    public Node_9202(char data) {
        this.data = data;
        this.child = new Node_9202[26];
        this.isFinished = false;
    }
}

public class Boggle_9202 {
    static int wordCnt, boardCnt;
    static char[][] boards;
    static int[] dy = {-1, -1, -1, 0, 1, 1, 1, 0};  //왼위 위 오위 오 오아래 아래 왼아래 왼
    static int[] dx = {-1, 0, 1, 1, 1, 0, -1, -1};

    static HashSet<String> hashSet;
    static boolean[][] checked;

    private static void makeWord(int y, int x, String str, Node_9202 node) {
        if(y < 0 || y >= 4 || x < 0 || x >= 4) return;
        if(checked[y][x]) return;
        if(str.length() > 8) return;
        if(node.child[boards[y][x] - 'A'] == null) return;

        //해당 글자가 Trie에 있다면 문자를 조합하며 진행
        checked[y][x] = true;
        str += boards[y][x];

        if(node.child[boards[y][x] - 'A'].isFinished){
            hashSet.add(str);
        }

        //출발지점이 단어가 존재하면 -> 그걸 포함한채로 인접한 곳을 dfs로 계속 탐색 -> 끝나면 해당 지점 마킹 해제(다른 조합에 사용되야하니까)
        for(int i=0; i<8; i++){
            int adj_Y = y + dy[i];
            int adj_X = x + dx[i];

            makeWord(adj_Y, adj_X, str, node.child[boards[y][x] - 'A']);
        }
        checked[y][x] = false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        wordCnt = Integer.parseInt(br.readLine());
        Trie_9202 trie = new Trie_9202();
        for(int i=0; i<wordCnt; i++){
            //단어 Trie에 삽입
            String input = br.readLine();
            trie.insert(input);
        }
        br.readLine();

        boardCnt = Integer.parseInt(br.readLine());
        int b = 0;
        while (b < boardCnt){
            //보드로 단어 조합, 조합된 단어를 Trie 사전에서 search
            boards = new char[4][4];
            for(int i=0; i<4; i++){
                String input = br.readLine();
                for(int j=0; j<4; j++){
                    boards[i][j] = input.charAt(j);
                }
            }

            hashSet = new HashSet<>();
            checked = new boolean[4][4];
            //for문을 통해 각각 출발점마다의 경우를 고려
            for(int i=0; i<4; i++){
                for(int j=0; j<4; j++){
                    makeWord(i, j, "", trie.root);
                }
            }

            ArrayList<String> aLists = new ArrayList<>();
            for(String a : hashSet)
                aLists.add(a);

            Collections.sort(aLists);

            int score = 0;
            String maxStr = "";
            int count = 0;
            for(String a : aLists){
                if(3 <= a.length() && a.length() <= 4) score += 1;
                else if(a.length() == 5) score += 2;
                else if(a.length() == 6) score += 3;
                else if(a.length() == 7) score += 5;
                else if(a.length() == 8) score += 11;

                if(maxStr.length() < a.length()){
                    maxStr = a;
                }
                count++;
            }
            System.out.println(score + " " + maxStr + " " + count);

            if(b < boardCnt-1){
                br.readLine();
            }
            b++;
        }
    }
}
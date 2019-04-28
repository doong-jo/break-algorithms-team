import java.io.*;

class Trie_4358 {
    Node_4358 root;

    public Trie_4358() {
        this.root = new Node_4358(' ');
    }

    public void insert(String str){
        Node_4358 current = root;
        for(int i=0; i<str.length(); i++){
            char data = str.charAt(i);
            int index = str.charAt(i) - 0;

            if(current.child[index] == null){
                current.child[index] = new Node_4358(data);
            }
            current = current.child[index];
        }

        //단어가 중복되면 개수++ , 아니라면 단어 추가
        if(current.isFinished){
            current.wordCnt += 1;

        } else {
            current.isFinished = true;
            current.word = str;
        }
    }

    //DFS -> 사전에 존재하는 문자를 탐색해 문자 발견 시 비율 출력
    public void getAllLeaf(Node_4358 node, int totalSpeciesCnt){
        if(node.isFinished) {
            String word = node.word;
            double wordCnt = node.wordCnt;
            double ratio = (wordCnt / totalSpeciesCnt) * 100;

            System.out.print(word + " ");
            System.out.printf("%.4f\n", ratio);
        }

        Node_4358 current = node;
        for(Node_4358 n : current.child){
            if(n != null){
                getAllLeaf(n, totalSpeciesCnt);
            }
        }
    }
}

class Node_4358 {
    public char data;
    public Node_4358[] child;
    public String word;
    public int wordCnt = 1;
    public boolean isFinished;

    public Node_4358(char data) {
        this.data = data;
        this.child = new Node_4358[128];
        isFinished = false;
    }
}

public class TreeDistribution_4358 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Trie_4358 trie = new Trie_4358();
        String input;
        int totalSpeciesCnt = 0;
        while ((input = br.readLine()) != null && input.length() != 0){
            trie.insert(input);
            totalSpeciesCnt += 1;
        }

        //최종 단어들(leaf)을 탐색해 각 단어가 갖는 개수로 백분율 계산
        trie.getAllLeaf(trie.root, totalSpeciesCnt);
    }
}
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

class Trie_4358 {
    Node_4358 root;

    public Trie_4358() {
        this.root = new Node_4358(' ');
    }

    public void insert(String input){
        String str = input.toUpperCase().replace(" ", "");

        Node_4358 current = root;
        for(int i=0; i<str.length(); i++){
            char data = str.charAt(i);
            int index = str.charAt(i) - 'A';

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
            current.word = input;
        }
    }

    public void getAllLeaf(Node_4358 node, double totalSpeciesCnt){
        if(node.isFinished) {
            String word = node.word;
            int wordCnt = node.wordCnt;
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
        this.child = new Node_4358[26];
        isFinished = false;
    }
}

public class TreeDistribution_4358 {
    static double totalSpeciesCnt = 0.0d;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Scanner scanner = new Scanner(System.in);

        Trie_4358 trie = new Trie_4358();
        String input;
        while (true){
            input = br.readLine();
            if(input.equals("")) break;

            //좀 더 정확하게는 중복되는 종이 있으면 총 count를 늘리면 x..
//            if(trie.insert(input)){
//               totalSpeciesCnt += 1;
//            }

            trie.insert(input);
            totalSpeciesCnt += 1;
        }

        //최종 단어들(leaf)을 탐색해 각 단어가 갖는 개수로 백분율 계산
        trie.getAllLeaf(trie.root, totalSpeciesCnt);

    }
}
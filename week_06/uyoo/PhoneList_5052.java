import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Trie_5052 {
    Node_5052 root;

    public Trie_5052() {
        this.root = new Node_5052(' ');
    }

    public boolean insert(String str){
        Node_5052 current = root;
        int cnt = 0;
        for(int i=0; i<str.length(); i++){
            char data = str.charAt(i);
            int index = str.charAt(i) - 48;

            if(current.child[index] == null){
                current.child[index] = new Node_5052(data);
            } else {
                cnt++;
            }
            current = current.child[index];

            //하나의 단어가 존재하는 상황에서, 이어서 또 다른 단어가 추가되려 할때
            //ex. 기존: bus, 이어서: busan -> 겹치니까 false
            if(current.isFinished){
                return false;
            }
        }
        //추가된 단어가 이전에 추가된 단어들의 속한다면 false
        if(cnt == str.length()) return false;

        current.isFinished = true;
        current.word = str;

        return true;
    }
}

class Node_5052{
    public char data;
    public Node_5052[] child;
    public boolean isFinished;
    public String word;

    public Node_5052(char data) {
        this.data = data;
        this.child = new Node_5052[10];
        this.isFinished = false;
    }
}

public class PhoneList_5052 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());

        int t = 0;
        while (t < testCase){
            int phoneNums = Integer.parseInt(br.readLine());
            String[] inputs = new String[phoneNums];
            for(int i=0; i<phoneNums; i++){
                inputs[i] = br.readLine();
            }
            //Arrays.sort(inputs);

            boolean possible = true;
            Trie_5052 trie = new Trie_5052();
            for(int i=0; i<phoneNums; i++){
                if(!trie.insert(inputs[i])){
                    possible = false;
                }
            }
            System.out.println(possible ? "YES" : "NO");

            t++;
        }

    }
}
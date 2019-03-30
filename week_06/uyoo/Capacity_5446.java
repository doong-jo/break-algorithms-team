import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Trie_5446 {
    Node_5446 root;

    public Trie_5446() {
        this.root = new Node_5446(' ');
    }

    public void insert(String input, boolean deleteCheck){
        if(input == null)
            return;

        Node_5446 current = root;
        for(int i=0; i<input.length(); i++){
            char data = input.charAt(i);
            int index = input.charAt(i) - 0;

            if(current.child[index] == null)
                current.child[index] = new Node_5446(data);

            current = current.child[index];
            current.delete = deleteCheck;
        }
        //단어가 완성되는 지점에는 기존에 상태를 저장(삭제할것인지 or 삭제x것인지)
        current.originStatus = deleteCheck;
    }

}

class Node_5446 {
    public char data;
    public Node_5446[] child;
    public boolean originStatus;
    public boolean delete;
    public String word;

    public Node_5446(char data) {
        this.data = data;
        this.child = new Node_5446[128];
        this.originStatus = false;
    }
}

public class Capacity_5446 {
    static int cnt = 0;

    public static void solve(Node_5446 node) {
        //해당 노드가 삭제 가능하다면, 이하의 노드들 모두 삭제 가능 -> 하나만 카운팅
        if(node.delete){
            cnt++;
            return;
        }
        //삭제해야할 노드가 삭제하지 말아야할 노드로 덮였다면, 기존에 갖고있던 상태를 비교해 카운팅
        else if(node.originStatus) {
            cnt++;
        }

        Node_5446 current = node;
        for(Node_5446 n : current.child){
            if(n != null){
                solve(n);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());

        int t = 0;
        while (t < testCase){
            int deleteFileNum = Integer.parseInt(br.readLine());

//            boolean deleteCheck;
            Trie_5446 trie = new Trie_5446();
            for(int i=0; i<deleteFileNum; i++){
//                deleteCheck = true;
                String input = br.readLine();
                trie.insert(input, true);
            }

            int noDeleteFileNum = Integer.parseInt(br.readLine());
            for(int i=0; i<noDeleteFileNum; i++){
//                deleteCheck = false;
                String input = br.readLine();
                trie.insert(input, false);
            }

            solve(trie.root);
            System.out.println(cnt);
            cnt = 0;

            t++;
        }
        br.close();
    }
}
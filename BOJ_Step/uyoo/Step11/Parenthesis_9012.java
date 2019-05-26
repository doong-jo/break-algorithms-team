import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Stack_9012{
    private ArrayList<String> arrayList = new ArrayList<>();
    private int top = -1;

    public void push(String input){
        if(input.equals("(")){
            this.arrayList.add(input);
            ++this.top;
        }

        // "("만 푸쉬해놓기 때문에, list에 하나라도 존재하면 ")"과 묶을 수 있음
        // 하나도 없다면 ")" 푸쉬
        else if(input.equals(")")){
            if(!isEmpty() && this.arrayList.get(top).equals("(")){
                pop();
                return;
            } else {
                this.arrayList.add(input);
                ++this.top;
            }
        }
    }

    public String pop(){
        if(isEmpty()) return "";
        return this.arrayList.remove(this.top--);
    }

    public boolean hasSize(){
        if(this.arrayList.size() <= 0) return false;
        else return true;
    }

    private boolean isEmpty() {
        if(this.top < 0) return true;
        return false;
    }
}

public class Parenthesis_9012 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCase = Integer.parseInt(br.readLine());
        int t = 0;
        while (t < testCase){
            String input = br.readLine();
            Stack_9012 stack = new Stack_9012();
            for(int i=0; i<input.length(); i++){
                String str = input.substring(i, i+1);
                stack.push(str);
            }

            if(stack.hasSize()) System.out.println("NO");
            else System.out.println("YES");

            t++;
        }
    }
}

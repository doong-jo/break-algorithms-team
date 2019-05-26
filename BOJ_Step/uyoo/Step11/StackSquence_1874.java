import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Stack_1874 {
    private ArrayList<Integer> arrayList = new ArrayList<>();
    private int top = -1;
    private int num = 1;  //입력되는 숫자

    public void push(int input){
        this.arrayList.add(input);
        ++top;
    }

    public int pop() {
        if(isEmpty()) return -1;
        return this.arrayList.remove(top--);
    }

    // 입력 값을 넣어 배열에 존재하는지 판단
    // 있는 상태 + top 위치의 값과 입력값 비교
    // 같지 않으면 NO, 같다면 pop
    // 없으면 해당 값까지 push 후 pop
    public ArrayList<String> deterNum(int input){
        ArrayList<String> ans = new ArrayList<>();

        int size = arrayList.size();
        for(int i=0; i<size; i++){
            if(arrayList.get(i) == input){
                if(arrayList.get(this.top) != arrayList.get(i)){
                    ans.add("NO");
                    return ans;
                } else {
                    pop();
                    ans.add("-");
                    return ans;
                }
            }
        }

        //꺼내려는 값이 없으면 넣어준 숫자부터 이어서 push 후 pop
        int index;
        for(index=num; index<=input; index++){
            push(index);
            ans.add("+");
        }
        num = index;
        pop();
        ans.add("-");
        return ans;
    }

    public boolean isEmpty(){
        if(this.top < 0) return true;
        return false;
    }

}

public class StackSquence_1874 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Stack_1874 stack = new Stack_1874();
        ArrayList<String> results = new ArrayList<>();
        for(int i=0; i<n; i++){
            int input = Integer.parseInt(br.readLine());
            for(String str : stack.deterNum(input)){
                if(str.equals("NO")){
                    System.out.println("NO");
                    return;
                } else{
                    results.add(str);
                }
            }
        }

        for(String res : results)
            System.out.println(res);
    }
}
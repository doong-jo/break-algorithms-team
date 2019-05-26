import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Stack {
    private int size;
    public int[] arr;
    public int top = -1;

    public Stack(int size) {
        this.size = size;
        arr = new int[size];
    }

    public void push(int input){
        //if top == size -> 동적할당
        if(top == size){
            size = size * 2;
            arr = Arrays.copyOf(arr, size);
        }

        arr[++top] = input;
    }

    public int pop(){
        if(isEmpty()){
            return -1;
        }
        return arr[top--];
    }

    public int peek(){
        if(isEmpty()){
            return -1;
        }
        return arr[top];
    }

    public int size(){
        return top+1;
    }

    public boolean isEmpty(){
        if(top < 0) return true;
        return false;
    }
}

public class Stack_10828 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int operationSize = Integer.parseInt(br.readLine());
        Stack stack = new Stack(operationSize);

        int o = 0;
        while (o < operationSize){
            String operation = br.readLine();
            st = new StringTokenizer(operation, " ");

            String command = st.nextToken();
            if(command.equals("push")){
                int input = Integer.parseInt(st.nextToken());
                stack.push(input);

            } else if(command.equals("pop")){
                int popValue = stack.pop();
                System.out.println(popValue);

            } else if(command.equals("top")){
                int topValue = stack.peek();
                System.out.println(topValue);

            } else if(command.equals("size")){
                int size = stack.size();
                System.out.println(size);

            } else if(command.equals("empty")){
                if(stack.isEmpty()) System.out.println(1);
                else System.out.println(0);
            }

            o++;
        }
    }
}
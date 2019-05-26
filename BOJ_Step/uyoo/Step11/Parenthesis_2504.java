import java.util.Scanner;
import java.util.Stack;

/*
* 처음 닫힌 괄호가 등장하면 값 부여 (2 or 3)
* 이후 닫힌 괄호가 들어왔을 때, 앞에 숫자가 존재하면 -> stack이 빌때까지 계산
*
* */

public class Parenthesis_2504 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        Stack<String> stack = new Stack<>();
        for(int i=0; i<input.length(); i++){
            String str = input.substring(i, i+1);

            if(str.equals("(") || str.equals("[")){
                stack.push(str);
            }

            else{
                if(stack.isEmpty()) {
                    System.out.println(0);
                    return;
                }

                if(str.equals(")")){
                    if(stack.peek().equals("(")){
                        stack.pop();
                        stack.push("2");
                    }
                    else{
                        if(!doCalculate(stack, "[", "(", 2)){
                            System.out.println(0);
                            return;
                        }
                    }
                }

                else if(str.equals("]")){
                    if(stack.peek().equals("[")){
                        stack.pop();
                        stack.push("3");
                    }
                    else{
                        if(!doCalculate(stack, "(", "[", 3)){
                            System.out.println(0);
                            return;
                        }
                    }
                }
            }
        }

        //stack에는 값만 존재
        int sum = 0;
        while (!stack.isEmpty()){
            if (stack.peek().equals("(") || stack.peek().equals(")") || stack.peek().equals("[")
                    || stack.peek().equals("]")) {
                System.out.println(0);
                return;
            }
            sum += Integer.parseInt(stack.pop());
        }
        System.out.println(sum);
    }


    /*
    * 1. 괄호 매치가 안되면 false
    * 2. 앞에 부합되면(짝이되는) 괄호가 존재하면 result *= value를 곱해준뒤 break
    * 3. 앞에 숫자가 존재하면 result += 숫자
    * loop
    * */
    private static boolean doCalculate(Stack<String> stack, String s1, String s2, int value) {
        int result = 0;

        while (!stack.isEmpty()){
            if(stack.peek().equals(s1)){
                return false;

            } else if(stack.peek().equals(s2)){
                stack.pop();
                result *= value;
                stack.push(String.valueOf(result));
                break;

            } else{
                result += Integer.parseInt(stack.pop());
            }
        }

        return true;
    }
}

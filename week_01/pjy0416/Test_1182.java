package bruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test_1182 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int[] numArr;
    private static int size, dest, cnt;

    public static int getResult() throws IOException {
        // Make an Array
        numArr = new int[size+1];
        String numStr = br.readLine();
        String[] numStrArr = numStr.split(" ");

        for(int i=0; i<size; i++) {
            numArr[i] = Integer.parseInt(numStrArr[i]);
        }

        cnt =0;
        if(dest ==0) {
            cnt--;
        }
        return go(0,0);
    }

    private static int go(int pos, int sum) {
        if(pos == size) {
            if(sum == dest) {
                cnt++;
            }
            return cnt;
        }
        go(pos+1, sum+numArr[pos]);         // 해당 수를 더하거나
        go(pos+1, sum);                          // 안더하거나...

        return cnt;
    }


    public static void main(String[] args) throws IOException {
        String inputStr = br.readLine();
        size = Integer.parseInt(inputStr.split(" ")[0]);        // get a size from client
        dest = Integer.parseInt(inputStr.split(" ")[1]);        // get a destination number from client

        System.out.println(getResult());

        br.close();
    }
}

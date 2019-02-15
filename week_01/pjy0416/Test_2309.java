package bruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Test_2309 {

    private final static int listSize = 9;
    private static int[] allArr = new int[listSize];        // get Exception 메소드에서 파라미터 전달 없이 이용하기 위해 static으로 선언
    private static int sum=0;                               // 마찬가지

    private static int[] getExp(int one, int two) {
        if( sum - allArr[one] - allArr[two] == 100) {
            int[] exp = {one, two} ;
            return exp;
        }

        if(two+1 >= listSize) {
            return getExp(one+1, one+2);
        }

        return getExp(one, two+1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i=0; i<listSize; i++) {
            int tmp = Integer.parseInt(br.readLine());
            allArr[i] = tmp;
            sum += tmp;
        }

        Arrays.sort(allArr);

        int[] expArr = getExp(0,1);

        for(int i=0; i<listSize; i++) {
            if(i == expArr[0] || i == expArr[1]) {
                continue;
            }
            System.out.println(allArr[i]);
        }

        br.close();
    }
}

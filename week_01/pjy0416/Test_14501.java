package bruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test_14501 {
    private static int size;
    private static int[] days, price;
    private static int max =0;

    public static void bf(int idx, int sum) {
        max = Math.max(max, sum);
        if(idx == size) {
            return ;
        }

        if(idx+days[idx] <= size) {
            bf(idx + days[idx], sum + price[idx]);
        }
        bf(idx+1, sum);     // 안더하고 그냥 넘기기

        return ;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));

        size = Integer.parseInt(br.readLine());

        days = new int[size+1];
        price = new int[size+1];

        for(int i=0; i<size; i++) {
            String str = br.readLine();
            days[i] = Integer.parseInt(str.split(" ")[0]);
            price[i] = Integer.parseInt(str.split(" ")[1]);
        }

        bf(0,0);
        System.out.println(max);

        br.close();
    }
}

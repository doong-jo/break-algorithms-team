package me.uyoo.GroupPractice.BOJ_Step.Step10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Demical_1929 {

    private static boolean findDemical(int num) {
        if(num == 1) return false;

        int num_sqrt = (int) Math.sqrt(num);
        for(int i=2; i<=num_sqrt; i++){
            if(num % i == 0)
                return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String config = br.readLine();
        st = new StringTokenizer(config, " ");

        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        for(int i=m; i<=n; i++){
            int num = i;
            if(findDemical(num)){
                System.out.println(num);
            }
        }
    }
}

package me.uyoo.GroupPractice.BOJ_Step.Step06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NumCnt_2577 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] numCnt = new int[10];

        int result = 1;
        for(int i=0; i<3; i++){
            String input = br.readLine();
            result *= Integer.parseInt(input);
        }

        String result_str = Integer.toString(result);
        for(int i=0; i<result_str.length(); i++){
            int index = Integer.parseInt(result_str.substring(i, i+1));
            numCnt[index]++;
        }

        for(int ans : numCnt)
            System.out.println(ans);

        br.close();
    }
}

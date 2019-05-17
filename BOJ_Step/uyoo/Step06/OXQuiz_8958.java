package me.uyoo.GroupPractice.BOJ_Step.Step06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class OXQuiz_8958 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());

        int t=0;
        while (t < testCase){
            String str = br.readLine();
            int score = 0;
            int plus = 0;

            for(int i=0; i<str.length(); i++){
                if(str.substring(i, i+1).equals("O")){
                    score += ++plus;
                } else {
                    plus = 0;
                }
            }

            System.out.println(score);
            t++;
        }
        br.close();
    }
}

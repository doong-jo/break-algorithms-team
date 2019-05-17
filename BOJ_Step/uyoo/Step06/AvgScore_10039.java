package me.uyoo.GroupPractice.BOJ_Step.Step06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AvgScore_10039 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] scores = new int[5];

        int sum = 0;
        for(int i=0; i<5; i++){
            scores[i] = Integer.parseInt(br.readLine());
            if(scores[i] < 40){
                scores[i] = 40;
            }
            sum += scores[i];
        }

        System.out.println(sum / 5);
    }
}
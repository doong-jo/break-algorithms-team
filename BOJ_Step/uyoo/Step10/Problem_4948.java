package me.uyoo.GroupPractice.BOJ_Step.Step10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Problem_4948 {

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

        while (true){
            int input = Integer.parseInt(br.readLine());
            if(input == 0) return;

            int cnt = 0;
            for(int i=input + 1; i<=input * 2; i++){
                int num = i;
                if(findDemical(num)){
                    cnt++;
                }
            }
            System.out.println(cnt);
        }
    }
}
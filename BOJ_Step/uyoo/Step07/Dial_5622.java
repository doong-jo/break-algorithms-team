package me.uyoo.GroupPractice.BOJ_Step.Step07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Dial_5622 {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        //알파벳 별로 소요되는 시간을 배열 형태로 삽입
        int[] alpha = {3, 3, 3, 4, 4, 4, 5, 5, 5, 6, 6, 6, 7, 7, 7, 8, 8, 8, 8, 9, 9, 9, 10, 10, 10, 10};

        int sum = 0;
        for(int i=0; i<input.length(); i++){
            int index = input.charAt(i) - 'A';
            sum += alpha[index];
        }
        System.out.println(sum);
    }
}

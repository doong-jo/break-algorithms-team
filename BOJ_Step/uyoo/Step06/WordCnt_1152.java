package me.uyoo.GroupPractice.BOJ_Step.Step06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class WordCnt_1152 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine().trim();
        String[] arr_str = input.split(" ");

        if(input.isEmpty()){
            System.out.println(0);
        } else {
            System.out.println(arr_str.length);
        }
    }
}

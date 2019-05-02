package me.uyoo.GroupPractice.Week_07_Step.Step02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Operation {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int result = 0;
        for(int i=0; i<2; i++){
            result += Integer.parseInt(br.readLine());
        }
        System.out.println(result);
    }
}

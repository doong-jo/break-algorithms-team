package me.uyoo.GroupPractice.Week_07_Step.Step01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Print {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;

        while ((input = br.readLine()) != null){
            System.out.println(input);
        }
    }
}

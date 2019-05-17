package me.uyoo.GroupPractice.BOJ_Step.Step07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FindAlphabet_10809 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] alphabets = new int[26];

        for(int i=0; i<alphabets.length; i++){
            alphabets[i] = -1;
        }

        String input = br.readLine();
        for(int i=0; i<input.length(); i++){
            int index = input.charAt(i) - 'a';
            if(alphabets[index] == -1){
                alphabets[index] = i;
            }
        }

        for(int ans : alphabets)
            System.out.print(ans + " ");
    }
}

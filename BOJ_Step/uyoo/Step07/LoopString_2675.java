package me.uyoo.GroupPractice.BOJ_Step.Step07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class LoopString_2675 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int testCase = Integer.parseInt(br.readLine());

        int t=0;
        while (t < testCase){
            String input = br.readLine();
            st = new StringTokenizer(input, " ");

            int size = Integer.parseInt(st.nextToken());
            String str = st.nextToken();
            for(int i=0; i<str.length(); i++){
                for(int j=0; j<size; j++){
                    System.out.print(str.substring(i, i+1));
                }
            }
            System.out.println();

            t++;
        }
    }
}

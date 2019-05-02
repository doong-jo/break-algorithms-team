package me.uyoo.GroupPractice.Week_07_Step.Step03;

import java.io.*;
import java.util.StringTokenizer;

public class FastSum_15552 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int testCase = Integer.parseInt(br.readLine());

        int t = 0;
        while (t < testCase){
            String input = br.readLine();
            st = new StringTokenizer(input, " ");
            int result = 0;

            int size = st.countTokens();
            for(int i=0; i<size; i++){
                result += Integer.parseInt(st.nextToken());
            }

            bw.write(result + "\n");
            t++;
        }

        bw.flush();
        bw.close();
        br.close();
    }
}

package me.uyoo.GroupPractice.BOJ_Step.Step09;

import java.io.*;

public class NumSort_10989 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[] nums = new int[10001];

        //해당되는 수의 배열 ++
        for(int i=0; i<n; i++){
            nums[Integer.parseInt(br.readLine())]++;
        }

        for(int i=0; i<nums.length; i++){
            if(nums[i] > 0){
                //중복된 수 처리를 위해
                for(int j=0; j<nums[i]; j++)
                    bw.write(i + "\n");
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }
}

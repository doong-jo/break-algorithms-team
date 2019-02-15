package me.uyoo.GroupPractice.Week_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Subset_1182 {
    static int[] nums;
    static int s;
    static int cnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String nInput = br.readLine();
        StringTokenizer st = new StringTokenizer(nInput, " ");

        int n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        nums = new int[n];

        String num = br.readLine();
        st = new StringTokenizer(num, " ");
        for(int i=0; i<n; i++){
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int index = 0;
        int sum = 0;
        countSubset(index, sum);
        System.out.println(cnt);

    }

    private static void countSubset(int index, int sum) {
        if(index >= nums.length){
            return;
        }
        if(sum + nums[index] == s){
            cnt++;
        }

        //현재 index를 부분집합으로 삼은 경우
        countSubset(index+1, sum + nums[index]);

        //현재 index를 부분집합으로 삼지 x 경우
        countSubset(index+1, sum);

    }

}

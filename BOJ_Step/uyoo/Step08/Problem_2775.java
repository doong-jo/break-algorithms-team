package me.uyoo.GroupPractice.BOJ_Step.Step08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Problem_2775 {

    static int[][] arr;

    private static int findPersonNum(int k, int n) {
        //아래층 00호로 이동 후 0이면 또 아래층으로 가서 00호까지 인원 구하기
        if(n == 1) return 1;

        int result = 0;
        k -= 1;
        for(int i=1; i<n+1; i++){
            if(arr[k][i] == 0){
                arr[k][i] = findPersonNum(k, i);
            }
            result += arr[k][i];
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCase = Integer.parseInt(br.readLine());
        int t = 0;
        while (t < testCase){
            int k = Integer.parseInt(br.readLine()); //00층
            int n = Integer.parseInt(br.readLine()); //00호
            arr = new int[k][n+1];

            //0층 호수에 있는 인원 수
            for(int j=1; j<n+1; j++){
                arr[0][j] = j;
            }

            int answer = findPersonNum(k, n);
            System.out.println(answer);

            t++;
        }
    }
}

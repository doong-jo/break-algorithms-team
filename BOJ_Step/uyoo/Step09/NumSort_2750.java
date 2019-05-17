package me.uyoo.GroupPractice.BOJ_Step.Step09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NumSort_2750 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        for(int i=0; i<n; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        //선택정렬
        for(int i=0; i<n-1; i++){
            for(int j=i; j<n; j++){
                if(arr[i] > arr[j]){
                    int tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                }
            }
        }

        for(int ans : arr)
            System.out.println(ans);

    }
}

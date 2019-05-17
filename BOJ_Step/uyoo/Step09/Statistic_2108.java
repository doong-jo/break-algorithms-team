package me.uyoo.GroupPractice.BOJ_Step.Step09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Statistic_2108 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr_positive = new int[4001];
        int[] arr_negative = new int[4001];
        int[] arr_ascend = new int[n];
        int[] arr = new int[n];

        int sum = 0;
        for(int i=0; i<n; i++){
            arr[i] = Integer.parseInt(br.readLine());
            if(arr[i] >= 0){
                arr_positive[arr[i]]++;
            } else {
                arr_negative[-arr[i]]++;
            }
            sum += arr[i];
        }

        //산술평균값
        float avg = (float)sum / n;
        System.out.printf("%.0f\n", avg);


        //최빈값 구하면서 오름차순까지 정렬
        int mode = 0;
        int ascendIndex = 0;
        ArrayList<Integer> arrayList = new ArrayList<>();
        for(int i=arr_negative.length-1; i>0; i--){
            if(arr_negative[i] != 0){
                if(mode == arr_negative[i]){
                    arrayList.add(-i);
                } else if(mode < arr_negative[i]){
                    arrayList = new ArrayList<>();
                    mode = arr_negative[i];
                    arrayList.add(-i);
                }

                while (arr_negative[i] != 0){
                    arr_ascend[ascendIndex++] = -i;
                    arr_negative[i]--;
                }
            }
        }

        for(int i=0; i<arr_positive.length; i++){
            if(arr_positive[i] != 0){
                if(mode == arr_positive[i]){
                    arrayList.add(i);
                } else if(mode < arr_positive[i]){
                    arrayList = new ArrayList<>();
                    mode = arr_positive[i];
                    arrayList.add(i);
                }

                while (arr_positive[i] != 0){
                    arr_ascend[ascendIndex++] = i;
                    arr_positive[i]--;
                }
            }
        }

        //오름차순 배열(arr)의 중앙값
        int midIndex = n/2;
        int mid = arr_ascend[midIndex];
        System.out.println(mid);

        if(arrayList.size() == 1){
            System.out.println(arrayList.get(0));
        } else{
            System.out.println(arrayList.get(1));
        }

        //범위
        System.out.println(arr_ascend[arr.length-1] - arr_ascend[0]);
    }
}

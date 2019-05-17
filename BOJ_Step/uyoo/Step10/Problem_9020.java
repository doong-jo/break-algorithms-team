package me.uyoo.GroupPractice.BOJ_Step.Step10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Problem_9020 {
    static ArrayList<Integer> arrayList;
    static int[] results;

    private static boolean findDemical(int num) {
        if(num == 1) return false;
        int num_sqrt = (int) Math.sqrt(num);
        for(int i=2; i<=num_sqrt; i++){
            if(num % i == 0)
                return false;
        }
        return true;
    }

    private static void doPartition(int input) {
        int size = arrayList.size();
        int min = Integer.MAX_VALUE;
        for(int i=0; i<size; i++){
            for(int j=i; j<size; j++){
                int sum = arrayList.get(i) + arrayList.get(j);
                if(sum == input) {
                    results[0] = arrayList.get(i);
                    results[1] = arrayList.get(j);

                    int gap = results[1] - results[0];
                    if(gap < min){
                        results[0] = arrayList.get(i);
                        results[1] = arrayList.get(j);
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCase = Integer.parseInt(br.readLine());
        int t = 0;
        while (t < testCase){
            int input = Integer.parseInt(br.readLine());
            arrayList = new ArrayList<>();
            for(int i=2; i<=input; i++){
                int num = i;
                if(findDemical(num)){
                    arrayList.add(num);
                }
            }

            results = new int[2];
            doPartition(input);

            for(int res : results)
                System.out.print(res + " ");

            System.out.println();

            t++;
        }
    }
}

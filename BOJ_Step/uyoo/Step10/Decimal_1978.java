package me.uyoo.GroupPractice.BOJ_Step.Step10;

import java.util.Scanner;

public class Decimal_1978 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arr = new int[n];

        for(int i=0; i<n; i++){
            arr[i] = scanner.nextInt();
        }

        int cnt = 0;
        for(int i=0; i<n; i++){
            int num = arr[i];
            if(deterDemical(num)){
                cnt++;
            }
        }
        System.out.println(cnt);
    }

    private static boolean deterDemical(int num) {
        if(num == 1) return false;

        int num_sqrt = (int) Math.sqrt(num);
        for(int i=2; i<=num_sqrt; i++){
            if(num % i == 0){
                return false;
            }
        }
        return true;
    }
}

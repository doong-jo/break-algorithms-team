package me.uyoo.GroupPractice.BOJ_Step.Step10;

import java.util.ArrayList;
import java.util.Scanner;

public class Demical_2581 {

    private static boolean findDemical(int num) {
        if(num == 1) return false;

        int num_sqrt = (int)Math.sqrt(num);
        for(int i=2; i<=num_sqrt; i++){
            if(num % i == 0){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int m = scanner.nextInt();

        int sum = 0;
        ArrayList<Integer> arrayList = new ArrayList<>();
        for(int i=n; i<=m; i++){
            int num = i;
            if(findDemical(num)){
                arrayList.add(num);
                sum += num;
            }
        }

        if(sum == 0){
            System.out.println(-1);
            return;
        } else {
            System.out.println(sum);
            System.out.println(arrayList.get(0));
        }
    }
}

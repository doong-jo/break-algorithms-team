package me.uyoo.GroupPractice.BOJ_Step.Step08;

import java.util.Scanner;

public class BeeHouse_2292 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        if(n == 1){
            System.out.println(1);
            return;
        }

        //8, 20, 38,,, 지점에 도달하는 규칙
        int changePoint = 2;
        int k = 1;
        while (changePoint <= n){
            changePoint += 6*(k++);
        }
        System.out.println(k);
    }
}
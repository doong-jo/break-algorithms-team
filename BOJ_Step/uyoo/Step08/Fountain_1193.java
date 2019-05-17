package me.uyoo.GroupPractice.BOJ_Step.Step08;

import java.util.Scanner;

public class Fountain_1193 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt();

        int row = 1, col = 1;
        int cnt = 0;
        String str;
        while (true){
            //오른쪽 대각선으로 이동 -> col+1, row-1
            while (isRanged(row, col)){
                cnt++;
                if(cnt == x){
                    str = String.valueOf(row) + "/" + String.valueOf(col);
                    System.out.println(str);
                    return;
                }
                //오른쪽 위 대각선 이동
                col += 1;
                row -= 1;
            }
            //대각선이 범위가 벗어나면 -> row + 1
            row += 1;

            //왼쪽 대각선으로 이동 -> row + 1, col - 1
            while (isRanged(row, col) && cnt != x){
                cnt++;
                if(cnt == x){
                    str = String.valueOf(row) + "/" + String.valueOf(col);
                    System.out.println(str);
                    return;
                }

                //왼쪽 아래 대각선 이동
                col -= 1;
                row += 1;
            }
            //대각선이 범위가 벗어나면 -> col + 1
            col += 1;
        }
    }

    private static boolean isRanged(int row, int col) {
        if(row > 0 && col > 0) return true;
        return false;
    }
}
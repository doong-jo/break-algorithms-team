package me.uyoo.GroupPractice.Week_07_Step.Step04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PlusCycle_1110 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String n = br.readLine();
        StringBuilder sb = new StringBuilder(n);

        if(n.length() < 2){
            sb.insert(0, "0");
            n = sb.toString();
        }

        String str;
        int cycleCnt = 0;
        while (true){
            int first = Integer.parseInt(sb.substring(0, 1));
            int second = Integer.parseInt(sb.substring(1, 2));
            int sum = first + second;
            String sum_second = Integer.toString(sum);

            //처음 숫자의 두번째 수 + 더해진 숫자의 두번째 수
            str = sb.substring(sb.length()-1, sb.length()) + sum_second.substring(sum_second.length()-1, sum_second.length());
            sb = new StringBuilder(str);
            cycleCnt++;

            if(sb.toString().equals(n)) break;
        }

        System.out.println(cycleCnt);
    }
}

package me.uyoo.GroupPractice.BOJ_Step.Step07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Croatia_2941 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] arr_new = {"c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z="};
        StringBuilder sb;

        String input = br.readLine();
        sb = new StringBuilder(input);

        //arr_new 배열을 넣어보면서 input에 대응되는 값이 있으면 해당 값을 지워주고 cnt++
        int cnt = 0;
        for(int i=0; i<arr_new.length; i++){
            boolean checked = false;
            int index = sb.indexOf(arr_new[i]);
            if(index != -1){
                sb = sb.replace(index, index + arr_new[i].length(), " ");
                cnt++;
                checked = true;
            }

            if(checked) i = -1;
        }

        //이후 남은 값들을 카운팅
        int cnt_nmg = sb.toString().replaceAll(" ", "").length();
        System.out.println((cnt + cnt_nmg));
    }
}
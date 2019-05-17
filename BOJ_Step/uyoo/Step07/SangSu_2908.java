package me.uyoo.GroupPractice.BOJ_Step.Step07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SangSu_2908 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String input = br.readLine();
        st = new StringTokenizer(input, " ");

        int size = st.countTokens();
        String[] arrs = new String[size];
        int[] arrs_num = new int[size];

        //입력받은 문자열을 거꾸로 전환한 숫자로 바꿈
        for(int i=0; i<size; i++){
            arrs[i] = st.nextToken();
            String str = "";
            for(int j=arrs[i].length()-1; j>=0; j--){
                str += arrs[i].substring(j, j+1);
            }
            arrs_num[i] = Integer.parseInt(str);
        }

        int max = 0;
        for(int i=0; i<arrs_num.length; i++){
            if(max < arrs_num[i])
                max = arrs_num[i];
        }
        System.out.println(max);
    }
}

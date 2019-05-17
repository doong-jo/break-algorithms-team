package me.uyoo.GroupPractice.BOJ_Step.Step06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Scale_2920 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        String asc = "12345678";
        String desc = "87654321";

        String input = br.readLine();
        st = new StringTokenizer(input, " ");

        int size = st.countTokens();
        String str = "";
        for(int i=0; i<size; i++){
            str += st.nextToken();
        }

        if(str.equals(asc)){
            System.out.println("ascending");
        } else if(str.equals(desc)){
            System.out.println("descending");
        } else{
            System.out.println("mixed");
        }
    }
}

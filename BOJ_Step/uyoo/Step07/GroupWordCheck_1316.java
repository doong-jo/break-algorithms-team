package me.uyoo.GroupPractice.BOJ_Step.Step07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GroupWordCheck_1316 {

    private static boolean wordChecker(String word) {
        //단어가 바뀌는 순간 해당 단어는 true, 나중에 다시 해당 단어가 나오면 return false;
        boolean[] checked = new boolean[26];

        for(int i=0; i<word.length(); i++){
            int index = word.charAt(i) - 'a';
            if(checked[index]){
                return false;
            }
            if(i < word.length()-1 && !word.substring(i, i+1).equals(word.substring(i+1, i+2))){
                checked[index] = true;
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cnt = 0;
        int wordCnt = Integer.parseInt(br.readLine());

        int w=0;
        while (w < wordCnt){
            String word = br.readLine();
            if(wordChecker(word)){
                cnt++;
            }
            w++;
        }
        System.out.println(cnt);
    }
}

package me.uyoo.GroupPractice.BOJ_Step.Step07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class StudyWord_1157 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] wordCnt = new int[26];

        String input = br.readLine().toUpperCase();
        for(int i=0; i<input.length(); i++){
            int index = input.charAt(i) - 'A';
            wordCnt[index]++;
        }

        //배열 복사 후 내림차순 정렬
       int[] wordCnt_tmp = wordCnt.clone();
        for(int i=0; i<wordCnt_tmp.length-1; i++){
            for(int j=i+1; j<wordCnt_tmp.length; j++){
                if(wordCnt_tmp[i] < wordCnt_tmp[j]){
                    int tmp = wordCnt_tmp[i];
                    wordCnt_tmp[i] = wordCnt_tmp[j];
                    wordCnt_tmp[j] = tmp;
                }
            }
        }

        ArrayList<Integer> arrayList = new ArrayList<>();
        for(int i=0; i<wordCnt.length; i++){
            if(wordCnt[i] != 0 && wordCnt_tmp[0] == wordCnt[i]){
                arrayList.add(i);
            }
        }

        if(arrayList.size() > 1) System.out.println("?");
        else System.out.println((char)(arrayList.get(0) + 'A'));
    }
}

package me.uyoo.GroupPractice.BOJ_Step.Step09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;

public class SortWord_1181 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashSet<String> hashSet = new HashSet<>();
        int n = Integer.parseInt(br.readLine());

        for(int i=0; i<n; i++){
            hashSet.add(br.readLine());
        }

        //hashset을 통해 중복된 입력은 자동 필터링
        int size = hashSet.size();
        String[] words = new String[size];
        Iterator<String> iterator = hashSet.iterator();

        int a = 0;
        while (iterator.hasNext()){
            words[a++] = iterator.next();
        }

        //문자 개수에 따라 오름차순 정렬
        Arrays.sort(words, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.compare(o1.length(), o2.length());
            }
        });

//        for(int i=0; i<size-1; i++){
//            for(int j=i; j<size; j++){
//                if(words[i].length() > words[j].length()){
//                    String tmp = words[i];
//                    words[i] = words[j];
//                    words[j] = tmp;
//                }
//            }
//        }

        //개수가 같은경우 사전식 정렬
        int j = 0;
        for(int i=0; i<words.length; i++){
            int len = words[i].length();
            for(j=i+1; j<words.length; j++){
                if(len != words[j].length()){
                    break;
                }
            }
            Arrays.sort(words, i, j);
            i = j - 1;
        }

        for(String ans : words)
            System.out.println(ans);
    }
}

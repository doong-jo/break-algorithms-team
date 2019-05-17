package me.uyoo.GroupPractice.BOJ_Step.Step08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
* 0~9 아스키코드 형태로 배치
* if 6에 하나가 존재하면 9로, 9에 하나가 존재하면 6
* but 9 or 6에도 존재한다면 set 추가
* */

public class RoomNum_1475 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] nums = new int[10];
        String input = br.readLine();

        int set = 1;
        for(int i=0; i<input.length(); i++){
            int index = input.charAt(i) - 48;

            if(index == 6 && nums[6] == set && nums[9] < set){
                nums[9]++;
            } else if(index == 9 && nums[9] == set && nums[6] < set){
                nums[6]++;
            } else if(nums[index] == set){
                set++;
            }

            if(nums[index] < set){
                nums[index]++;
            }
        }

        System.out.println(set);
    }
}

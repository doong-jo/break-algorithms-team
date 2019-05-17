package me.uyoo.GroupPractice.BOJ_Step.Step09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NumSort_2751 {
    static int[] nums;
    static int[] sortedNums;

    private static void merge(int front, int mid, int rear) {
        int leftIndex = front;
        int rightIndex = mid+1;
        int sortIndex = front;

        while (leftIndex <= mid && rightIndex <= rear){
            if(nums[leftIndex] < nums[rightIndex])
                sortedNums[sortIndex++] = nums[leftIndex++];

            else
                sortedNums[sortIndex++] = nums[rightIndex++];
        }

        while (leftIndex <= mid)
            sortedNums[sortIndex++] = nums[leftIndex++];

        while (rightIndex <= rear)
            sortedNums[sortIndex++] = nums[rightIndex++];

        for(int i=front; i<sortIndex; i++)
            nums[i] = sortedNums[i];
    }

    private static void mergeSort(int front, int rear) {
        int mid;
        if(front < rear){
            mid = (front + rear) / 2;
            mergeSort(front, mid);
            mergeSort(mid+1, rear);
            merge(front, mid, rear);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        nums = new int[n];
        sortedNums = new int[n];

        for(int i=0; i<n; i++)
            nums[i] = Integer.parseInt(br.readLine());

        int front = 0;
        int rear = nums.length-1;
        mergeSort(front, rear);

        for(int ans : nums)
            System.out.println(ans);
    }
}

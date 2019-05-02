package me.uyoo.GroupPractice.Week_07_Step.Step05;

import java.io.*;

public class Star_2448 {
    static char[][] arr;

    private static void makeStar(int row, int col, int n) {
        if(n == 3){
            arr[row][col] = '*';
            arr[row+1][col-1] = arr[row+1][col+1] = '*';
            arr[row+2][col-2] = arr[row+2][col-1] = arr[row+2][col] = arr[row+2][col+1] = arr[row+2][col+2] = '*';

            return;
        }

        makeStar(row, col, n/2);
        makeStar(row + (n/2), col - (n/2), n/2);
        makeStar(row + (n/2), col + (n/2), n/2);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        arr = new char[n][2*n-1];

        for(int i=0; i<n; i++){
            for(int j=0; j<2*n-1; j++){
                arr[i][j] = ' ';
            }
        }

        int row = 0;
        int col = (2*n-1) / 2;
        makeStar(row, col, n);

        for(int i=0; i<n; i++){
            for(int j=0; j<2*n-1; j++){
                bw.write(arr[i][j]);
            }
            bw.write("\n");
        }

        bw.flush();
        bw.close();
        br.close();

    }
}

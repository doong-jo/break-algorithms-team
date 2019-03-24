package sw_ea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class PassWord_5658 {
    private static final int SIDE = 4;      // 4각형이니까 변이 4
    private static final int HEXNUM = 16;   // 16진수를 나타내는 변수
    private static long[] resultArr;        // 패스워드 결과를 담을 long Array

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCase = Integer.parseInt(br.readLine());

        // testCase 만큼 변수 n, k, 비밀번호 후보 Arr, 패스워드 결과 Arr 생성
        int[] n = new int[testCase];
        int[] k = new int[testCase];
        char[][] treasurePass = new char[testCase][];
        resultArr = new long[testCase];

        // testCase 만큼 입력 저장
        for(int i=0; i<testCase; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            n[i] = Integer.parseInt(st.nextToken());
            k[i] = Integer.parseInt(st.nextToken());
            treasurePass[i] = br.readLine().toCharArray();
        }

        savePass(n, k, testCase, treasurePass); // 패스워드를 저장하는 메소드
        printResult(testCase);                  // 결과 출력 메소드
        br.close();
    }

    // 결과 출력 메소드
    private static void printResult(int testCase) {
        for(int i=0; i<testCase; i++) { // testCase 만큼
            System.out.print("#"+(i+1) + " " + resultArr[i]);       // 저장된 패스워드 출력
            System.out.println();                                   // make a new line.
        }
    }

    // passWord 를 저장하는 메소드
    private static void savePass(int[] n, int[] k, int testCase, char[][] tresurePass) {
        for(int i =0; i<testCase; i++) {
//            System.out.println(i + "번 째 테스트 케이스");
            resultArr[i] = makePass(n[i], k[i], i, tresurePass);
        }
    }

    // passWord 를 만들고 10진수로 변환하는 메소드
    private static long makePass(int n, int k, int idx , char[][] tresurePass) {
//        int rotateNum = n/SIDE;
        int passLen =n/SIDE;        // 패스워드 길이 저장.

        long[] passArr = new long[n];    // 비밀번호의 최대 갯수는, 4 * 로테이션 횟수    , 자료형 고민좀 해보자 Set 쓰고싶은데
        int cnt =0;
        for(int i=0; i<passLen; i++) {              // 패스워드 길이만큼 rotate.
            String[] passHex = initStrArr();        // 16진수 비밀번호를 저장할 String Array를 init 메소드를 통해서 초기화.

            // 이제 비밀번호를 만들 차례, 숫자 갯수는 n/4 개
            for(int turn =0; turn < passLen; turn++) {
                passHex[0] += tresurePass[idx][(i*SIDE +turn) %n];          // rotate 될 때 마다의 첫번째 password
                passHex[1] += tresurePass[idx][(i*SIDE +turn+1) %n];        // rotate 될 때 마다의 두번째 password
                passHex[2] += tresurePass[idx][(i*SIDE +turn+2)%n];         // rotate 될 때 마다의 세번째 password
                passHex[3] += tresurePass[idx][(i*SIDE +turn+3)%n];         // rotate 될 때 마다의 네번째 password
            }
            // ex => 0,1,2,3 || 4,5,6,7 || 8,9,10,11 || 12,13,14,15
            //       1,2,3,4 || 2,3,4,5 ~~~~
            //          ........................
            //       3,4,5,6 || 7,8,9,10 || 11,12,13,14 || 15,0,1,2         index로 비밀번호 조합

            for(int j=0; j<SIDE; j++) {         // 저장된 16진수
//                System.out.println(passHex[j]);
                long num = Long.parseLong(passHex[j], HEXNUM);      // 16진수로 변환
                if(!contains(num, passArr)) {                       // 예비 패스워드에 들어있지 않으면
                    passArr[cnt++] = num;                           // 저장
                }
            }
        }
//        System.out.println();
        Arrays.sort(passArr);                                       // Array sorting

        return passArr[n-k];    // k번 째로 가장 큰 수이니까, 오름차순으로 정리된 Arr 에서 n-k 번 째 작은 수가 됨
    }

    // 해당 숫자가 Array에 있는지 여부를 return 하는 메소드
    public static boolean contains(long target, long[] arr) {
        for(long num : arr) {
            if(target == num) {
                return true;
            }
        }
        return false;
    }

    // String Array를 초기화하는 메소드
    public static String[] initStrArr() {
        String[] result = new String[SIDE]; // 비밀번호가 담길 갯수만큼 생성 (4개)

        for(int i=0; i<SIDE; i++) {
            result[i] = "";                 // 각 비밀번호 초기화
        }
        return result;
    }
}

/*
Test cases sample
5
12 10
1B3B3B81F75E
16 2
F53586D76286B2D8
20 14
88F611AE414A751A767B
24 16
044D3EBA6A647B2567A91D0E
28 11
8E0B7DD258D4122317E3ADBFEA99
 */
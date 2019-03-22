/**
 *
 각 변에 다음과 같이 16진수 숫자(0~F)가 적혀 있는 보물상자가 있다.

 보물 상자의 뚜껑은 시계방향으로 돌릴 수 있고, 한 번 돌릴 때마다 숫자가 시계방향으로 한 칸씩 회전한다.



 각 변에는 동일한 개수의 숫자가 있고, 시계방향 순으로 높은 자리 숫자에 해당하며 하나의 수를 나타낸다.

 예를 들어 [Fig.1]의 수는 1A3, B54, 8F9, D66이고, [Fig.2]의 수는 61A, 3B5, 48F, 9D6이다.

 보물상자에는 자물쇠가 걸려있는데, 이 자물쇠의 비밀번호는 보물 상자에 적힌 숫자로 만들 수 있는 모든 수 중, K번째로 큰 수를 10진 수로 만든 수이다.

 N개의 숫자가 입력으로 주어졌을 때, 보물상자의 비밀 번호를 출력하는 프로그램을 만들어보자.

 (서로 다른 회전 횟수에서 동일한 수가 중복으로 생성될 수 있다. 크기 순서를 셀 때 같은 수를 중복으로 세지 않도록 주의한다.)



 [제약 사항]


 N은 4의 배수이고, 8이상 28이하의 정수이다. (8 ≤ N ≤ 28)
 N개의 숫자는 각각 0이상 F이하로 주어진다. (A~F는 알파벳 대문자로만 주어진다.)
 K는 생성 가능한 수의 개수보다 크게 주어지지 않는다.


 [예제]


 아래와 같이 (1, B, 3, B, 3, B, 8, 1, F, 7, 5, E) 12개의 숫자가 주어지고 K가 10인 경우를 살펴보자.
 이 경우에 생성 가능한 수는 각 회전 별로 다음과 같다.

 0회전 : 1B3, B3B, 81F, 75E
 1회전 : E1B, 3B3, B81, F75
 2회전 : 5E1, B3B, 3B8, 1F7
 3회전 : 0회전과 동일

 생성 가능한 수를 내림 차순으로 나열하면 다음과 같고, K(=10)번째로 큰 수는 503(=1F7)이다.
 (B3B를 중복으로 세지 않도록 주의한다.)

 F75, E1B, B81, B3B, 81F, 75E, 5E1, 3B8, 3B3, 1F7, 1B3

 [입력]

 가장 첫 줄에는 테스트 케이스의 개수 T가 주어지고, 그 아래로 각 테스트 케이스가 주어진다.

 각 테스트 케이스의 첫 번째 줄에는 숫자의 개수 N과 크기 순서 K가 주어 진다.

 그 다음 줄에는 16진수 0~F 숫자가 공백 없이 N개 주어진다.

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

 [출력]

 출력의 각 줄은 '#t'로 시작하고, 공백을 한 칸 둔 다음 정답을 출력한다.

 (t는 테스트 케이스의 번호를 의미하며 1부터 시작한다.)

 #0 3957
 #1 11663
 #2 587281
 #3 10897330
 #4 131933581
 */

package swea;

import java.io.IOException;
import java.util.Scanner;

public class TresureBoxPw {
    public static final int MAX_N = 45;
    static int N, K;
    static int[] Digits;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int tcCnt = sc.nextInt();
        char[] temp = new char[MAX_N + 1];
        for (int t = 0; t <= tcCnt; t++) {
            N = sc.nextInt();
            K = sc.nextInt();

            String s = sc.nextLine();
            s = sc.nextLine();
            for (int i1 = 0; i1 < s.length(); i1++) { temp[i1] = s.charAt(i1); }
            Digits = new int[MAX_N];
            for (int i = 0; i < N; i++) {
                if (temp[i] < 'A') Digits[i] = temp[i] - '0';
                else Digits[i] = temp[i] - 'A' + 10;
            }
            for (int a = 0; a < N / 4; a++) {
                Digits[N + a] = Digits[a];
            }

            System.out.printf("#%d %d\n", t, Solution());
        }
    }

    public static int Solution() {
        int[] nums = new int[MAX_N];
        int cnt = 0;
        int len = N / 4;

        for (int i = 0; i < len; ++i) {
            for (int j = 0; j < 4; ++j) {
                int tmp = 0;

                for (int k = 0; k < len; ++k) {
                    tmp *= 16;
                    tmp += Digits[i + j * len + k];
                }

                nums[cnt++] = tmp;
            }
        }

        for (int i = 0; i < cnt - 1; ++i) {
            for (int j = 0; j < i + 1; j++) {
                if( nums[i] < nums[j] ) {
                    int tmp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = tmp;
                }
            }
        }

        int k;
        for (k = 0;  k < K; k++) {
            if ( k > 0 && nums[k] == nums[k - 1]) K++;
        }
        return nums[k - 1];
    }
}

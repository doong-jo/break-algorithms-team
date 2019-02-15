package brute_force;

import java.util.*;

public class Sum_of_subsets {
    static ArrayList<Integer> nums = new ArrayList<>();
    static int N;
    static int S;

    static int result = 0;

    public static void main(String[] args) {
        final Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        S = sc.nextInt();

        for(int i=0; i<N; i++) { nums.add(sc.nextInt()); }

        findSubSet(0, 0, 0);

        System.out.println(result);
    }

    public static void findSubSet(int cnt, int sum, int length) {
        if(cnt == N) {
            result = sum == S && length != 0 ? result + 1 : result;
            return;
        }

        findSubSet(cnt + 1, sum + nums.get(cnt), length + 1);
        findSubSet(cnt + 1, sum, length);
    }
}

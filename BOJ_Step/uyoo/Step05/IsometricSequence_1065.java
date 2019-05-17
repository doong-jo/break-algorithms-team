
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class IsometricSequence_1065 {

    //2자리수까지는 등차수열이 항상 성립 -> true
    //3자리수부터 비교
    private static boolean findIsometric(int index) {
        String str = Integer.toString(index);
        if(str.length() <= 2) return true;

        String[] digits = new String[str.length()];
        for(int i=0; i<str.length(); i++){
            digits[i] = str.substring(i, i+1);
        }
        int sub1 = Integer.parseInt(digits[0]) - Integer.parseInt(digits[1]);
        int sub2 = Integer.parseInt(digits[1]) - Integer.parseInt(digits[2]);

        if(sub1 == sub2) return true;

        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int cnt = 0;
        for(int i=1; i<=n; i++){
            if(findIsometric(i)){
                cnt++;
            }
        }
        System.out.println(cnt);
    }
}

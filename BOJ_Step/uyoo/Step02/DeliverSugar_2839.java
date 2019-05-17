
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DeliverSugar_2839 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int bong_a = 3, bong_b = 5;
        int cnt = 0;

        while (n > 0 && n % bong_b != 0){
            n -= bong_a;
            cnt++;
        }

        if(n < 0) {
            System.out.println(-1);
            return;
        }
        else{
            int mok = n / bong_b;
            cnt += mok;
        }
        System.out.println(cnt);
    }
}

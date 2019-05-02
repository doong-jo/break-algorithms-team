
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class GetDay_1924 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        String[] strs = {"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"};

        String input = br.readLine();
        st = new StringTokenizer(input, " ");

        int month = Integer.parseInt(st.nextToken());
        int day = Integer.parseInt(st.nextToken());
        int tmp = day;

        for(int i=1; i<month; i++){
            if(i == 1 || i == 3 || i == 5 || i == 7 || i == 8 || i == 10 || i == 12){
                tmp += 31;

            } else if(i == 4 || i == 6 || i == 9 || i == 11){
                tmp += 30;

            } else if(i == 2){
                tmp += 28;
            }
        }

        int index = tmp % 7;
        System.out.println(strs[index]);
    }
}

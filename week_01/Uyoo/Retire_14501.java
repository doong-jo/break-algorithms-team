import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Retire_14501 {
    static int day;
    static int max = 0;
    static int[] term;
    static int[] price;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        day = Integer.parseInt(br.readLine());
        term = new int[day];
        price = new int[day];

        for(int i=0; i<day; i++){
            String nInput = br.readLine();
            st = new StringTokenizer(nInput, " ");
            term[i] = Integer.parseInt(st.nextToken());
            price[i] = Integer.parseInt(st.nextToken());
        }
        int index = 0;
        int totalPrice = 0;
        makeSchedule(index, totalPrice);

        System.out.println(max);
    }

    private static void makeSchedule(int index, int totalPrice) {
        /*
        * 1) 해당 일에 상담 진행 -> index + term[index] <= day 이면 스케줄 가능
        * 2) 해당 일에 상담 진행 x -> index + 1을 통해 다음 날 진행하도록, index+1 <= day 이면 스케줄 가능
        * 재귀를 통해 모든 경우의 수 고려
        * */

        if(index >= day){
            max = Math.max(max, totalPrice);
            return;
        }

        //해당 일에 상담 진행
        if(index + term[index] <= day){
            makeSchedule(index + term[index], totalPrice + price[index]);
        }

        //해당 일에 상담을 진행하지 않을 때
        if(index + 1 <= day){
            makeSchedule(index +1, totalPrice);
        }
    }

}

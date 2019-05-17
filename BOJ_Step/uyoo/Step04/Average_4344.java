
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Average_4344 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int[] scores;

        int testCase = Integer.parseInt(br.readLine());
        int t = 0;
        while (t < testCase){
            String input = br.readLine();
            st = new StringTokenizer(input, " ");

            int studentSize = Integer.parseInt(st.nextToken());
            scores = new int[studentSize];
            int sum = 0;
            for(int i=0; i<studentSize; i++){
                scores[i] = Integer.parseInt(st.nextToken());
                sum += scores[i];
            }

            float avg = (float) sum / studentSize;
            int cnt = 0;
            for(int i=0; i<studentSize; i++){
                if(scores[i] > avg){
                    cnt++;
                }
            }
            float ratio = ((float) cnt / studentSize) * 100;
            System.out.printf("%.3f", ratio);
            System.out.println("%");

            t++;
        }

    }
}

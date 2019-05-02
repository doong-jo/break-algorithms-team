
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Average_1546 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        float[] nums;

        int subjectSize = Integer.parseInt(br.readLine());
        nums = new float[subjectSize];
        String input = br.readLine();
        st = new StringTokenizer(input, " ");

        float m = 0;
        for(int i=0; i<subjectSize; i++){
            nums[i] = Integer.parseInt(st.nextToken());
            if(m < nums[i]){
                m = nums[i];
            }
        }

        float sum = 0.0f;
        for(int i=0; i<subjectSize; i++){
            nums[i] = (nums[i] / m) * 100;
            sum += nums[i];
        }

        System.out.println(sum / subjectSize);
    }
}

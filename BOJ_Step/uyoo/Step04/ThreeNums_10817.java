
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ThreeNums_10817 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int[] nums;

        String input = br.readLine();
        st = new StringTokenizer(input, " ");

        int size = st.countTokens();
        nums = new int[size];
        for(int i=0; i<size; i++){
            nums[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0; i<nums.length-1; i++){
            for(int j=i+1; j<nums.length; j++){
                if(nums[i] < nums[j]){
                    int tmp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = tmp;
                }
            }
        }

        System.out.println(nums[1]);
    }
}

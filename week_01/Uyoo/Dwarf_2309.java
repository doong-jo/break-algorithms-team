import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Dwarf_2309 {
    static int remainder;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] dwarf_tall = new int[9];

        int sum_tall = 0;
        for(int i=0; i<dwarf_tall.length; i++){
            dwarf_tall[i] = Integer.parseInt(br.readLine());
            sum_tall += dwarf_tall[i];
        }
        remainder = sum_tall - 100;

        int index=0;
        findDwarf(dwarf_tall, index);

        Arrays.sort(dwarf_tall);

        for(int i=0; i<dwarf_tall.length; i++){
            if(dwarf_tall[i] != 0){
                System.out.println(dwarf_tall[i]);
            }
        }
        br.close();
    }

    private static void findDwarf(int[] dwarf_tall, int index) {
        for(int i=index+1; i<dwarf_tall.length; i++){
            if(dwarf_tall[index] + dwarf_tall[i] == remainder){
                dwarf_tall[index] = 0;
                dwarf_tall[i] = 0;
                return;
            }
        }

        findDwarf(dwarf_tall, index + 1);
    }
}

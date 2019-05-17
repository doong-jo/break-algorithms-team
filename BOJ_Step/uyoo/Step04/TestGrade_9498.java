
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TestGrade_9498 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testGrade = Integer.parseInt(br.readLine());

        if(testGrade >= 90){
            System.out.println("A");
        } else if(testGrade >= 80){
            System.out.println("B");
        } else if(testGrade >= 70){
            System.out.println("C");
        } else if(testGrade >= 60){
            System.out.println("D");
        } else {
            System.out.println("F");
        }
    }
}
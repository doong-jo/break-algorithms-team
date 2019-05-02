import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * 초기에 넣어준 숫자 배치와 같다면 종료
 * */

public class TreasurePassword_5658 {

    static int n, k;
    static LinkedList<String> lists;
    static ArrayList<Integer> convertedNums;
    static boolean check;

    //시계방향 회전
    private static void rotateNums() {
        //마지막 숫자를 받고 첫번째 구간에 넣기
        String rearNum = lists.getLast();
        lists.removeLast();
        lists.addFirst(rearNum);
    }

    //각 변에 할당된 수만큼 10진수로 변환
    private static void convertDemical(int divide){
        int d=0;
        while (d < lists.size()){
            String str = "";
            for(int i=d; i< d+divide; i++){
                str += lists.get(i);
            }
            int convertNum = Integer.parseInt(str, 16);
            if(!convertedNums.contains(convertNum)) {
                convertedNums.add(convertNum);
                check = false;

            } else {
                check = true;   //중복된 값은 넣진 않고 마킹만
            }
            d += divide;
        }
    }

    private static void insertNums(String nums) {
        lists = new LinkedList<>();
        for(int i=0; i<n; i++){
            lists.add(nums.substring(i, i+1));
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int testCase = Integer.parseInt(br.readLine());

        int t = 0;
        while (t < testCase){
            String input = br.readLine();
            st = new StringTokenizer(input, " ");
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            //초기 숫자 값 LinkedList에 삽입
            String nums = br.readLine();
            insertNums(nums);

            //각 변에 할당할 숫자 개수
            int divide = n / 4;

            //10진수로 변환 -> 중복된 값이 담겼는지 -> 회전
            convertedNums = new ArrayList<>();
            check = false;
            while (true){
                convertDemical(divide);

                //초기에 넣어준 숫자 배치와 같다면 종료
                if(check) break;
                rotateNums();
            }

            Collections.sort(convertedNums, new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o2 - o1;
                }
            });
            System.out.println("#" + (t+1) + " " + convertedNums.get(k-1));

            t++;
        }
    }
}
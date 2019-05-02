package me.uyoo.GroupPractice.Week_07_Step.Step05;

public class SelfNumber_4673 {
    static boolean[] checkd = new boolean[10001];

    private static void findSelfNum(int index) {
        String str = Integer.toString(index);
        StringBuilder sb = new StringBuilder(str);
        if(sb.length() < 2){
            sb.insert(0, "0");
        }

        int originNum = Integer.parseInt(sb.toString());
        int sum = 0;
        sum += originNum;
        for(int i=0; i<sb.length(); i++){
            sum += Integer.parseInt(sb.substring(i, i+1));
        }
        if(sum > 10000) return;

        checkd[sum] = true;
    }

    public static void main(String[] args) {
        //1부터 함수에 넣어서 생성자가 존재하면 true, 아니면 false
        for(int i=1; i<=10000; i++)
            findSelfNum(i);

        for(int i=1; i<=10000; i++)
            if(checkd[i] == false)
                System.out.println(i);
    }
}

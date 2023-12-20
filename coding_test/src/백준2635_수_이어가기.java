import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class 백준2635_수_이어가기 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int MAX = Integer.MIN_VALUE;
        ArrayList<Integer> MAX_ARRAY = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            int s = N;
            int e = i;
            ArrayList<Integer> tempList = new ArrayList<>();
            tempList.add(s);
            tempList.add(e);
            int count = 0;
            while (true) {
                int k = s - e;
                if (k < 0)
                    break;
                s = e;
                e = k;
                tempList.add(k);
                count++;
            }
            if(MAX < count){
                MAX = count;
                MAX_ARRAY = (ArrayList<Integer>) tempList.clone();
            }

        }

        System.out.println(MAX+2);
        for (Integer integer : MAX_ARRAY) {
            System.out.print(integer+ " ");
        }

    }
}

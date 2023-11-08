import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 백준1920_수_찾기 {
    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int N = Integer.parseInt(stringTokenizer.nextToken());

        int[] arr = new int[N];
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(stringTokenizer.nextToken());
        }


        Arrays.sort(arr);


        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int M = Integer.parseInt(stringTokenizer.nextToken());
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < M; i++) {
            boolean find = false;
            int target = Integer.parseInt(stringTokenizer.nextToken());
            int start = 0;
            int end = arr.length -1;

            while(start <= end){
                int mid_index = (start+end)/2;
                int mid_value = arr[mid_index];
                if(mid_value > target){
                    end = mid_index - 1;
                }else if (mid_value < target){
                    start = mid_index +1;
                }else {
                    find = true;
                    break;
                }
            }
            if(find)
                System.out.println(1);
            else
                System.out.println(0);
        }
    }

}


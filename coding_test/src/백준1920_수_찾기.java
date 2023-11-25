import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.nio.Buffer;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 백준1920_수_찾기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        int arr[] = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());


        for (int i = 0; i < M; i++) {
            int target = Integer.parseInt(st.nextToken());
            int start = 0;
            int end = N-1;
            boolean find = false;
            while(start <= end){
                int midIndex = (start+end)/2;
                int midValue = arr[midIndex];

                if(target < midValue){
                    end = midIndex-1;
                }else if (midValue < target){
                    start = midIndex+1;
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


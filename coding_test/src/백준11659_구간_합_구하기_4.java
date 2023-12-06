import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class 백준11659_구간_합_구하기_4 {

    private static int[] arr;
    private static int[] sum;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int size = Integer.parseInt(st.nextToken());
        int count = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        arr = new int[size+1];
        sum = new int[size+1];
        sum[0] = 0;
        for (int i = 1; i < size+1; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            sum[i] = sum[i-1] + arr[i];
        }

        for (int v = 0; v < count; v++) {
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());
            int subSum = sum[j] - sum[i - 1];
            System.out.println(subSum);
        }
    }
}


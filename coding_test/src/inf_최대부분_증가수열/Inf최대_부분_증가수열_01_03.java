package inf_최대부분_증가수열;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Inf최대_부분_증가수열_01_03 extends RuntimeException{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());


        int input[] = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }

        int dp[] = new int[N];
        dp[N - 1] = 1;
        for (int i = N - 2; i >= 0; i--) {
            int largestAfterMe = findLargestAfterMe(N, input, dp, i);
            dp[i] = largestAfterMe+1;
        }

        System.out.println(Arrays.stream(dp).max().getAsInt());
    }

    private static int findLargestAfterMe(int N, int[] input, int[] dp, int i) {
        int max = 0;
        for (int j = i + 1; j < N; j++) {
            if (input[i] < input[j]) {
                max = Math.max(max, dp[j]);
            }
        }
        return max;
    }
}

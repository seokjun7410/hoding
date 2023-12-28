package inf_최대점수_구하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class inf_최대점수_구하기_12_27 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        long dp[] = new long[T+1];
        int input[][] = new int[2][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int score = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            input[0][i] = time;
            input[1][i] = score;
        }


        for (int i = 0; i < N; i++) {
            for (int j = T; j > 0; j--) {
                int time = input[0][i];
                int score = input[1][i];
                int remain = j - time;
                if(remain >= 0) {
                    dp[j] = Math.max(dp[remain]+ score,dp[j]);
                }
            }
        }

        System.out.println(dp[T]);
    }

}
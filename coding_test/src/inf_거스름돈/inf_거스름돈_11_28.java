package inf_거스름돈;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class inf_거스름돈_11_28 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int coin[] = new int[N];
        for (int i = 0; i < N; i++) {
            coin[i] = Integer.parseInt(st.nextToken());
        }

        int change = Integer.parseInt(br.readLine());
        int dp[] = new int[change+1];
        Arrays.fill(dp,Integer.MAX_VALUE-1);
        dp[0] = 0;

        for (int i = 0; i < N; i++) {
            int currentCoin = coin[i];
            for (int j = currentCoin; j < change+1; j++) {
                     dp[j] = Math.min(dp[j-currentCoin] + 1,dp[j]);
            }
        }

        System.out.println(dp[change]);
    }
}
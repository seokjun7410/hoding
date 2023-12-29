
import java.util.Arrays;

public class 프로그래머스_N으로표현 {
    static int N = 5;
    static int number = 12;

    public static void main(String[] args) {

        int dp[] = new int[number + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        for (int i = 0; i < N; i++) {
            dp[i] = i * 2;
        }
        dp[5] = 1;
        dp[11] = 3;
        dp[10] = 5;

        for (int i = N; i <= number; i++) {

            for (int j = 1; j < i; j++) {
                int a = dp[i - j] + dp[j];
                dp[i] = Math.min(a, dp[i]);
            }

        }

        int result = dp[number];
        if (result > 8)
            result = -1;
        System.out.println(result);
    }


}

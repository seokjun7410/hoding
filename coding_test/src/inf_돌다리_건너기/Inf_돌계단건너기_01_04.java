package inf_돌다리_건너기;


import java.util.Scanner;

public class Inf_돌계단건너기_01_04 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();

        int[] dp = new int[N+1];
        dp[0] = 1;
        dp[1] = 2;
        for (int i = 2; i < N+1; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }

        System.out.println(dp[N]);
    }
}

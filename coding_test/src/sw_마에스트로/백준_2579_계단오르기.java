package sw_마에스트로;

import java.util.Scanner;

public class 백준_2579_계단오르기 {

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();

		int[] input = new int[n + 1];
		for (int i = 1; i < n + 1; i++) {
			input[i] = sc.nextInt();
		}

		int[] dp = new int[n + 1];
		dp[0] = 0;
		dp[1] = input[1];
		if (n >= 2) {
			dp[2] = input[1] + input[2];
		}
		for (int i = 3; i < n + 1; i++) {
			dp[i] = Math.max(dp[i - 2], dp[i - 3] + input[i - 1]) + input[i];
		}

		System.out.println(dp[n]);

	}

}

package sw_마에스트로;

import java.util.Scanner;

public class 백준9095_123 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();

		int[] input = new int[n];
		int max = 0;
		for (int i = 0; i < n; i++) {
			int value = sc.nextInt();
			input[i] = value;
			max = Math.max(max, value);
		}

		int[] dp = new int[max + 1];
		dp[0] = 0;
		dp[1] = 1;
		if (n >= 2) {
			dp[2] = 2;
		}
		if (n >= 3) {
			dp[3] = 4;
		}
		for (int i = 4; i < max + 1; i++) {
			dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
		}

		for (int i : input) {
			System.out.println(dp[i]);
		}
	}

}


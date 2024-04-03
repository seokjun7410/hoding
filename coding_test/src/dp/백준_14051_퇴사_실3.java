package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class 백준_14051_퇴사_실3 {

	private static int[][] input;
	private static int result = 0;
	private static int M;
	private static int[] dp;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		M = Integer.parseInt(br.readLine());

		StringTokenizer st;
		dp = new int[M + 1];
		input = new int[M][2];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			input[i][0] = Integer.parseInt(st.nextToken());
			input[i][1] = Integer.parseInt(st.nextToken());
		}

		recursive(0);
		System.out.println(dp[0]);
	}

	private static int recursive(int L) {
		if (L == M) {
//			result = Math.max(result);
			return 0;
		}
		if (L > M) {
			return Integer.MIN_VALUE;
		}

		if (dp[L] != 0) {
			return dp[L];
		}

		int time = input[L][0];
		int money = input[L][1];

		return dp[L] = Math.max(recursive(L + time) + money, recursive(L + 1));

	}
}

package sw_마에스트로;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 백준_1149_RGB거리 {

	private static final int RED = 0;
	private static final int GREEN = 1;
	private static final int BLUE = 2;


	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		StringTokenizer st;
		int[][] input = new int[n][3];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			input[i][RED] = Integer.parseInt(st.nextToken());
			input[i][GREEN] = Integer.parseInt(st.nextToken());
			input[i][BLUE] = Integer.parseInt(st.nextToken());
		}

		int[][] dp = new int[n][3];
		dp[0][RED] = input[0][RED];
		dp[0][GREEN] = input[0][GREEN];
		dp[0][BLUE] = input[0][BLUE];
		for (int i = 1; i < n; i++) {
			dp[i][RED] = Math.min(dp[i - 1][GREEN], dp[i - 1][BLUE]) + input[i][RED];
			dp[i][GREEN] = Math.min(dp[i - 1][RED], dp[i - 1][BLUE]) + input[i][GREEN];
			dp[i][BLUE] = Math.min(dp[i - 1][GREEN], dp[i - 1][RED]) + input[i][BLUE];
		}

		int result = Arrays.stream(dp[n - 1]).min().getAsInt();
		System.out.println(result);
	}

}

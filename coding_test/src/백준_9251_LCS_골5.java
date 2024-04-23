import java.util.Scanner;

public class 백준_9251_LCS_골5 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		char[] N = sc.next().toCharArray();
		char[] M = sc.next().toCharArray();

		int dp[][] = new int[N.length + 1][M.length + 1];
		for (int i = 1; i < N.length + 1; i++) {
			for (int j = 1; j < M.length + 1; j++) {
				if (N[i - 1] == M[j - 1]) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
				} else {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				}
			}
		}

		System.out.println(dp[N.length][M.length]);
	}

}

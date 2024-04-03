package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 백준_1937_욕심쟁이판다_골3 {

	private static int[] dx = {0, 0, 1, -1};
	private static int[] dy = {1, -1, 0, 0};
	private static int input[][];
	private static int N;
	private static int[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		StringTokenizer st;
		input = new int[N][N];
		dp = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				input[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				recursive(i, j);
			}
		}

		System.out.println(Arrays.stream(dp).flatMapToInt(Arrays::stream).max().getAsInt() + 1);
	}

	private static int recursive(int x, int y) {

		if (dp[x][y] != 0) {
			return dp[x][y];
		}

		for (int i = 0; i < 4; i++) {
			int nextX = x + dx[i];
			int nextY = y + dy[i];
			if (nextX >= 0 && nextY >= 0 && nextY < N && nextX < N
				&& input[x][y] < input[nextX][nextY]) {
				dp[x][y] = Math.max(dp[x][y], recursive(nextX, nextY) + 1);
			}
		}

		return dp[x][y];
	}
}

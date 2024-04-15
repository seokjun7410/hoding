import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_1520_내리막길_골3 {

	private static int[][] input;
	private static int N;
	private static int M;
	private static int[] dx = {0, 0, 1, -1};
	private static int[] dy = {1, -1, 0, 0};
	private static int[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		input = new int[M][N];
		dp = new int[M][N];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				input[i][j] = Integer.parseInt(st.nextToken());
				dp[i][j] = -1;
			}
		}

		int recursive = recursive(0, 0);
		System.out.println(recursive);
	}

	private static int recursive(int x, int y) {

		if (dp[x][y] != -1) {
			return dp[x][y];
		}

		if (x == M - 1 && y == N - 1) {
			return 1;
		}

		int route = 0;
		for (int i = 0; i < 4; i++) {
			int nextX = x + dx[i];
			int nextY = y + dy[i];
			if (nextX >= 0 && nextY >= 0 && nextX < M && nextY < N) {
				if (input[x][y] > input[nextX][nextY]) {
					route += recursive(nextX, nextY);
				}
			}
		}

		return dp[x][y] = route;
	}
}

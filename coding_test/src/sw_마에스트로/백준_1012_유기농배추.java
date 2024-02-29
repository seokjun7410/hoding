package sw_마에스트로;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_1012_유기농배추 {

	static int[][] table;
	static int dx[] = {0, 0, -1, 1};
	static int dy[] = {-1, 1, 0, 0};
	static boolean[][] visited;
	static int N;
	static int M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;

		for (int i = 0; i < T; i++) {

			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			visited = new boolean[N][M];
			table = new int[N][M];
			for (int j = 0; j < K; j++) {
				st = new StringTokenizer(br.readLine());
				int o1 = Integer.parseInt(st.nextToken());
				int o2 = Integer.parseInt(st.nextToken());
				table[o2][o1] = 1;
			}

			solution();
		}
	}

	private static void solution() {
		int count = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (!visited[i][j] && table[i][j] == 1) {
					count++;
					DFS(i, j, 0);
				}
			}
		}

		System.out.println(count);

	}

	private static void DFS(int i, int j, int depth) {
		visited[i][j] = true;

		for (int k = 0; k < 4; k++) {
			int newI = i + dy[k];
			int newJ = j + dx[k];
			if (canMove(newI, newJ) && !visited[newI][newJ]) {
				DFS(newI, newJ, depth + 1);
			}
		}
	}

	private static boolean canMove(int i, int j) {
		return i >= 0 && j >= 0 && i < N && j < M && table[i][j] == 1;
	}

}

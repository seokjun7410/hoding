package inf_섬나라;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class inf_섬나라_01_22 {

	static int di[] = {0, 0, -1, 1, 1, -1, 1, -1};
	static int dj[] = {1, -1, 0, 0, 1, 1, -1, -1};
	static int N;
	static int[][] map;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		StringTokenizer st;
		map = new int[N][N];
		visited = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int result = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j] && map[i][j] == 1) {
					result++;
					DFS(i, j);
				}
			}
		}

		System.out.println(result);
	}

	private static void DFS(int i, int j) {
		if (visited[i][j]) {
			return;
		}

		visited[i][j] = true;

		for (int k = 0; k < 8; k++) {
			int newI = i + di[k];
			int newJ = j + dj[k];
			if (isLinkedIsland(newI, newJ)) {
				DFS(newI, newJ);
			}
		}
	}

	private static boolean isLinkedIsland(int i, int j) {
		return i >= 0 && j >= 0 && i < N && j < N && map[i][j] == 1;
	}
}

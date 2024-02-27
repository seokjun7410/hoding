package sw_마에스트로;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 백준_2667_단자번호붙이기 {

	static boolean[][] visited;
	static int[][] input;
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	static int n;
	static List<Integer> counts = new ArrayList<>();
	static int count = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());

		StringTokenizer st;
		input = new int[n][n];
		visited = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			char[] charArray = st.nextToken().toCharArray();
			for (int j = 0; j < n; j++) {
				input[i][j] = Integer.parseInt(String.valueOf(charArray[j]));
			}
		}

		int cc = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (canMove(i, j) && !visited[i][j]) {
					cc++;
					DFS(i, j, 1);
					counts.add(count);
					count = 0;
				}
			}
		}

		System.out.println(cc);
		counts.sort((o1, o2) -> o1 - o2);
		for (Integer integer : counts) {
			System.out.println(integer);
		}
	}

	private static void DFS(int i, int j, int c) {
		count = Math.max(c, count);
		visited[i][j] = true;

		for (int k = 0; k < 4; k++) {
			int newI = i + dx[k];
			int newJ = j + dy[k];
			if (canMove(newI, newJ) && !visited[newI][newJ]) {
				DFS(newI, newJ, count + 1);
			}
		}
	}

	private static boolean canMove(int newI, int newJ) {
		return newI >= 0 && newJ >= 0 && newI < n && newJ < n && input[newI][newJ] != 0;
	}
}

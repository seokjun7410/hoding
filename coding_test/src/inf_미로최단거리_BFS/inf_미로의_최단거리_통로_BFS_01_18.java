package inf_미로최단거리_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class inf_미로의_최단거리_통로_BFS_01_18 {

	static int di[] = {-1, 0, 0, 1};
	static int dj[] = {0, 1, -1, 0};
	static int[][] miro;
	static boolean visited[][];
	static int N;
	static int M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		miro = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			char[] charArray = st.nextToken().toCharArray();
			for (int j = 0; j < M; j++) {
				miro[i][j] = (charArray[j] - '0');
			}

		}
		visited = new boolean[N][M];
		Queue<MyNode> queue = new LinkedList<>();
		queue.add(new MyNode(0, 0));
		visited[0][0] = true;

		while (!queue.isEmpty()) {
			MyNode now = queue.poll();
			int i = now.i;
			int j = now.j;
			for (int k = 0; k < 4; k++) {
				int newI = i + di[k];
				int newJ = j + dj[k];
				if (canMove(newI, newJ)) {
					if (!visited[newI][newJ]) {
						queue.add(new MyNode(newI,newJ));
						miro[newI][newJ] = miro[i][j] + 1;
						visited[newI][newJ] = true;
					}
				}
			}
		}

//		int x = miro[N - 1][M - 1] == 0 ? -1 : miro[N - 1][M - 1];
		System.out.println(miro[N-1][M-1]);

	}

	private static boolean canMove(int newI, int newJ) {
		return newI >= 0 && newJ >= 0 && newI < N && newJ < M && miro[newI][newJ] != 0;
	}
	static class MyNode {

		int i;
		int j;

		public MyNode(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}

}

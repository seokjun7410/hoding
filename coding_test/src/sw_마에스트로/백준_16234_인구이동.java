package sw_마에스트로;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 백준_16234_인구이동 {

	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {1, -1, 0, 0};
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());

		int map[][] = new int[N][N];
		boolean visited[][] = new boolean[N][N];

		Queue<Node> queue = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int value = Integer.parseInt(st.nextToken());
				map[i][j] = value;
				queue.add(new Node(i, j, value));
			}
		}

		int total = 0;
		int count = 0;
		int day = 0;
		int transactionCount = 0;
		int queueSize = queue.size();
		int failCount = 0;
		boolean breakValue = true;
		ArrayList<Node> groupIndexList = new ArrayList<>();
		while (!queue.isEmpty()) {
			Node now = queue.poll();
			transactionCount++;
			int x = now.x;
			int y = now.y;
			int value = map[x][y];

			boolean done = false;
			for (int i = 0; i < 4; i++) {
				int newX = x + dx[i];
				int newY = y + dy[i];
				if (validate(newX, newY)) {
					int adjacenyValue = map[newX][newY];
					int abs = Math.abs(value - adjacenyValue);
					if (L <= abs & abs <= R) {

						if (!visited[x][y]) {
							groupIndexList.add(now);
							count++;
							total += value;
							visited[x][y] = true;
						}
						if (!visited[newX][newY]) {
							groupIndexList.add(new Node(newX, newY, adjacenyValue));
							count++;
							total += adjacenyValue;
							visited[newX][newY] = true;
						}

						done = true;
					}
				}
			}

			if (!done) {
				failCount++;
				queue.offer(now);
			}

			if (queueSize == transactionCount) {
				if (queueSize == failCount) {
					break;
				}
				int avg = total / count;

				for (Node node : groupIndexList) {
					map[node.x][node.y] = avg;
				}
				total = 0;
				count = 0;
				groupIndexList = new ArrayList<>();
				visited = new boolean[N][N];
				queueSize = queue.size();
				transactionCount = 0;
				failCount = 0;
				day++;
			}
		}

		System.out.println(day);
	}

	private static boolean validate(int x, int y) {
		return 0 <= x && x < N && 0 <= y && y < N;
	}

	private static class Node {

		int x;
		int y;
		int value;

		public Node(int x, int y, int value) {
			this.x = x;
			this.y = y;
			this.value = value;
		}
	}
}

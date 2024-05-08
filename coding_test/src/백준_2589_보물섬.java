import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 백준_2589_보물섬 {

	private static int[] dx = {0, 0, 1, -1};
	private static int[] dy = {1, -1, 0, 0};
	private static int N;
	private static int M;
	private static char[][] input;


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		input = new char[N][M];
		for (int i = 0; i < N; i++) {
			char[] charArray = br.readLine().toCharArray();
			input[i] = charArray;
		}

		Queue<Node> queue;
		int max = 0;
		boolean[][] visited;
		int[][] distance;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (input[i][j] == 'W') {
					continue;
				}
				queue = new LinkedList<>();
				visited = new boolean[N][M];
				distance = new int[N][M];
				queue.add(new Node(i, j));
				visited[i][j] = true;

				while (!queue.isEmpty()) {
					Node now = queue.poll();
					int ox = now.x;
					int oy = now.y;
					for (int k = 0; k < 4; k++) {
						int cx = ox + dx[k];
						int cy = oy + dy[k];
						if (available(cx, cy)) {
							if (!visited[cx][cy]) {
								visited[cx][cy] = true;
								queue.add(new Node(cx, cy));
								distance[cx][cy] = distance[ox][oy] + 1;
								max = Math.max(max, distance[cx][cy]);
							}
						}
					}
				}
			}
		}

		System.out.println(max);
	}

	private static boolean available(int x, int y) {
		return x >= 0 && y >= 0 && x < N && y < M && input[x][y] != 'W';
	}

	private static class Node {

		int x;
		int y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}

package sw_마에스트로;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 백준_1738_골목길 {

	private static final int HOME = 1;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		ArrayList<Edge> edges = new ArrayList<Edge>();
		long[] money = new long[N + 1];
		for (int i = 0; i < N + 1; i++) {
			money[i] = Integer.MIN_VALUE;
		}
		money[HOME] = 0;

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			edges.add(new Edge(s, e, w));
		}

		int[][] path = new int[N + 1][2];
		for (int i = 0; i < N * 2; i++) {
			for (int j = 0; j < M; j++) {
				Edge edge = edges.get(j);
				int s = edge.s;
				int e = edge.e;
				int w = edge.w;

				if (money[s] == Long.MAX_VALUE) {
					money[e] = Long.MAX_VALUE;
				} else if (money[s] != Integer.MIN_VALUE) {
					if (money[e] < money[s] + w) {
						if (i > N - 1) {
							money[e] = Long.MAX_VALUE;
						} else {
							money[e] = money[s] + w;
							path[e][0] = s;
							path[e][1] = e;
						}
					}
				}
			}
		}

		if (money[N] == Long.MAX_VALUE || money[N] == Integer.MIN_VALUE) {
			System.out.println(-1);
		} else {
			boolean end = false;
			int kepp = 0;
			for (int i = HOME, k = 0; k < N; k++) {
				for (int j = 0; j < N + 1; j++) {
					if (i == path[j][0]) {
						System.out.print(i + " ");
						i = path[j][1];
						kepp = j;
						if (i == N) {
							end = true;
						}
						break;
					}
				}
				if (end) {
					break;
				}
			}
			System.out.print(path[kepp][1]);

		}
	}

	private static class Edge {

		int s;
		int e;
		int w;

		public Edge(int s, int e, int w) {
			this.s = s;
			this.e = e;
			this.w = w;
		}
	}
}

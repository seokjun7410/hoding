package sw_마에스트로;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 백준_1219_오만식의_고민 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		ArrayList<Edge> edges = new ArrayList<Edge>();
		int[] cityCell = new int[N + 1];
		long[] distance = new long[N + 1];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			edges.add(new Edge(s, e, w));
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			cityCell[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < N; i++) {
			distance[i] = Integer.MIN_VALUE;
		}
		distance[start] = 0;

		for (int useEdge = 0; useEdge < N * 2; useEdge++) {
			for (int i = 0; i < M; i++) {
				Edge now = edges.get(i);
				int s = now.s;
				int e = now.e;
				int w = cityCell[s] - now.w;

				if (distance[s] == Long.MAX_VALUE) {
					distance[e] = Long.MAX_VALUE;
				} else if (distance[s] != Integer.MIN_VALUE) {
					if (distance[e] < distance[s] + w) {
						if (useEdge > N - 1) {
							distance[e] = Long.MAX_VALUE;
						} else {
							distance[e] = distance[s] + w;
						}
					}
				}
			}
		}

		if (distance[end] == Long.MAX_VALUE) {
			System.out.println("Gee");
		} else if (distance[end] == Integer.MIN_VALUE) {
			System.out.println("gg");
		} else {
			System.out.println(cityCell[end] + distance[end]);
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
//5 0 2 5
//0 1 2
//1 2 2
//3 1 1
//3 4 1
//4 3 1
//0 0 0 1 8

//4 1 3 4
//0 1 0
//0 1 100000
//1 2 3
//2 3 4
//2 2 2 2


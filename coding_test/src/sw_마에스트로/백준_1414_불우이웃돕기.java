package sw_마에스트로;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class 백준_1414_불우이웃돕기 {

	private static int[] unionFind;
	private static boolean[] visited;


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		PriorityQueue<Edge> queue = new PriorityQueue<>();
		ArrayList<Edge> edges = new ArrayList<Edge>();
		visited = new boolean[N];
		unionFind = new int[N];
		for (int i = 0; i < N; i++) {
			unionFind[i] = i;
		}

		int total = 0;
		for (int k = 0; k < N; k++) {
			char[] charArray = br.readLine().toCharArray();
			for (int i = 0; i < N; i++) {

				int num = charArray[i];
				if (97 <= num && num <= 122) {
					num = num - 96;
				} else if (65 <= num && num <= 90) {
					num = num - 38;
				} else {
					continue;
				}
				total += num;
				if (k == i) {
					continue;// need check
				}
				Edge edge = new Edge(k, i, num);
				edges.add(edge);
				queue.add(edge);
			}
		}

		int sum = 0;
		boolean fail = false;
		for (int useEdge = 0; useEdge < N - 1; ) {
			if (queue.isEmpty()) {
				fail = true;
				break;
			}
			Edge now = queue.poll();
			int s = now.s;
			int e = now.e;
			int w = now.w;

			if (!isCycle(s, e)) {
				union(s, e);
				sum += w;
				useEdge++;
			}
		}

		if (fail) {
			System.out.println(-1);
		} else {
			System.out.println(total - sum);
		}

	}

	private static void union(int s, int e) {
		int parentS = find(s);
		int parentE = find(e);
		unionFind[parentE] = parentS;
	}

	private static boolean isCycle(int s, int e) {
		return find(s) == find(e);
	}

	private static int find(int value) {
		if (unionFind[value] == value) {
			return value;
		}
		return unionFind[value] = find(unionFind[value]);
	}

	private static class Edge implements Comparable<Edge> {

		int s;
		int e;
		int w;

		public Edge(int s, int e, int w) {
			this.s = s;
			this.e = e;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o1) {
			return this.w - o1.w;
		}
	}
}

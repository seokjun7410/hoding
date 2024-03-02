package sw_마에스트로;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 백준_1197_최소_스패닝_트리 {

	private static int[] unionFind;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		unionFind = new int[V + 1];
		for (int i = 1; i < V + 1; i++) {
			unionFind[i] = i;
		}
		PriorityQueue<Edge> edges = new PriorityQueue<>();
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			edges.add(new Edge(s, e, w));
		}

		int result = 0;
		int useEdge = 0;
		while (useEdge < V - 1) {
			Edge edge = edges.poll();
			int start = edge.s;
			int end = edge.e;
			if (!isCycle(start, end)) {
				union(start, end);
				result += edge.w;
				useEdge++;
			}
		}

		System.out.println(result);
	}

	private static boolean isCycle(int start, int end) {
		return find(start) == find(end);
	}

	private static void union(int start, int end) {
		int startParent = find(start);
		int endParent = find(end);
		unionFind[startParent] = endParent;
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
			return w - o1.w;
		}
	}
}

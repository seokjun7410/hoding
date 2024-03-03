package sw_마에스트로;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 백준_2211_네트워크_복구 {

	private static ArrayList<Node>[] adjacency;
	private static boolean[] visited;
	private static int[] distance;


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		distance = new int[N + 1];
		for (int i = 0; i < N + 1; i++) {
			distance[i] = Integer.MAX_VALUE;
		}
		visited = new boolean[N + 1];
		adjacency = new ArrayList[N + 1];
		for (int i = 0; i < N + 1; i++) {
			adjacency[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			adjacency[s].add(new Node(s, e, w));
			adjacency[e].add(new Node(e, s, w));
		}

		distance[1] = 0;
		visited[1] = true;
		PriorityQueue<Node> queue = new PriorityQueue<>();
		queue.add(new Node(1, 1, 0));
		ArrayList<Node> path = new ArrayList<>();
		while (!queue.isEmpty()) {
			Node now = queue.poll();
			int e = now.e;
			if (!visited[e]) {
				visited[e] = true;
				path.add(now);
			}

			for (Node next : adjacency[e]) {
				int nextE = next.e;
				int nextW = next.w;
				if (!visited[nextE]) {
					if (distance[nextE] > distance[e] + nextW) {
						distance[nextE] = distance[e] + nextW;
						queue.add(new Node(e, nextE, distance[nextE]));
					}
				}
			}

		}

		System.out.println(path.size());
		for (Node node : path) {
			System.out.println(node.s + " " + node.e);
		}
	}

	private static class Node implements Comparable<Node> {

		int s;
		int e;
		int w;

		public Node(int s, int e, int w) {
			this.s = s;
			this.e = e;
			this.w = w;
		}

		@Override
		public int compareTo(Node o1) {
			return w - o1.w;
		}
	}
}

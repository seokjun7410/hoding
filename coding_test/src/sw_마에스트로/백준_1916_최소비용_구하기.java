package sw_마에스트로;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 백준_1916_최소비용_구하기 {

	private static int n;
	private static int m;
	private static ArrayList<Node>[] adjacencyList;
	private static boolean[] visited;
	private static int[] distance;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine()); // 정점 개수
		m = Integer.parseInt(br.readLine()); // 엣지 개수
		visited = new boolean[n + 1];
		adjacencyList = new ArrayList[n + 1];
		for (int i = 1; i <= n; i++) {
			adjacencyList[i] = new ArrayList<>();
		}
		distance = new int[n + 1];
		Arrays.fill(distance, Integer.MAX_VALUE);

		StringTokenizer st;
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			adjacencyList[s].add(new Node(e, w));
		}

		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
//		System.out.println("start = " + start);
//		System.out.println("end = " + end);

		distance[start] = 0;

		PriorityQueue<Node> queue = new PriorityQueue<>();
		queue.offer(new Node(start, 0));

		while (!queue.isEmpty()) {
			Node now = queue.poll();
			int vertex = now.vertex;

			//queue에서 보관중일 때 방문처리가 될 수 있음
			if (!visited[vertex]) {
				visited[vertex] = true;

				for (Node next : adjacencyList[vertex]) {
					int nextVertex = next.vertex;
					int nextWeight = next.weight;
					//업데이트 방식에 대한 이해가 잘못되어 있음
					if (!visited[nextVertex]) {
						if (distance[nextVertex] > distance[vertex] + nextWeight) {
							distance[nextVertex] = distance[vertex] + nextWeight;
							queue.offer(new Node(nextVertex, distance[nextVertex]));
						}
					}
				}
			}
		}

//		for (long l : distance) {
//			System.out.print(l + "\t");
//		}

		System.out.println(distance[end]);

	}

	private static class Node implements Comparable<Node> {

		int vertex;
		int weight;

		private Node(int vertex, int weight) {
			this.vertex = vertex;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o1) {
			return this.weight - o1.weight;
		}
	}
}

//9
//8
//1 2 99999
//2 3 99999
//3 4 99999
//4 5 99999
//5 6 99999
//6 7 99999
//7 8 99999
//8 9 99999
//1 9

//9
//8
//1 2 99999
//2 3 99999
//3 4 99999
//4 5 99999
//5 6 99999
//6 7 99999
//7 8 99999
//8 9 99999
//1 9

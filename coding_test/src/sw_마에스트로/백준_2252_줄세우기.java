package sw_마에스트로;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 백준_2252_줄세우기 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		int[] indegree = new int[n + 1];
		ArrayList<Integer>[] adjacency = new ArrayList[n + 1];
		for (int i = 1; i < n + 1; i++) {
			adjacency[i] = new ArrayList<>();
		}

		for (int i = 1; i <= m; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			adjacency[a].add(b);
			indegree[b]++;
		}

		Queue<Integer> queue = new LinkedList<>();

		for (int i = 1; i <= n; i++) {
			if (indegree[i] == 0) {
				queue.add(i);
			}
		}

		StringBuilder sb = new StringBuilder();
		while (!queue.isEmpty()) {

			int now = queue.poll();
			sb.append(now).append("\t");
			for (int next : adjacency[now]) {
				int nextIndegree = --indegree[next];
				if (nextIndegree == 0) {
					queue.add(next);
				}
			}
		}

		System.out.println(sb);
	}

	private static boolean end(int n, boolean[] visited) {
		for (int i = 1; i < n + 1; i++) {
			if (!visited[i]) {
				return false;
			}
		}

		return true;
	}
}

package sw_마에스트로;

import java.util.Scanner;

public class 백준_15650_N과m_2 {

	static int[] arr;
	static int n;
	static int[] result;
	static boolean[] visited;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		int m = sc.nextInt();
		arr = new int[n + 1];
		visited = new boolean[n + 1];
		for (int i = 1; i <= n; i++) {
			arr[i] = i;
		}
		result = new int[m];
		for (int i = 1; i <= n; i++) {
			DFS(i, m, 0);
		}
	}

	private static void DFS(int v, int maxDepth, int currentDepth) {
		result[currentDepth] = v;

		if (currentDepth == maxDepth - 1) {
			for (int k : result) {
				if (k != 0) {
					System.out.print(k + " ");
				}
			}
			System.out.println();
			return;
		}
		for (int i = v + 1; i <= n; i++) {
			int v1 = arr[i];
			DFS(v1, maxDepth, currentDepth + 1);
		}

	}
}

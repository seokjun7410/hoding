package 완전탐색;

import java.util.Scanner;

public class 백준_15649_N과_M1_실3 {

	private static int[] arr;
	private static int n;
	private static int m;
	private static boolean[] visited;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		m = sc.nextInt();

		visited = new boolean[n + 1];
		arr = new int[m + 1];
		recursive(0);
	}

	private static void recursive(int L) {
		if (L == m) {
			for (int i = 0; i < m; i++) {
				System.out.print(arr[i] + " ");
			}
			System.out.println();
			return;
		}

		for (int i = 1; i < n + 1; i++) {
			arr[L] = i;
			if (!visited[i]) {
				visited[i] = true;
				recursive(L + 1);
				visited[i] = false;
			}
		}
	}

}

package 완전탐색;

import java.util.Scanner;

public class 백준_15649_N과_M2_실3 {

	private static int N;
	private static int M;
	private static int[] arr;
	private static boolean[] visited;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		arr = new int[N + 1];
		visited = new boolean[N + 1];

		for (int start = 1; start <= N; start++) {
			recursive(start, M, 0);
		}
	}

	private static void recursive(int selected, int maxDepth, int currentDepth) {
		arr[currentDepth] = selected;

		if (currentDepth == maxDepth - 1) {
			for (int i : arr) {
				if (i != 0) {
					System.out.print(i + " ");
				}
			}
			System.out.println();
			return;
		}

		for (int i = selected + 1; i <= N; i++) {
			recursive(i, maxDepth, currentDepth + 1);
		}
	}

}

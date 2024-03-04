package 완전탐색;

import java.util.Scanner;

public class 백준_15649_N과_M3_실3 {

	private static int N;
	private static int M;
	private static int[] arr;
	private static StringBuilder sb = new StringBuilder();


	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		arr = new int[M];

		for (int i = 1; i < N + 1; i++) {
			recursive(i, M - 1, 0);
		}

		System.out.println(sb);
	}

	private static void recursive(int selected, int maxDepth, int currentDepth) {
		arr[currentDepth] = selected;
		if (currentDepth == maxDepth) {
			for (int i : arr) {
				sb.append(i).append(" ");
			}
			sb.append("\n");
			return;
		}

		for (int i = 1; i < N + 1; i++) {
			recursive(i, maxDepth, currentDepth + 1);
		}
	}


}

package 완전탐색;


import java.util.Arrays;
import java.util.Scanner;

public class 백준_15649_N과_M5_실3 {

	private static int N;
	private static int M;
	private static int[] arr;
	private static int[] temp;
	private static StringBuilder sb = new StringBuilder();
	private static boolean[] visited;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		arr = new int[N];
		temp = new int[M];
		visited = new boolean[N + 1];

		for (int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}
		Arrays.sort(arr);

		for (int selected = 0; selected < N; selected++) {
			visited[selected] = true;
			recursive(arr[selected], M - 1, 0);
			visited[selected] = false;
		}

		System.out.println(sb);
	}

	private static void recursive(int selected, int maxDepth, int currentDepth) {
		temp[currentDepth] = selected;

		if (maxDepth == currentDepth) {
			for (int i : temp) {
				sb.append(i).append(" ");
			}
			sb.append("\n");
			return;
		}

		for (int i = 0; i < N; i++) {
			if (!visited[i]) {
				visited[i] = true;
				recursive(arr[i], maxDepth, currentDepth + 1);
				visited[i] = false;
			}
		}
	}
}

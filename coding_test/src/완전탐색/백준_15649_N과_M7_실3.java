package 완전탐색;


import java.util.Arrays;
import java.util.Scanner;

public class 백준_15649_N과_M7_실3 {

	private static int N;
	private static int M;
	private static int[] arr;
	private static int[] temp;
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		arr = new int[N];
		temp = new int[M];

		for (int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}
		Arrays.sort(arr);

		recursive(0, M);
		System.out.println(sb);
	}

	private static void recursive(int currentDepth, int maxDepth) {
		if (currentDepth == maxDepth) {
			for (int i : temp) {
				sb.append(i).append(" ");
			}
			sb.append("\n");
			return;
		}

		for (int i = 0; i < N; i++) {
			int next = arr[i];
			temp[currentDepth] = next;
			recursive(currentDepth + 1, maxDepth);
		}
	}
}

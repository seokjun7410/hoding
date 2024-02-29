package sw_마에스트로;

import java.util.Scanner;

public class 백준_11053_가장_긴_증가하는_부분수열 {

	static int n;
	static int[] input;
	static int max = 0;
	static int[] array;
	static int reuslt = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		input = new int[n];
		array = new int[n];
		for (int i = 0; i < n; i++) {
			input[i] = sc.nextInt();
		}

		for (int i = 0; i < n; i++) {
			array[i] = input[i];
			DFS(i, 1);
		}
		System.out.println(reuslt);
	}

	private static void DFS(int L, int V) {
		if (reuslt > V + n - L) {
			return;
		}
		if (L == n - 1) {
			reuslt = Math.max(reuslt, V);
		}
		max = Math.max(max, input[L]);

		for (int i = L + 1; i < n; i++) {
			if (max < input[i]) {
				array[L] = max;
				DFS(L + 1, V + 1);
			} else {
				DFS(L + 1, V);
			}
		}
	}
}

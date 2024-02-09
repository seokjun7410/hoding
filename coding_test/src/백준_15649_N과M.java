import java.util.Scanner;

public class 백준_15649_N과M {

	static int[] input;
	static int M;
	static int N;
	static int[] result;
	static boolean[] visited;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		N = scanner.nextInt();
		M = scanner.nextInt();
		input = new int[N + 1];
		result = new int[M + 1];
		visited = new boolean[N + 1];
		for (int i = 1; i <= N; i++) {
			input[i] = i;
		}

		DFS(0, 1);
	}

	private static void DFS(int L, int i) {
		if (L == M) {
			for (int j = 0; j < M; j++) {
				System.out.print(result[j] + " ");
			}
			System.out.println();
			return;
		}
		if (i > N) {
			return;
		}

		for (int j = 1; j < N + 1; j++) {
			if (!visited[j]) {
				visited[j] = true;
				result[L] = input[j];
				DFS(L + 1, i + 1);
				visited[j] = false;
			}

		}
	}
}

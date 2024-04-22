import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 백준_2565_전깃줄_골5 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		StringTokenizer st;

		Node[] input = new Node[N];
		int[] dp = new int[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			input[i] = new Node(a, b);
		}

		Arrays.sort(input);
		for (int i = 1; i < N; i++) {
			for (int j = 0; j < i; j++) {
				if (input[i].b > input[j].b) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
			}
		}

		int result = N - Arrays.stream(dp).max().getAsInt();
		System.out.println(result - 1);

	}

	private static class Node implements Comparable<Node> {

		int a;
		int b;

		public Node(int a, int b) {
			this.a = a;
			this.b = b;
		}

		public int compareTo(Node o) {
			return a - o.a;
		}

	}
}

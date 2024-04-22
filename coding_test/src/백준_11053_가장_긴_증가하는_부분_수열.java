import java.util.Arrays;
import java.util.Scanner;

public class 백준_11053_가장_긴_증가하는_부분_수열 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();

		int[] input = new int[N];
		int[] dp = new int[N];
		for (int i = 0; i < N; i++) {
			input[i] = sc.nextInt();
		}

		for (int i = 1; i < N; i++) {
			for (int j = 0; j < i; j++) {
				if (input[i] > input[j]) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
			}
		}

		int result = Arrays.stream(dp).max().getAsInt();
		System.out.println(result + 1);

	}
}

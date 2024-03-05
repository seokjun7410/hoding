package 완전탐색;

import java.util.Scanner;

public class 백준_2961_도영이가만든음식_실2 {

	private static final int SIN_MOT = 0;
	private static final int SEUN_MOT = 1;
	private static long[][] food;
	private static int N;
	private static long min = Long.MAX_VALUE;


	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		food = new long[N][2];
		for (int i = 0; i < N; i++) {
			food[i][SIN_MOT] = sc.nextInt();
			food[i][SEUN_MOT] = sc.nextInt();
		}

		long[] flavor = {1, 0};
		recursive(0, flavor, 0, N);

		System.out.println(min);
	}

	private static void recursive(int index, long[] flavor, int currentDepth, int maxDepth) {
		if (currentDepth == maxDepth) {
			return;
		}

		for (int i = index; i < N; i++) {
			long[] next = new long[2];
			next[0] = flavor[SIN_MOT] * food[i][SIN_MOT];
			next[1] = flavor[SEUN_MOT] + food[i][SEUN_MOT];
			min = Math.min(min, Math.abs(next[0] - next[1]));
			recursive(i + 1, next, currentDepth + 1, maxDepth);
			recursive(i + 1, flavor, currentDepth + 1, maxDepth);
		}

	}

}

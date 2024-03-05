package 완전탐색;

import java.util.Scanner;

public class 백준_14591_퇴사_실3 {

	private static int N;
	private static int[][] schedule;
	private static final int TIME = 0;
	private static final int MONEY = 1;
	private static int max = Integer.MIN_VALUE;


	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();

		schedule = new int[N][2];
		for (int i = 0; i < N; i++) {
			schedule[i][TIME] = sc.nextInt();
			schedule[i][MONEY] = sc.nextInt();
		}

		recursive(0, 0, 0);

		System.out.println(max);
	}

	private static void recursive(int today, int nextAvailableTime, int money) {
		if (today == N) {
			if (money != 0) {
				max = Math.max(max, money);
			}
			return;
		}

		if (nextAvailableTime <= today && today + schedule[today][TIME] <= N) {
			recursive(today + 1, today + schedule[today][TIME], money + schedule[today][MONEY]);
		}
		recursive(today + 1, nextAvailableTime, money);
	}
}

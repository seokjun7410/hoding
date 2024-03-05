package 완전탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_2503_숫자야구_반복문해결 {

	private static final int NUMBER = 0;
	private static final int STRIKE = 1;
	private static final int BALL = 2;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		StringTokenizer st;
		int[][] hints = new int[N][3];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			hints[i][NUMBER] = Integer.parseInt(st.nextToken());
			hints[i][STRIKE] = Integer.parseInt(st.nextToken());
			hints[i][BALL] = Integer.parseInt(st.nextToken());
		}

		int answer = 0;
		for (int i = 1; i < 10; i++) {
			for (int j = 1; j < 10; j++) {
				for (int k = 1; k < 10; k++) {

					if (i == j || j == k || i == k) {
						continue;
					}
					char[] answerCandidate = String.valueOf(i * 100 + j * 10 + k).toCharArray();

					int correctCount = 0;
					for (int l = 0; l < N; l++) {
						int strike = 0;
						int ball = 0;
						char[] hint = String.valueOf(hints[l][NUMBER]).toCharArray();
						for (int o = 0; o < 3; o++) {
							for (int m = 0; m < 3; m++) {
								if (answerCandidate[o] == hint[m]) {
									if (o == m) {
										strike++;
									} else {
										ball++;
									}
								}
							}
						}

						if (strike == hints[l][STRIKE] && ball == hints[l][BALL]) {
							correctCount++;
						}
					}

					if (correctCount == N) {
						answer++;
					}
				}
			}
		}

		System.out.println(answer);
	}
}

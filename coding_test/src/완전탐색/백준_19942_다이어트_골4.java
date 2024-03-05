package 완전탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class 백준_19942_다이어트_골4 {

	private static int N;
	private static int answer[];
	private static int table[][];
	private static long min = Long.MAX_VALUE;
	private static Stack<Integer> selected;
	private static Integer[] minSelected;


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());
		answer = new int[4];
		selected = new Stack<>();
		minSelected = new Integer[N];
		for (int i = 0; i < 4; i++) {
			answer[i] = Integer.parseInt(st.nextToken());
		}

		table = new int[N][5];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 5; j++) {
				table[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		recursive(0, 0, 0, 0, 0, 0);

		if (min == Long.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(min);

			Arrays.sort(minSelected);
			for (int i : minSelected) {
				if (i == 0) {
					continue;
				}
				System.out.print(i + " ");
			}
		}
	}

	private static void recursive(int index, int a, int b, int c, int d, int e) {
		if (index == N || (answer[0] <= a && answer[1] <= b && answer[2] <= c && answer[3] <= d)) {
			if (!selected.isEmpty() && (answer[0] <= a && answer[1] <= b && answer[2] <= c
				&& answer[3] <= d)) {
				if (min > e) {
					min = e;
					minSelected = selected.toArray(Integer[]::new);

				}
			}
			return;
		}
		selected.push(index + 1);
		recursive(index + 1, a + table[index][0], b + table[index][1], c + table[index][2],
			d + table[index][3], e + table[index][4]);
		selected.pop();
		recursive(index + 1, a, b, c, d, e);
	}


}

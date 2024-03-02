package sw_마에스트로;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_1389_케빈_베이컨 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		long[][] distance = new long[N + 1][N + 1];
		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < N + 1; j++) {
				if (i == j) {
					distance[i][j] = 0;
				} else {
					distance[i][j] = Integer.MAX_VALUE;
				}
			}
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			distance[s][e] = 1;
			distance[e][s] = 1;
		}

		for (int k = 1; k < N + 1; k++) {
			for (int s = 1; s < N + 1; s++) {
				for (int e = 1; e < N + 1; e++) {
					long min = Math.min(distance[s][e], distance[s][k] + distance[k][e]);
					distance[s][e] = min;
				}
			}
		}

		int[] kevin = new int[N + 1];
		int minValue = Integer.MAX_VALUE;
		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < N + 1; j++) {
				long l = distance[i][j];
				kevin[i] += l;
			}
			minValue = Math.min(minValue, kevin[i]);
		}

		for (int i = 1; i < N + 1; i++) {
			if (kevin[i] == minValue) {
				System.out.println(i);
				break;
			}
		}

	}
}

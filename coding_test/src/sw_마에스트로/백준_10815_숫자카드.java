package sw_마에스트로;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 백준_10815_숫자카드 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());
		Integer[] input = new Integer[n];
		for (int i = 0; i < n; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}

		int m = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		int[] target = new int[m];
		for (int i = 0; i < m; i++) {
			target[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(input);

		for (int k : target) {
			boolean find = false;
			int end = n - 1;
			int start = 0;
			while (start <= end) {
				int mid = (end + start) / 2;
				if (input[mid] < k) {
					start = mid + 1;
				} else if (input[mid] > k) {
					end = mid - 1;
				} else {
					find = true;
					break;
				}
			}
			if (find) {
				System.out.print(1 + " ");
			} else {
				System.out.print(0 + " ");
			}
		}

	}

}

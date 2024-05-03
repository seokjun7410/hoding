import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 백준_숫자카드_실5 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());
		int cards[] = new int[N];
		for (int i = 0; i < N; i++) {
			cards[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(cards);

		int M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < M; i++) {
			int num = Integer.parseInt(st.nextToken());
			int s = 0;
			int e = N - 1;
			boolean flag = false;
			while (s <= e) {
				int mid = (s + e) / 2;
				if (cards[mid] == num) {
					flag = true;
					break;
				} else if (cards[mid] > num) {
					e = mid - 1;
				} else if (cards[mid] < num) {
					s = mid + 1;
				}

			}

			if (flag) {
				sb.append(1);
			} else {
				sb.append(0);
			}

			sb.append(" ");
		}

		System.out.println(sb);

	}
}

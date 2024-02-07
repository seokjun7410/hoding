import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 주유소_13305 {

	static long[] adjacencyList;
	static long[] oilPrice;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		oilPrice = new long[N + 1];
		adjacencyList = new long[N + 1];

		boolean flag = true;
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i < N; i++) {
			long w = Long.parseLong(st.nextToken());
			adjacencyList[i] = (w);
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i < N + 1; i++) {
			int price = Integer.parseInt(st.nextToken());
			long a = oilPrice[i - 1];
			if (a != 0) {
				oilPrice[i] = Math.min(a, price);
			} else {
				oilPrice[i] = price;
			}
			if (price != 1) {
				flag = false;
			}
		}

		if (flag) {
			long sum = 0;
			for (Long aLong : adjacencyList) {
				sum += aLong;
			}
			System.out.println(sum);
			return;
		}

		long result = 0;
		for (int i = 0; i < N - 1; i++) {
			Long distance = adjacencyList[i + 1];
			long l = oilPrice[i + 1];

			result += distance * l;
		}

		System.out.println(result);
	}
}

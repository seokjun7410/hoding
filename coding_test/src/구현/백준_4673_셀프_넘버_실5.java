package 구현;

public class 백준_4673_셀프_넘버_실5 {

	static boolean[] visited;

	public static void main(String[] args) {
		visited = new boolean[10000];
		for (int i = 0; i < 10000; i++) {
			for (int j = 0; j < 10000; j++) {
				visited[d(i, j, 0)] = true;
			}
			System.out.println(i);
		}

		for (int i = 1; i < 10000; i++) {
			if (!visited[i]) {
				System.out.println(i);
			}
		}
	}

	public static int d(int n, int k, int output) {
		if (k == 0) {
			return output;
		}

		int sum = 0;
		char[] temp = String.valueOf(n).toCharArray();
		for (int i = 0; i < temp.length; i++) {
			int sum1 = temp[i] - '0';
			sum += sum1;
		}
		return d(n, k - 1, n + sum);
	}
}

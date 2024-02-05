import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class 백준_소트게임_1327 {

	static int K, N;
	static String input, result;
	static Set<String> visited;
	static Queue<String> queue;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		StringBuilder builder = new StringBuilder();
		StringBuilder resultBuilder = new StringBuilder();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			builder.append(Integer.parseInt(st.nextToken()));
			resultBuilder.append(i + 1);
		}
		input = builder.toString();
		result = resultBuilder.toString();

		visited = new HashSet<>();
		queue = new LinkedList<>();
		queue.add(input);
		visited.add(input);

		boolean flag = false;
		int count = 0;
		int willTask = queue.size();
		int done = 0;
		while (!queue.isEmpty()) {
			String now = queue.poll();
			done++;
			if (now.equals(result)) {
				flag = true;
				break;
			}

			for (int i = 1; i <= N - K + 1; i++) {
				String reversed = reverse(now, i);
				if (!isNotVisited(visited, reversed)) {
					queue.add(reversed);
					visited.add(reversed);
				}
			}

			if (willTask == done) {
				willTask = queue.size();
				done = 0;
				count++;
			}
		}

		if (flag) {
			System.out.println(count);
		} else {
			System.out.println(-1);
		}


	}

	private static boolean isNotVisited(Set<String> visited, String reversed) {
		return visited.contains(reversed);
	}

	private static String reverse(String str, int index) {
		int i = index - 1;
		int j = index + K - 1;

		StringBuilder sb = new StringBuilder();
		sb.append(str.substring(0, i));

		// 특정 부분만 뒤집기
		String reverse = str.substring(i, j);
		for (int t = K - 1; t >= 0; t--) {
			sb.append(reverse.charAt(t));
		}

		sb.append(str.substring(j, N));
		return sb.toString();
	}
}
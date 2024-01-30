import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 백준_2623_음악프로그램 {

	private static int[] unionFind;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());//가수 수
		int M = Integer.parseInt(st.nextToken());//pd수

		ArrayList<Integer>[] adjacencyList = new ArrayList[N + 1];
		for (int i = 0; i < N + 1; i++) {
			adjacencyList[i] = new ArrayList<>();
		}

		unionFind = new int[N + 1];
		for (int i = 1; i < N + 1; i++) {
			unionFind[i] = i;
		}

		int[] entryLevel = new int[N + 1];
		Queue<Integer> queue = new LinkedList<>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int preSequence = -1;
			int count = Integer.parseInt(st.nextToken());
			for (int j = 0; j < count; j++) {
				int now = Integer.parseInt(st.nextToken());
				if (preSequence == -1) {
					preSequence = now;
				} else {
//					if (find(preSequence) == find(now)) {
//						System.out.println("0");
//						return;
//					}
					union(preSequence, now);
					adjacencyList[preSequence].add(now);
					entryLevel[now]++;
					preSequence = now;
				}
			}
		}

		for (int i = 1; i < N + 1; i++) {
			if (entryLevel[i] == 0) {
				queue.offer(i);
			}
		}

//		for (int i = 1; i < N + 1; i++) {
//			if (adjacencyList[i].isEmpty() && entryLevel[i] == 0) {
//				System.out.println(i);
//			}
//		}
		StringBuffer sb = new StringBuffer();
		int count = 0;
		while (!queue.isEmpty()) {
			int now = queue.poll();
			sb.append(now).append("\n");
			count ++;
			for (int next : adjacencyList[now]) {
				int level = --entryLevel[next];
				if (level == 0) {
					queue.add(next);
				}
			}
		}

		if (count != N) {
			System.out.println(0);
		} else {
			System.out.println(sb.toString());
		}
	}

	private static void union(int preSequence, int now) {
		unionFind[find(preSequence)] = find(now);
	}

	private static int find(int now) {
		if (unionFind[now] == now) {
			return now;
		}
		return unionFind[now] = find(unionFind[now]);
	}
}

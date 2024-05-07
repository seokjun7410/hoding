import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 백준_바이러스_2606_실3 {

	private static boolean visited[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int E = Integer.parseInt(br.readLine());

		visited = new boolean[N + 1];
		StringTokenizer st;
		List<Integer>[] adjacency = new ArrayList[N + 1];
		for (int i = 0; i < N + 1; i++) {
			adjacency[i] = new ArrayList<>();
		}
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			adjacency[s].add(e);
			adjacency[e].add(s);
		}

		Queue<Integer> queue = new LinkedList<>();
		queue.add(1);

		int cnt = 0;
		while (!queue.isEmpty()) {
			Integer now = queue.poll();
			if (!visited[now]) {
				cnt++;
				visited[now] = true;
				for (Integer next : adjacency[now]) {
					if (!visited[next]) {
						queue.add(next);
					}
				}
			}

		}

		System.out.println(cnt - 1);
	}
}

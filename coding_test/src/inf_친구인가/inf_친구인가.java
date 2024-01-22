package inf_친구인가;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class inf_친구인가 {

	static boolean visited[];
	static List<Integer>[] relationGraph;
	static boolean flag = false;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int studentCount = Integer.parseInt(st.nextToken());
		int relationCount = Integer.parseInt(st.nextToken());

		visited = new boolean[studentCount];
		relationGraph = new ArrayList[studentCount+1];
		for (int i = 0; i < studentCount+1; i++) {
			relationGraph[i] = new ArrayList<>();
		}

		for (int i = 0; i < relationCount; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			relationGraph[a].add(b);
			relationGraph[b].add(a);
		}

		st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());

		DFS(a, b);
		if (flag)
			System.out.println("YES");
		else {
			System.out.println("NO");
		}
	}

	private static void DFS(int a, int b) {
		if(flag)
			return ;

		if (a == b) {
			flag = true;
		}

		if (visited[a]) {
			return;
		}

		visited[a] = true;
		for (int next : relationGraph[a]) {
			if (!visited[next]) {
				DFS(next, b);
			}
		}
	}
}

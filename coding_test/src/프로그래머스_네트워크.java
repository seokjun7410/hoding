import java.util.ArrayList;
import java.util.List;

public class 프로그래머스_네트워크 {

	static int[][] computers = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};

	static int N = 3;

	static List<Integer>[] adjacencyList;
	static boolean[] visited;

	public static void main(String[] args) {

		visited = new boolean[N];
		adjacencyList = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			adjacencyList[i] = new ArrayList<>();
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (i != j && computers[i][j] == 1) {
					adjacencyList[i].add(j);
					adjacencyList[j].add(i);
				}
			}
		}

		int result = 0;
		while (!visitedComplete()) {
			int notVistiedVertex = getNotVistiedVertex();
			DFS(notVistiedVertex);
			result++;
		}

		System.out.println(result);

	}

	private static void DFS(int v) {
		visited[v] = true;

		for (int next : adjacencyList[v]) {
			if (!visited[next]) {
				DFS(next);
			}
		}
	}

	private static int getNotVistiedVertex() {
		for (int i = 0; i < N; i++) {
			if (!visited[i]) {
				return i;
			}
		}

		return -1;
	}

	private static boolean visitedComplete() {
		for (int i = 0; i < N; i++) {
			if (!visited[i]) {
				return false;
			}
		}
		return true;
	}

}

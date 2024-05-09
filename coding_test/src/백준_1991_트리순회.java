import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 백준_1991_트리순회 {

	private static List<Integer>[] graph;

	private static StringBuffer aa = new StringBuffer();
	private static StringBuffer bb = new StringBuffer();
	private static StringBuffer cc = new StringBuffer();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		graph = new ArrayList[100];
		for (int i = 0; i < 100; i++) {
			graph[i] = new ArrayList<>();
		}
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = st.nextToken().chars().toArray()[0];
			int b = st.nextToken().chars().toArray()[0];
			int c = st.nextToken().chars().toArray()[0];
			graph[a].add(b);
			graph[a].add(c);
		}

		dfs('A');

		System.out.println(aa);
		System.out.println(bb);
		System.out.println(cc);
	}

	private static void dfs(int v) {
		int c = '.';
		if (v == c) {
			return;
		}

		aa.append(Character.toChars(v));
		dfs(graph[v].get(0));
		bb.append(Character.toChars(v));
		dfs(graph[v].get(1));
		cc.append(Character.toChars(v));

	}
}

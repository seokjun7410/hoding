package inf_친구인가;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class inf_친구인가_유니온파인드 {
	static boolean visited[];
	static int unionArray[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int studentCount = Integer.parseInt(st.nextToken());
		int relationCount = Integer.parseInt(st.nextToken());

		unionArray = new int[studentCount+1];
		visited = new boolean[studentCount];
		for (int i = 0; i < studentCount+1; i++) {
			unionArray[i] = i;
		}

		for (int i = 0; i < relationCount; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			union(a,b);
		}
		st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());

		if (find(b) == find(a)) {
			System.out.println("YES");
		}else {
			System.out.println("NO");
		}
	}

	private static void union(int a, int b) {
		unionArray[find(a)] = find(b);
	}

	private static int find(int a) {
		if(a == unionArray[a])
			return a;

		return unionArray[a] = find(unionArray[a]);
	}
}

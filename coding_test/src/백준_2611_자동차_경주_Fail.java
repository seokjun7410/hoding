import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

public class 백준_2611_자동차_경주_Fail {

	static ArrayList<Road>[] adjacencyList;
	static ArrayList<Road>[] pathList;

	static Stack<Integer> resultPath;
	static int V;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		V = Integer.parseInt(br.readLine());
		int E = Integer.parseInt(br.readLine());

		adjacencyList = new ArrayList[V + 1];
		for (int i = 0; i < V + 1; i++) {
			adjacencyList[i] = new ArrayList<>();
		}
		pathList = new ArrayList[V + 1];
		for (int i = 0; i < V + 1; i++) {
			pathList[i] = new ArrayList<>();
		}
		resultPath = new Stack<>();

		int[] entryLevel = new int[V + 1];
		int[] vertexMaxDistance = new int[V + 1];

		StringTokenizer st;
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			adjacencyList[s].add(new Road(s, e, w));
			entryLevel[e]++;
		}

		PriorityQueue<Road> queue = new PriorityQueue<>();
		queue.add(new Road(1, 1, 0));

		while (!queue.isEmpty()) {
			Road nowRoad = queue.poll();
			int now = nowRoad.vertex;
			vertexMaxDistance[now] = Math.max(vertexMaxDistance[now],
				vertexMaxDistance[nowRoad.start] + nowRoad.weight);
			System.out.println("now = " + now);
			if(nowRoad.start != nowRoad.vertex)
				pathList[nowRoad.start].add(nowRoad);
			for (Road nextRoad : adjacencyList[now]) {
				int next = nextRoad.vertex;
				int entry = --entryLevel[next];
				if (entry == 0) {
					queue.add(nextRoad);
				}
			}
		}

		System.out.println(vertexMaxDistance[1]);
		System.out.print(1+" ");
		DFS(0, 1);



	}

	private static void DFS(int L, int v) {
		if (L == V-1) {
			ArrayList<Integer> temp = new ArrayList<>();
			while(!resultPath.isEmpty()){
				temp.add(resultPath.pop());
			}

			Collections.reverse(temp);
			for (Integer each : temp) {
				System.out.print(each+" ");
			}
			return;
		}

		for (Road next : pathList[v]) {
			resultPath.push(next.vertex);
			DFS(L + 1, next.vertex);
			if(!resultPath.isEmpty())
				resultPath.pop();
		}


	}


	private static class Road implements Comparable<Road> {

		int start;
		int vertex;
		int weight;

		public Road(int start, int vertex, int weight) {
			this.start = start;
			this.vertex = vertex;
			this.weight = weight;
		}

		@Override
		public int compareTo(Road o) {
			return o.weight - this.weight;
		}
	}
}

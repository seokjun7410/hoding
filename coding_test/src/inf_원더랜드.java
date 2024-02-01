import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import org.w3c.dom.Node;

public class inf_원더랜드 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;// = new StringTokenizer(br.readLine());

		int V = Integer.parseInt(br.readLine());
		int E = Integer.parseInt(br.readLine());

		List<Node>[] adjancenyList = new ArrayList[V+1];
		boolean[] visited = new boolean[V+1];

		for (int i = 0; i < V+1; i++) {
			adjancenyList[i] = new ArrayList<>();
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			adjancenyList[s].add(new Node(e,w));
			adjancenyList[e].add(new Node(s,w));
		}


		long sum = 0;
		PriorityQueue<Node> queue = new PriorityQueue();
		queue.add(new Node(1,0));
		visited[0] = true;
		int count = 0;
		int max = Integer.MIN_VALUE;
		while(!queue.isEmpty()){
			if(count == V)
				break;
			Node now = queue.poll();
			int vertex = now.v;

			if(!visited[vertex]) {
				max = Math.max(max,now.w);
				count++;
				sum += now.w;
			}
			visited[vertex] = true;

			for (Node candidate : adjancenyList[vertex]) {
				if(!visited[candidate.v] )
					queue.add(candidate);
			}
		}

		System.out.println(sum);
	}


	public static class Node implements Comparable<Node>{
		int v;
		int w;

		public Node(int v, int w) {
			this.v = v;
			this.w = w;
		}

		@Override
		public int compareTo(Node o) {
			return this.w - o.w;
		}
	}
}

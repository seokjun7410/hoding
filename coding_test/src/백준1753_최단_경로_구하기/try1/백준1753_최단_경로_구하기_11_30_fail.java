package 백준1753_최단_경로_구하기.try1;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 백준1753_최단_경로_구하기_11_30_fail {

    static boolean visited[];
    static ArrayList<MyNode> adjacencyList[];
    static int distance[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(br.readLine());

        visited = new boolean[V + 1];
        distance = new int[V + 1];
        adjacencyList = new ArrayList[V + 1];
        for (int i = 1; i < V + 1; i++) {
            adjacencyList[i] = new ArrayList<>();
            distance[i] = Integer.MAX_VALUE;
        }
        distance[start] = 0;

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            adjacencyList[u].add(new MyNode(v, w));
        }

        BFS(new MyNode(start, 0));
        for (int i = 1; i < V + 1; i++) {
            int x = distance[i];

            if (i == start)
                System.out.println("0");
            else if (x == Integer.MAX_VALUE)
                System.out.println("INF");
            else
                System.out.println(x);
        }
    }

    private static void BFS(final MyNode v) {
        Queue<MyNode> queue = new LinkedList<>();
        queue.add(v);

        while (!queue.isEmpty()) {
            MyNode current = queue.poll();
            int c_v = current.v;
//            if (visited[c_v]) continue;
//            visited[c_v] = true;
            for (int i = 0; i < adjacencyList[c_v].size(); i++) {
                MyNode temp = adjacencyList[current.v].get(i);
                int next = temp.v;
                int value = temp.w;
                if (distance[next] > distance[c_v] + value) { // 최소 거리로 업데이트
                    distance[next] = value + distance[c_v];
                    queue.add(new MyNode(next,distance[next]));
                }

            }
        }
    }


}

  class MyNode {
    final int v;
    final int w;

    public MyNode(final int v, final int w) {
        this.v = v;
        this.w = w;
    }

}
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 백준1854_K번째_최단경로 {
    private static ArrayList<Edge> adjacencyList[];


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); //정점 개수
        int m = Integer.parseInt(st.nextToken()); //엣지 개수
        int k = Integer.parseInt(st.nextToken());

        initAdjacencyList(n);

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            adjacencyList[a].add(new Edge(b, c));
        }


        PriorityQueue<Integer> distance[] = new PriorityQueue[n + 1];
        Comparator<Integer> cp = new Comparator<>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 < o2 ? 1 : -1;
            }
        };
        for (int i = 1; i < n + 1; i++) {
            distance[i] = new PriorityQueue<>(n, cp);
        }

        PriorityQueue<Edge> queue = new PriorityQueue<>();
        queue.add(new Edge(1, 0));
        distance[1].add(0);
        while (!queue.isEmpty()) {
            Edge now = queue.poll();
            int nowVertex = now.vertex;
            int nowWeight = now.weight;
            for (Edge next : adjacencyList[nowVertex]) {
                int nextVertex = next.vertex;
                int nextWeight = next.weight;
                if (distance[nextVertex].size() < k) {
                    distance[nextVertex].add(nowWeight + nextWeight);
                    queue.add(new Edge(nextVertex, nowWeight + nextWeight));
                } else if (distance[nextVertex].peek() > nowWeight + nextWeight) {
                    distance[nextVertex].poll();
                    distance[nextVertex].add(nowWeight + nextWeight);
                    queue.add(new Edge(nextVertex, nowWeight + nextWeight));
                }
            }
        }

        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 1; i < n + 1; i++) {
            if (distance[i].size() == k) {
                stringBuffer.append(distance[i].peek()).append("\n");
            } else {
                stringBuffer.append(-1).append("\n");
            }
        }

        System.out.println(stringBuffer);


    }

    private static void initAdjacencyList(int v) {
        adjacencyList = new ArrayList[v + 1];
        for (int i = 1; i < v + 1; i++) {
            adjacencyList[i] = new ArrayList<>();
        }
    }

    private static class Edge implements Comparable<Edge> {
        int vertex;
        int weight;

        public Edge(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight > o.weight ? 1 : -1;
        }
    }
}

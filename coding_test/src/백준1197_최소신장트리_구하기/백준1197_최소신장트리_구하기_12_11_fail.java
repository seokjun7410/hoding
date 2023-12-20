
package 백준1197_최소신장트리_구하기;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 백준1197_최소신장트리_구하기_12_11_fail {
    private static Edge[] edgeList;
    private static int unionFind[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        edgeList = new Edge[V + 1];
        unionFind = new int[V + 1];
        PriorityQueue<Edge> queue = new PriorityQueue<>();

        for (int i = 1; i < V+1; i++) {
            unionFind[i] = i;
        }
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            queue.add(new Edge(a, b, c));
        }
        int useEdge = 0;
        int result = 0;
        while (useEdge < V - 1) {
            Edge now = queue.poll();
            int start = now.start;
            int end = now.end;
            int value = now.value;
            if (find(start) != find(end)) {
                union(start, end);
                result = result + value;
                useEdge++;
            }
        }

        System.out.println(result);
    }

    public static void union(int a, int b) {
        unionFind[find(a)] = find(b);
    }

    public static int find(int a) {
        if (unionFind[a] == a)
            return a;
        return unionFind[a] = find(unionFind[a]);
    }

    private static class Edge implements Comparable<Edge> {
        int start;
        int end;
        int value;

        public Edge(int start, int end, int value) {
            this.start = start;
            this.end = end;
            this.value = value;
        }

        @Override
        public int compareTo(Edge o) {
            return this.value > o.value ? 1 : -1;
        }
    }
}



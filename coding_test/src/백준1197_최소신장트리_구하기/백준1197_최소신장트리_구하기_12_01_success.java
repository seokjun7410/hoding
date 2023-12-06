package 백준1197_최소신장트리_구하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 백준1197_최소신장트리_구하기_12_01_success {

    static int[] unionFind;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        PriorityQueue<Edge> queue = new PriorityQueue();

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            queue.add(new Edge(A, B, C));
        }
        unionFind = new int[N + 1];
        for (int i = 1; i < N+1; i++) {
            unionFind[i] = i;
        }
        int edgeWeight = 0;
        int linkedEdgeSize = 0;

        while (linkedEdgeSize < N - 1) {
            Edge now = queue.poll();
            int a = now.A;
            int b = now.B;
            if (find(a) != find(b)) {
                union(a, b);
                edgeWeight += now.C;
                linkedEdgeSize++;
            } else {
                continue;
            }
        }

        System.out.println(edgeWeight);
    }

    private static void union(int a, int b) {
        unionFind[find(a)] = unionFind[find(b)];
    }

    private static int find(final int a) {
        if (unionFind[a] == a)
            return a;
        return unionFind[a] = find(unionFind[a]);
    }

    static class Edge implements Comparable<Edge> {
        int A;
        int B;
        int C;

        public Edge(final int a, final int b, final int c) {
            A = a;
            B = b;
            C = c;
        }

        @Override
        public int compareTo(final Edge o) {
            return this.C - o.C;
        }
    }
}



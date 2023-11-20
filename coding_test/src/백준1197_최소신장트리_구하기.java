import java.io.IOException;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class 백준1197_최소신장트리_구하기 {
    static int parent[];
    static PriorityQueue<pEdge> queue;

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int M = scanner.nextInt();

        queue = new PriorityQueue<pEdge>();
        parent = new int[N+1];

        for (int i = 0; i <N; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < M; i++) {
            int s = scanner.nextInt();
            int e = scanner.nextInt();
            int v = scanner.nextInt();
            queue.add(new pEdge(s,e,v));
        }

        int useEdge = 0;
        int result = 0;

        while(useEdge < N-1){
            pEdge now = queue.poll();
            if(find(now.s) != find(now.e)){
                union(now.s,now.e);
                result = result + now.v;
                useEdge++;
            }
        }
        System.out.println(result);

    }

    private static void union(final int a, final int b) {
        int pa = find(a);
        int pb = find(b);
        if(pa!=pb){
            parent[pb] = pa;
        }
    }

    private static int find(int a) {
        if(a==parent[a])
            return a;
        else {
            parent[a] = find(parent[a]);
            return parent[a];
        }
    }

    private static class pEdge implements Comparable<pEdge> {
        private final int s;
        private final int e;
        private final int v;

        public pEdge(final int s, final int e, final int v) {
            this.s = s;
            this.e = e;
            this.v = v;
        }


        @Override
        public int compareTo(final pEdge o) {
            return this.v - o.v;
        }
    }
}

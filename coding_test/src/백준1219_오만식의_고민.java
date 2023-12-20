import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 백준1219_오만식의_고민 {
    private static Edge[] edgeList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); //도시 개수
        int S = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken()); //교통수단 개수

        edgeList = new Edge[M];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            edgeList[i] = new Edge(s, e, w);
        }

        long money[] = new long[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            money[i] = Long.parseLong(st.nextToken());
        }

        long distance[] = new long[N];
        Arrays.fill(distance, Long.MIN_VALUE);
        distance[S] = money[S];

        for (int i = 0; i <= N + 100; i++) {
            for (int j = 0; j < M; j++) {
                Edge edge = edgeList[j];
                if (distance[edge.s] == Long.MIN_VALUE) continue;
                else if (distance[edge.s] == Long.MAX_VALUE)
                    distance[edge.e] = Long.MAX_VALUE;
                if (distance[edge.e] < distance[edge.s] + money[edge.e] - edge.w) {
                    distance[edge.e] = distance[edge.s] + money[edge.e] - edge.w;
                    if (i >= N - 1) distance[edge.e] = Long.MAX_VALUE;
                }
            }
        }


        if (distance[E] == Long.MAX_VALUE) {
            System.out.println("Gee");
        } else if (distance[E] == Long.MIN_VALUE) {
            System.out.println("gg");
        } else {
            System.out.println(distance[E]);
        }


    }

    private static class Edge {
        int s;
        int e;
        int w;

        public Edge(int s, int e, int w) {
            this.s = s;
            this.e = e;
            this.w = w;
        }

    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준11657_타임머신 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Edge edgeList[] = new Edge[M + 1];
        for (int i = 1; i < M + 1; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            edgeList[i] = new Edge(s, e, w);
        }

        long result[] = new long[N + 1];
        for (int i = 1; i < N + 1; i++) {
            result[i] = Integer.MAX_VALUE;
        }
        result[1] = 0;

        for (int k = 0; k < N-1; k++) {
            for (int i = 1; i < M + 1; i++) {
                if (result[edgeList[i].s] != Integer.MAX_VALUE) {
                    if (result[edgeList[i].e] > result[edgeList[i].s] + edgeList[i].w) {
                        result[edgeList[i].e] = result[edgeList[i].s] + edgeList[i].w;
                    }
                }
            }
        }

        boolean minusCycle = false;
        for (int i = 1; i < M + 1; i++) {
            if (result[edgeList[i].s] != Integer.MAX_VALUE) {
                if (result[edgeList[i].e] > result[edgeList[i].s] + edgeList[i].w) {
                    minusCycle = true;
                }
            }
        }

        StringBuffer stringBuffer = new StringBuffer();

        if(minusCycle)
            stringBuffer.append(-1).append("\n");
        else{
            for (int i = 2; i < N+1; i++) {
                long x = result[i];
                if(x == Integer.MAX_VALUE)
                    stringBuffer.append(-1).append("\n");
                else
                    stringBuffer.append(x).append("\n");
            }
        }

        System.out.print(stringBuffer);
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

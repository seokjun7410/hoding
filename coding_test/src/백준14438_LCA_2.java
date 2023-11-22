import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 백준14438_LCA_2 {

    static ArrayList<Integer>[] list;
    static int[] depth;
    static int kmax;
    static int[][] parent;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        list = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            list[s].add(e);
            list[e].add(s);
        }

        depth = new int[N + 1];
        visited = new boolean[N + 1];

        kmax = 0;
        int temp = 1;
        while (temp <= N) {
            temp <<= 1; //shift 연산, temp에 2의 거듭제곱
            kmax++;
        }

        parent = new int[kmax + 1][N + 1];
        BFS(1);
        for (int k = 1; k <= kmax; k++) {
            for (int n = 1; n <= N; n++) {
                parent[k][n] = parent[k - 1][parent[k - 1][n]];
            }
        }
        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int LCA = excuteLCA(a, b);
            System.out.println(LCA);
        }
    }

    private static int excuteLCA(int a, int b) {
        if (depth[a] > depth[b]) {
            int temp = a;
            a = b;
            b = temp;
        }

        //깊이 맞추기 => 빠르게
        for (int k = kmax; k >= 0; k--) {
            if (Math.pow(2, k) <= depth[b] - depth[a]) {
                if (depth[a] <= depth[parent[k][b]])
                    b = parent[k][b];
            }
        }

        //동시에 올라가면서 조상을 찾기 => 빠르게
        for (int k = kmax; k >= 0 && a != b; k--) {
            if (parent[k][a] != parent[k][b]) {
                a = parent[k][a];
                b = parent[k][b];
            }
        }

        int LCA = a;
        if (a != b)
            LCA = parent[0][LCA];
        return LCA;
    }

    private static void BFS(final int node) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(node);
        visited[node] = true;
        int depthValue = 1; //depth
        int processTargetCountInCurrentDepth = 1; //level을 컨트롤하기 위한 변수 1 -> 현재depth
        int currentProcessedCount = 0; //level을 컨트롤하기 위한 변수 2

        while (!queue.isEmpty()) {
            Integer nowNode = queue.poll();
            for (int next : list[nowNode]) {
                if (!visited[next]) {
                    visited[next] = true;
                    queue.add(next);
                    parent[0][next] = nowNode;
                    depth[next] = depthValue;
                }
            }
            currentProcessedCount++;
            if (currentProcessedCount == processTargetCountInCurrentDepth) {
                currentProcessedCount = 0;
                depthValue++;
                processTargetCountInCurrentDepth = queue.size();
            }
        }
    }


}

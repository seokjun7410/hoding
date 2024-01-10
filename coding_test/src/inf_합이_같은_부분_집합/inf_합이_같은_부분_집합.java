package inf_합이_같은_부분_집합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class inf_합이_같은_부분_집합 {
    static boolean visited[];
    static int input[];

    static int N;

    static boolean result = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        visited = new boolean[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        input = new int[N];
        for (int i = 0; i < N; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }

        DFS(0);
        if(result)
            System.out.println("YES");
        else
            System.out.println("NO");
    }

    private static void DFS(int v) {
        if (visited[v])
            return;

        visited(v);
        if(result)
            return;

        for (int next = 0; next < N; next++) {
            if(!visited[next]) {
                DFS(next);
                visited[next] = false;
                if(result)
                    break;
            }
        }
    }

    private static void visited(int v) {
        visited[v] = true;

        int sumA = 0;
        int sumB = 0;
        for (int i = 0; i < N; i++) {
            if(visited[i])
                sumA += input[i];
            else
                sumB += input[i];
        }
        if(sumA == sumB)
            result = true;

    }
}

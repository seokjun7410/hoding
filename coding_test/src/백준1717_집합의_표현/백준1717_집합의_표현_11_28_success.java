package 백준1717_집합의_표현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준1717_집합의_표현_11_28_success {

    private static int arr[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        arr = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            arr[i] = i;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (cmd == 0) { //union
                union(a, b);
            } else { //find
                if (find(a) == find(b))
                    System.out.println("YES");
                else {
                    System.out.println("NO");
                }
            }

        }
    }

    private static int find(final int a) {
        if (arr[a] == a)
            return a;

        int parent = find(arr[a]);
        return arr[a] = parent;
    }

    private static void union(int a, int b) {
        arr[find(a)] = arr[find(b)];
    }


}

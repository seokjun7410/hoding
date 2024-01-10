package inf_합이_같은_부분_집합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class inf_합이_같은_부분_집합_01_08 {
    static int input[];
    static int total = 0;
    static boolean flag = false;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringTokenizer st;
        input = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            input[i] = Integer.parseInt(st.nextToken());
            total += input[i];
        }

        DFS(0,0);
        if(!flag)
            System.out.println("NO");
    }

    private static void DFS(int L, int sum) {
        if(flag)
            return;

        if(L == N)
            return;

        if(sum == total-sum) {
            System.out.println("YES");
            flag = true;
            return;
        }

        DFS(L+1,sum +input[L]);
        DFS(L+1,sum);
    }
}

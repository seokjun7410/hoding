package inf_바둑이_승차;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class inf_바둑이승차_01_09 {
    static int W;
    static int N;
    static int input[];
    static int count = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        W = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        input = new int[N];
        for (int i = 0; i < N; i++) {
            input[i] = Integer.parseInt(br.readLine());
        }

        DFS(0,0);

        System.out.println(count);

    }

    private static void DFS(int L, int sum) {
        if(sum < W){
            count = Math.max(sum,count);
        }
        if(L == N) {
            return;
        }


        DFS(L+1,sum+input[L]);
        DFS(L+1,sum);
    }
}

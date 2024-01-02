import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class inf_바둑이승차 {
    static int C;
    static int max;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        int input[] = new int[N];
        for (int i = 0; i < N; i++) {
            input[i] = Integer.parseInt(br.readLine());
        }

        DFS(-1, 0, input);

        System.out.println(max);
    }

    private static void DFS(int L, int sum, int[] input) {
        if (L+1 >= N || sum > C) {
            if(sum<=C) {
                max = Math.max(max, sum);
            }
            return;
        }

        DFS(L+1,sum+input[L+1],input);
        DFS(L+1,sum,input);

    }
}

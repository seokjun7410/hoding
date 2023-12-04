package 백준_1722_순열의_순서;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 백준1722_순열의_순서_12_04 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int cmd = Integer.parseInt(st.nextToken());

        int awnser[] = new int[N + 1];
        int cases[] = new int[N + 1];
        boolean used[] = new boolean[N+1];
        for (int i = 1; i <= N; i++) {
            cases[i] = factorial(N - i + 1);
            System.out.print(" " + cases[i] + "\t");
            System.out.println();
        }


        if (cmd == 1) {
            int question = Integer.parseInt(st.nextToken());

        } else {
            for (int i = 0; i < N; i++) {
                int num = Integer.parseInt(st.nextToken());
            }
        }

    }

    private static int factorial(final int n) {
        if (n == 1)
            return 1;

        return n * factorial(n - 1);
    }
}

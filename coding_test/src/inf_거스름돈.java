import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class inf_거스름돈 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        ;
        int coin[] = new int[N + 1];
        for (int i = 1; i < N+1; i++) {
            coin[i] = Integer.parseInt(st.nextToken());
        }
        int exchange = Integer.parseInt(br.readLine());
        int dy[] = new int[exchange + 1];
        Arrays.fill(dy, Integer.MAX_VALUE);
        dy[0] = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = coin[i]; j < exchange + 1; j++) {
                dy[j] = Math.min(dy[j], dy[j - coin[i]] + 1);
            }
        }

        System.out.println(dy[exchange]);

    }

}
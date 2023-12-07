import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준11047_동전0 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int coinArray[] = new int[N];
        for (int i = 0; i < N; i++) {
            coinArray[i] = Integer.parseInt(br.readLine());
        }

        int cnt = 0;
        for (int i = N-1 ; i >= 0; i--) {
            if (K >= coinArray[i]) {
                while (K >= coinArray[i]) {
                    K=K-coinArray[i];
                    cnt++;
                }
            }
            if(K == 0)
                break;
        }
        System.out.println(cnt);

    }
}

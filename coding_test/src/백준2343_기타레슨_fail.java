import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준2343_기타레슨_fail {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int video[] = new int[N + 1];
        ;
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N + 1; i++) {
            video[i] = Integer.parseInt(st.nextToken());
        }

        int subSum[] = new int[N + 1];
        for (int i = 1; i < N + 1; i++) {
            subSum[i] = subSum[i - 1] + video[i];
        }

        int max = 0;

        for (int k = 0; k < M; k++) {
            int preSplit = 1;

            for (int i = preSplit; i < N; i++) {
                if (i == 1) { //첫 선택
                    if (max < subSum[i])
                        max = subSum[i];
                } else {
                    if (max < subSum[i] - subSum[preSplit])
                        max = subSum[i] - subSum[preSplit];
                }
            }
        }
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준11403_경로찾기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int arr[][] = new int[N + 1][N + 1];
        StringTokenizer st;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (arr[i][k] == 1 && arr[k][j] == 1)
                        arr[i][j] = 1;
                }
            }
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                stringBuffer.append(arr[i][j]).append(" ");
            }
            stringBuffer.append("\n");
        }

        System.out.println(stringBuffer);
    }
}

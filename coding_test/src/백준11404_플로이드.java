import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준11404_플로이드 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        long adjacency[][] = new long[n + 1][n + 1];
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (i == j)
                    adjacency[i][j] = 0;
                else
                    adjacency[i][j] = Integer.MAX_VALUE;
            }
        }


        StringTokenizer st;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            adjacency[a][b] = Math.min(adjacency[a][b],c);
        }


        for (int k = 1; k < n+1; k++) {
            for (int s = 1; s < n+1; s++) {
                for (int e = 1; e < n+1; e++) {
                 adjacency[s][e] = Math.min(adjacency[s][e],adjacency[s][k]+adjacency[k][e]);
                }
            }
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < n +1; j++) {
                long value = adjacency[i][j];
                if(value == Integer.MAX_VALUE)
                    stringBuffer.append(0).append(" ");
                else
                    stringBuffer.append(value).append(" ");
            }
            stringBuffer.append("\n");
        }

        System.out.println(stringBuffer);


    }

}

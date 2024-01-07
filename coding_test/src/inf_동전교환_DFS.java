import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class inf_동전교환_DFS {

    static int N;
    static Integer[] coin;
    static int change;
    static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        coin = new Integer[N];
        for (int i = 0; i < N; i++) {
            coin[i] = Integer.parseInt(st.nextToken());
        }
        change = Integer.parseInt(br.readLine());

        Arrays.sort(coin,Collections.reverseOrder());
        DFS(0,0,0);

        System.out.println(min);
    }

    private static void DFS(int index, int sum, int count) {
        if(index == N || sum > change || count > min) {
            return;
        }
        if(sum == change) {
            min = Math.min(min,count);
            return;
        }

        DFS(index+1,sum+coin[index],count+1); //현재코인을 선택하고 다음코인으로 넘어간다.
        DFS(index,sum+coin[index],count+1); //현재 코인을 선택하나 다음코인으로 넘어가지 않는다.
        DFS(index+1,sum,count);//아무것도 선택하지 않고 다음코인으로 넘어간다.

    }
}

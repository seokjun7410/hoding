import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준14888_연산자_끼워넣기 {

    static int N;
    static int[] num;
    static int oper[] = new int[4];
    static int MAX = Integer.MIN_VALUE;
    static int MIN = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        num = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            oper[i] = Integer.parseInt(st.nextToken());
        }

        backtracking(num[0], 1);
        System.out.println(MAX);
        System.out.println(MIN);

    }

    private static void backtracking(final int now, final int index) {

        if (index == N) {
            MAX = Math.max(MAX,now);
            MIN = Math.min(MIN,now);
            return;
        }
        // + - * /
        if(oper[0]>0){
            oper[0]--;
            backtracking(now+num[index],index+1);
            oper[0]++;
        }

        if(oper[1]>0){
            oper[1]--;
            backtracking(now-num[index],index+1);
            oper[1]++;
        }

        if(oper[2]>0){
            oper[2]--;
            backtracking(now*num[index],index+1);
            oper[2]++;
        }

        if(oper[3]>0){
            oper[3]--;
            backtracking(now/num[index],index+1);
            oper[3]++;
        }

    }
}

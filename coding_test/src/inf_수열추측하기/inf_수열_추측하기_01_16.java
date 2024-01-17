package inf_수열추측하기;
import java.util.Scanner;

public class inf_수열_추측하기_01_16 {

    static int[] b;
    static int N;
    static int number;
    static int[] result;

    static boolean visited[];
    static boolean flag = false;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();
        number = scanner.nextInt();
        visited = new boolean[N+1];
        b = bInit(N);
        result = new int[N];
        DFS(0, 0);

    }

    private static int[] bInit(int n) {
        int[] temp = new int[n];
        for (int i = 0; i < n; i++) {
            temp[i] = combination(n-1, i);
        }
        return temp;
    }

    private static int combination(int n, int r) {
        if (n == r)
            return 1;
        if (r == 0)
            return 1;
        return combination(n - 1, r - 1) + combination(n - 1, r);
    }

    private static void DFS(int L, int sum) {
        if (flag)
            return;
        if (L == N) {
            if (sum == number) {
                flag = true;
                for (int i = 0; i < N; i++) {
                    System.out.print(result[i]+ " ");
                }
            }
            return;
        }


        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                result[L] = i;
                DFS(L + 1, sum + b[L] * i);
                visited[i] = false;
            }
        }
    }
}
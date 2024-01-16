package inf_수열추측하기;

import java.util.*;

public class inf_수열_추측하기 {
    static int N;
    static int value;
    static boolean visited[];
    static int comArr[][];
    static int[] result;
    static boolean flag = false;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();
        value = scanner.nextInt();
        comArr = new int[N][N];
        visited = new boolean[N+1];
        result = new int[N];
        DFS(0,0);

    }

    private static void DFS(int L, int sum) {
        if(flag)
            return;
        if(L == N){
            if(sum == value) {
                for (int i : result) {
                    System.out.print(i+" ");
                }
                flag = true;
            }
            return;
        }

        for (int i = 1; i <= N; i++) {
            if(!visited[i]) {
                visited[i] = true;
                result[L] = i;
                DFS(L + 1, sum + i*com(N - 1, L));
                visited[i] = false;
            }
        }
    }

    private static int com(int n, int r) {
        if(r == 0)
            return 1;
        if(n == r)
            return 1;

        if(comArr[n][r] != 0){
            return comArr[n][r];
        }

        int sum = com(n - 1, r) + com(n - 1, r - 1);
        return comArr[n][r] = sum;
    }
}
package 백준2193_이친수;

import java.util.Scanner;

public class 백준2193_이친수_12_05_Success {
    private static final int ZERO_INDEX = 0;
    private static final int ONE_INDEX = 1;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        long history[][] = new long[2][N+1];

        history[ZERO_INDEX][1] = 0;
        history[ONE_INDEX][1] = 1;

        for (int i = 2; i < N + 1; i++) {
            history[ZERO_INDEX][i] = history[ZERO_INDEX][i - 1] + history[ONE_INDEX][i - 1];
            history[ONE_INDEX][i] = history[ZERO_INDEX][i-1];
        }

        System.out.println(history[ZERO_INDEX][N]+history[ONE_INDEX][N]);
    }
}



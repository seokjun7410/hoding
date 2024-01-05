package inf_돌다리_건너기;

import java.util.Scanner;

public class Inf돌계단건너기 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int d[] = new int[N + 2];
        d[1] = 1;
        d[2] = 2;
        for (int i = 3; i < N + 2; i++) {
            d[i] = d[i - 1] + d[i - 2];
        }

        System.out.println(d[N+1]);
    }
}

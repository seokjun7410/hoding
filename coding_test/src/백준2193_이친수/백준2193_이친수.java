package 백준2193_이친수;

import java.util.Scanner;

public class 백준2193_이친수 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        long[] find1 = new long[N+2];
        long[] find0 = new long[N+2];
        find1[1] = 1;
        find1[2] = 0;
        find0[1] = 0;
        find0[2] = 1;

        for (int i = 2; i <= N; i++) {
            find1[i] = find0[i-1];
            find0[i] = find1[i-1] + find0[i-1];
        }

        if(N == 1)
            System.out.println(1);
        else {
            System.out.println(find1[N - 1] + find0[N - 1] * 2);
        }
    }
}



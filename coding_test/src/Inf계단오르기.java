import java.util.Scanner;

public class Inf계단오르기 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int d[] = new int[N + 1];
        d[1] = 1;
        d[2] = 2;
        for (int i = 3; i < N + 1; i++) {
            d[i] = d[i - 1] + d[i - 2];
        }

        System.out.println(d[N]);
    }

}

import java.util.Arrays;
import java.util.Scanner;

public class 백준_1463_1로_만들기 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int d[] = new int[n+1];
        for (int i = 2; i < n+1; i++) {
            int possible[] = new int[3];
            Arrays.fill(possible,Integer.MAX_VALUE);
            possible[0] = d[i - 1] + 1;
            if(i % 3 == 0)
                possible[1] = d[i/3]+1;
            if(i % 2 == 0)
                possible[2] = d[i/2]+1;
            d[i] = Arrays.stream(possible).min().getAsInt();
        }

        System.out.println(d[n]);
    }
}

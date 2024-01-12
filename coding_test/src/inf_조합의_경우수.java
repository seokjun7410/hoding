import java.util.Scanner;

public class inf_조합의_경우수 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int r = scanner.nextInt();

        int sum = DFS(n, r, 0);

        System.out.println(sum);
    }

    private static int DFS(int n, int r, int sum) {
        if (n == r) {
            return 1;
        }
        if (r == 1) {
            return n;
        }

        sum += DFS(n - 1, r - 1, sum) + DFS(n - 1, r, sum);
        return sum;
    }
}

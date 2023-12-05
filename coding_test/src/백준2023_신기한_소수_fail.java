import java.util.Scanner;

public class 백준2023_신기한_소수_fail {
    static int N;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();
        DFS(2,1);
        DFS(3,1);
        DFS(5,1);
        DFS(7,1);

    }

    private static void DFS(final int number, final int jarisu) {
        if(jarisu == N){
            if(isPrime(number))
                System.out.println(number);
            return;
        }

        for (int i = 1; i < 10; i++) {
            if(i%2 == 0)
                continue;
            if(isPrime(number*10 + i)){
                DFS(number * 10 + i,jarisu+1);
            }
        }
    }

    private static boolean isPrime(final int number) {
        for (int i = 2; i <= number/2 ; i++) {
            if(number % i == 0)
                return false;
        }
        return true;
    }
}

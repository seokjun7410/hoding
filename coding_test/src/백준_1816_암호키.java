
import java.util.Scanner;

public class 백준_1816_암호키 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        boolean result = false;

        for (int j = 0; j < i; j++) {
            long k = scanner.nextLong();
            for (int l = 2; l <= 1000000; l++) {
                if(k%l == 0) {
                    result = true;
                    break;
                }
            }
            if(!result)
                System.out.println("YES");
            else
                System.out.println("NO");
            result = false;
        }
    }
}

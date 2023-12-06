import java.io.IOException;
import java.util.Scanner;

public class 백준14501_퇴사 {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] D = new int[n+2];
        int[] T = new int[n+1];
        int[] p = new int[n+1];
        for (int i =1; i<=n; i++){
            T[i] = scanner.nextInt();
            p[i] = scanner.nextInt();
        }

        for (int i = n; i > 0; i--) {
            if(i+T[i] > n+1){
                D[i] = D[i+1];
            }else{
                D[i] = Math.max(D[i+1],D[i+T[i]]+p[i]);
            }
        }
        System.out.println(D[1]);


    }
}

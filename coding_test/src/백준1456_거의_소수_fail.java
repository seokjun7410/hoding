import java.util.Scanner;

public class 백준1456_거의_소수_fail {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        long s = scanner.nextLong();
        long N = scanner.nextLong();
        int arr[] = new int[(int) N + 1];
        for (int i = 1; i < N + 1; i++) {
            arr[i] = i;
        }

        int sum = 0;
        int pre = 0;
        for (int i = 2; i < Math.sqrt(N); i++) {
            if (arr[i] == -1)
                continue;
            for (int j = i + i; j < Math.sqrt(N); j = j + i) {
                for (int k = j; k % i == 0; k = k / i) {
                    if (k / i == 1) {
                        pre++;
                        break;
                    }
                }
                arr[j] = -1;
            }
        }


        for (int i = 2; i < Math.sqrt(N); i++) {
            if (arr[i] == 0 || arr[i] == -1)
                continue;

            for (int j = i * i; j <= N; j = j * i) {
                arr[j] = 0;
            }


        }


        long start = 1;
        if(Math.sqrt(N)>=s){
            start = (int) Math.sqrt(N);
            sum = pre;
        }else{
            start = s;
        }
        for (long i = start; i < N + 1; i++) {
            if (i < Math.sqrt(N))
                continue;
            if (arr[(int)i] == 0)
                sum++;
        }
        System.out.println(sum);
    }

}


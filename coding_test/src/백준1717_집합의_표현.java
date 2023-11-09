import java.io.IOException;
import java.util.Scanner;

public class 백준1717_집합의_표현 {
    static int parent[];

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        int M = scanner.nextInt();
        parent = new int[N + 1];
        for (int i = 0; i < N + 1; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < M; i++) {
            int question = scanner.nextInt();
            int a = scanner.nextInt();
            int b = scanner.nextInt();

            if (question == 0) { //union
                union(a, b);
            } else {
                boolean result = checkSame(a,b);
                if(result){
                    System.out.println("YES");
                }else {
                    System.out.println("NO");
                }
            }
        }
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if(a != b){
            parent[b] = a;
        }
    }

    private static int find(final int a) {
        if (a == parent[a]) return a;
        else {
            return parent[a] = find(parent[a]);
        }
    }

    private static boolean checkSame(int a, int b){
        a = find(a);
        b = find(b);
        if(a == b) return true;
        else return false;
    }

}


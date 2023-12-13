import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class 백준9252_최장공통부분수열찾기 {
    static char[] str1;
    static char[] str2;
    static Stack<Character> result = new Stack<>();
    static int dp[][];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        str1 = scanner.next().toCharArray();
        str2 = scanner.next().toCharArray();

        dp = new int[str1.length + 1][str2.length + 1];

        for (int i = 1; i < str1.length + 1; i++) {
            for (int j = 1; j < str2.length + 1; j++) {
                if (str1[i - 1] == str2[j - 1])
                    dp[i][j] = dp[i-1][j-1] +1;
                else {
                    if (dp[i - 1][j] > dp[i][j - 1])
                        dp[i][j] = dp[i - 1][j];
                    else
                        dp[i][j] = dp[i][j - 1];
                }
            }
        }


        getText(str1.length, str2.length);
        System.out.println(result.size());
        while(!result.isEmpty()){
            System.out.print(result.pop());
        }
    }

    private static void getText(int r, int c) {
        if (r == 0 || c == 0) return;
        if (str1[r - 1] == str2[c - 1]) {
            result.add(str1[r - 1]);
            getText(r - 1, c - 1);
        } else {
            if (dp[r - 1][c] > dp[r][c - 1])
                getText(r - 1, c);
            else
                getText(r, c - 1);
        }
    }
}

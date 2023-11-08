import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class 백준1541_잃어버린_괄호 {
    static int answer = 0;
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String ex = scanner.nextLine();
        String[] str = ex.split("-");

        for (int i = 0; i < str.length; i++) {
            int temp = mySum(str[i]);
            if(i==0) answer = answer + temp;
            else{
                answer = answer - temp;
            }
        }
        System.out.println(answer);
    }

    private static int mySum(final String s) {
        int sum = 0;
        String[] temp = s.split("[+]");
        for (int i = 0; i < temp.length; i++) {
            sum = sum + Integer.parseInt(temp[i]);
        }
        return sum;
    }

}


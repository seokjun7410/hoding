import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

class SWEA_1244_최대_상금 {
    static String[] arr;
    static int count;
    static int max = Integer.MIN_VALUE;
    static String temp = "";
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("src/res/input.txt"));
        Scanner scanner = new Scanner(System.in);
        int testCase = scanner.nextInt();

        for (int t = 0; t < testCase; t++) {
            String str = scanner.next();
            arr = str.split("");

            count = scanner.nextInt();
            if(count > arr.length)
                count = arr.length;

            int dfcCount = 0;
            dfs(dfcCount);


            System.out.println(max);

            arr = null;
            max = Integer.MIN_VALUE;
            count = 0;


        }


    }

    private static void dfs(final int c) {
        if(c == count) {
            for (int i = 0; i < arr.length; i++) {
                temp += arr[i];
            }
            int result = Integer.parseInt(temp);
            if(max < result) {
                max = result;
            }
            temp = "";
            return;
        }

        for (int i = 0; i < arr.length-1; i++) {
            for (int j = i+1; j < arr.length; j++) {
                swap(i,j);
                dfs(c+1);
                swap(i,j);
            }
        }
    }

    private static void swap(final int j, final int k) {
        String temp = arr[j];
        arr[j] = arr[k];
        arr[k] = String.valueOf(temp);
    }
}


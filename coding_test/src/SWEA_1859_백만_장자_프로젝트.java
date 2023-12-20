
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

class SWEA_1859_백만_장자_프로젝트 {

    static int[] arr;
    static long amount = 0;
    static int pay = 0;
    static int payCount = 0;
    public static void main(String args[]) throws Exception {

        System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int testCase = Integer.parseInt(br.readLine());

        for (int t = 0; t < testCase; t++) {

            st = new StringTokenizer(br.readLine());
            int count = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            arr = new int[count];
            for (int i = 0; i < count; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            int maxIndex = getMaxIndex(0, count);

            for (int i = 0; i < count; i++) {
                if(i < maxIndex)
                    buy(i);
                else if (i > maxIndex) {
                    maxIndex = getMaxIndex(i, count);
                    if(i < maxIndex)
                        buy(i);
                }
                else {
                    sell(i);
                    maxIndex = getMaxIndex(i+1,count);
                }
            }

            System.out.println("#"+(t+1)+" "+amount);

            amount = 0;
            pay = 0;
            payCount = 0;
        }
    }

    private static void sell(final int index) {
        int memega = arr[index];
        amount += (memega*payCount) - pay;
        pay = 0;
        payCount = 0;
    }

    private static void buy(final int index) {
        int memega = arr[index];
        pay = pay + memega;
        payCount++;
    }

    private static int getMaxIndex(final int start, final int end) {
        int max = Integer.MIN_VALUE;
        int maxIndex = 0;
        for (int i = start; i < end; i++) {
            if(max < arr[i]) {
                max = arr[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }
}


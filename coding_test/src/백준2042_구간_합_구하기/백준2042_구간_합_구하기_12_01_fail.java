package 백준2042_구간_합_구하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준2042_구간_합_구하기_12_01_fail {
    static long tree[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); //수 갯수
        int M = Integer.parseInt(st.nextToken()); //변경 횟수
        int K = Integer.parseInt(st.nextToken()); // 구간합 횟수

        int treeHeight = getTreeHeight(N);

        int treeSize = (int) Math.pow(2, treeHeight) * 2;
        int leafStart = treeSize / 2;
        tree = new long[treeSize + 1];

        leafNodeInit(N, br, treeSize, leafStart);

        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            long b = Long.parseLong(st.nextToken());

            if (cmd == 1) {
                update(a + leafStart - 1, b);
            } else {
                long sum = 0;
                long subSum = getSubSum(a + leafStart - 1, (int) b + leafStart - 1, sum);
                System.out.println(subSum);
            }
        }


    }

    private static long getSubSum(int start, int end, long sum) {


        while (start <= end) {
            if (start % 2 == 1) {
                sum += tree[start];
                start++;
            }
            if (end % 2 == 0) {
                sum += tree[end];
                end--;
            }

            start = (start) / 2;
            end = (end) / 2;
        }
        return sum;
    }

    private static void update(int index, final long updateValue) {
        long preValue = tree[index];
        long willSumValue = updateValue - preValue;

        while (index > 0) {
            tree[index] += willSumValue;
            index /= 2;
        }
    }

    private static void leafNodeInit(final int N, final BufferedReader br, final int treeSize, final int leafStart) throws IOException {
        StringTokenizer st;
        for (int i = leafStart; i < leafStart + N; i++) {
            st = new StringTokenizer(br.readLine());
            long value = Long.parseLong(st.nextToken());
            tree[i] = value;
        }
        for (int i = treeSize - 1; i >= 2; i--) {
            tree[i / 2] += tree[i];
        }

    }

    private static int getTreeHeight(final int N) {
        double v = (Math.log(N) / Math.log(2));
        if (v % 1 != 0)
            v++;
        return (int) v;
    }
}



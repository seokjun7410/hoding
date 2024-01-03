package Inf가장_높은_탑_쌓기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Inf가장_높은_탑_쌓기_01_02 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        StringTokenizer st;
        Bluck[] blucks = new Bluck[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int area = Integer.parseInt(st.nextToken());
            int height = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            blucks[i] = new Bluck(area,height,weight);
        }

        Arrays.sort(blucks);

        int dp[][] = new int[N][2];
        dp[0][0] = blucks[0].height;
        dp[0][1] = blucks[0].weight;

        for (int i = 1; i < N; i++) {
            Bluck bluck = blucks[i];
            dp[i][0] = bluck.height;
            dp[i][1] = bluck.weight;
            for (int j = i; j >= 1; j--) {
                int height = dp[j-1][0];
                int weight = dp[j-1][1];
                if(weight < bluck.weight) {
                    if(dp[i][0] < bluck.height + height) {
                        dp[i][0] = bluck.height + height;
                        dp[i][1] = bluck.weight;
                    }
                }
            }

        }

        System.out.println(Arrays.stream(dp).max(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] > o2[0] ? 1 : -1;
            }
        }).get()[0]);

    }

    private static class Bluck implements Comparable<Bluck> {
        public final int area;
        public final int height;
        public final int weight;

        public Bluck(int area, int height, int weight) {
            this.area = area;
            this.height = height;
            this.weight = weight;
        }

        @Override
        public int compareTo(Bluck o) {
            return this.area > o.area ? 1 : -1;
        }
    }
}

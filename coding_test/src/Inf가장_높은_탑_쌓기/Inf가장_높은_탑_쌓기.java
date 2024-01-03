package Inf가장_높은_탑_쌓기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Inf가장_높은_탑_쌓기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        ArrayList<Bluck> input = new ArrayList<>();
        StringTokenizer st ;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int area = Integer.parseInt(st.nextToken());
            int height = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            input.add(new Bluck(area,height,weight));
        }
        Collections.sort(input);
        int dy[] = new int[N];
        dy[0] = input.get(0).height;

        for (int i = 1; i < N; i++) {
            dy[i] = input.get(i).height;
            int maxHeight = 0;
            for (int j = 0; j < i; j++) {
                    if (input.get(i).wegiht < input.get(j).wegiht)
                        maxHeight = Math.max(maxHeight, dy[j]);
            }

            dy[i] += maxHeight;
        }

        Arrays.sort(dy);
        System.out.println(dy[N - 1]);
    }

    private static class Bluck implements Comparable<Bluck>{
        int area;
        int height;
        int wegiht;

        public Bluck(int area, int height, int wegiht) {
            this.area = area;
            this.height = height;
            this.wegiht = wegiht;
        }

        @Override
        public int compareTo(Bluck o) {
            return this.area < o.area ? 1 : -1;
        }


    }
}

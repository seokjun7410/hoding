package inf_회의실;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class inf_회의실 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st;
        Meeting[] meeting = new Meeting[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            long s = Long.parseLong(st.nextToken());
            long e = Long.parseLong(st.nextToken());
            meeting[i] = new Meeting(s, e);
        }

        Arrays.sort(meeting);


        long preEnd = 0;
        int count = 0;
        for (int i = 0; i < N; i++) {

            if (preEnd <= meeting[i].start) {
                preEnd = meeting[i].end;
                count++;
            }
        }

        System.out.println(count);

    }

    private static class Meeting implements Comparable<Meeting> {
        long start;
        long end;

        public Meeting(long start, long end) {
            this.start = start;
            this.end = end;
        }

        public long getDuring() {
            return end - start;
        }

        @Override
        public int compareTo(Meeting o) {
            if(this.end == o.end)
                return this.start > o.start ? 1 : -1;
            return this.end > o.end ? 1 : -1;
        }
    }
}

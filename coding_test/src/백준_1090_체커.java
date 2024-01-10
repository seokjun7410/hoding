import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 백준_1090_체커 {
    static final int X = 0;
    static final int Y = 1;
    static TempCheckker checkker[];
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());


        checkker = new TempCheckker[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()); //x
            int y = Integer.parseInt(st.nextToken()); //y
            checkker[i] = new TempCheckker(x,y,-1);
        }

        Range range = getSearchRange(checkker);

        long result[] = new long[N];
        result[0] = 0L;

        for (int numberOfPeople = 1; numberOfPeople < N; numberOfPeople++) {
            result[numberOfPeople] = findMinDistanceByNumberOfPeople(range, numberOfPeople + 1);
        }

        StringBuffer sb = new StringBuffer();
        sb.append(0).append(" ");
        for (int i = 1; i < N - 1; i++) {
            sb.append(result[i]).append(" ");
        }
        sb.append(result[N - 1]);

        System.out.println(sb);
    }

    private static long findMinDistanceByNumberOfPeople(Range range, int numberOfPeople) {
        long minDistance = Long.MAX_VALUE;
        for (int rangeX = range.startX; rangeX <= range.endX; rangeX++) {
            for (int rangeY = range.startY; rangeY <= range.endY; rangeY++) {
                PriorityQueue<TempCheckker> queue = sortByDistance(rangeX, rangeY);
                long resultDistance = 0;
                for (int nearlyCheckker = 1; nearlyCheckker < numberOfPeople + 1; nearlyCheckker++) {
                    TempCheckker poll = queue.poll();
                    resultDistance += poll.distance;
                }
                minDistance = Math.min(minDistance, resultDistance);
            }
        }

        return minDistance;
    }

    private static int getDistance(int rangeValue, int checckerValue) {
        return Math.abs(rangeValue - checckerValue);
    }

    private static PriorityQueue<TempCheckker> sortByDistance(int rangeX, int rangeY) {
        PriorityQueue<TempCheckker> queue = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            int nearlyX = checkker[i].x;
            int distanceX = getDistance(rangeX, nearlyX);
            int nearlyY = checkker[i].y;
            int distanceY = getDistance(rangeY, nearlyY);
            int distance = distanceX + distanceY;
            checkker[i].distance = distance;
            queue.add(checkker[i]);
        }
        return queue;
    }

    private static Range getSearchRange(TempCheckker[] input) {


        int startX = Arrays.stream(input).min(TempCheckker.getComparatorX()).get().x;
        int startY = Arrays.stream(input).min(TempCheckker.getComparatorY()).get().y;
        int endX = Arrays.stream(input).max(TempCheckker.getComparatorX()).get().x;
        int endY = Arrays.stream(input).max(TempCheckker.getComparatorY()).get().y;

        return new Range(startX, startY, endX, endY);
    }

    private static class Range {
        int startX;
        int startY;
        int endX;
        int endY;

        public Range(int startX, int startY, int endX, int endY) {
            this.startX = startX;
            this.startY = startY;
            this.endX = endX;
            this.endY = endY;
        }
    }

    private static class TempCheckker implements Comparable<TempCheckker> {
        int x;
        int y;
        long distance;

        public TempCheckker(int x, int y, long distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }

        @Override
        public int compareTo(TempCheckker o) {
            return this.distance > o.distance ? 1 : -1;
        }

        public static Comparator<TempCheckker> getComparatorX(){
            return new Comparator<>() {
                @Override
                public int compare(TempCheckker o1, TempCheckker o2) {
                    return o1.x > o2.x ? 1 : -1;
                }
            };
        }

        public static Comparator<TempCheckker> getComparatorY(){
            return new Comparator<>() {
                @Override
                public int compare(TempCheckker o1, TempCheckker o2) {
                    return o1.y > o2.y ? 1 : -1;
                }
            };
        }
    }
}

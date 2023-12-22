import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_14003_외판원_TSP {

    static int pathMaxCount;
    static int path[][];
    static int start;
    static long[][] dy;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        path = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                path[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        pathMaxCount = 1 << N;
        dy = new long[N][pathMaxCount];


        start = 0;
        int startPath = 1;
        System.out.println(TSP(start, startPath));

    }

    private static long TSP(int currentCity, int currentPathBinary) {
        if (currentPathBinary == pathMaxCount - 1)
            return path[currentCity][start] == 0 ? Integer.MAX_VALUE : path[currentCity][start];

        if (dy[currentCity][currentPathBinary] != 0)
            return dy[currentCity][currentPathBinary];

        int min_val = Integer.MAX_VALUE;
        for (int nextCity = 0; nextCity < N; nextCity++) {
            if(!isVisited(currentPathBinary, nextCity) && path[currentCity][nextCity] != 0){
                min_val = (int) Math.min(min_val, TSP(nextCity, pathAdd(currentPathBinary, nextCity)) + path[currentCity][nextCity]);
            }
        }
        dy[currentCity][currentPathBinary] = min_val;
        return dy[currentCity][currentPathBinary];
    }

    private static int pathAdd(int currentPathBinary, int nextCity) {
        return currentPathBinary | getPathBinary(nextCity);
    }

    private static boolean isVisited(int currentPathBinary, int city) {
        return (currentPathBinary & getPathBinary(city)) != 0;
    }

    private static int getPathBinary(int city) {
        return 1 << city;
    }
}

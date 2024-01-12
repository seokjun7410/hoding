import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class inf_토마토 {

    static Queue<int[]> ripenCoordinateQueue;
    static int tomatoPlate[][];
    static int dx[] = {0, 0, 1, -1};
    static int dy[] = {1, -1, 0, 0};
    static int N;
    static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        ripenCoordinateQueue = new LinkedList<>();

        tomatoPlate = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int tomato = Integer.parseInt(st.nextToken());
                tomatoPlate[i][j] = tomato;
                if (isRipens(tomato)) {
                    int[] coordinate = {i, j};
                    ripenCoordinateQueue.add(coordinate);
                }
            }
        }

        int days = 0;
        int pollCount = 0;
        int queueSize = ripenCoordinateQueue.size();

        while (!ripenCoordinateQueue.isEmpty()) {
            pollCount++;
            int[] ripensCoordinate = ripenCoordinateQueue.poll();
            int x = ripensCoordinate[0];
            int y = ripensCoordinate[1];

            for (int i = 0; i < 4; i++) {
                int aroundX = x + dx[i];
                int aroundY = y + dy[i];

                if (isUnripe(aroundX, aroundY)) {
                    tomatoPlate[aroundX][aroundY] = 1;
                    int[] newCoordinate = {aroundX, aroundY};
                    ripenCoordinateQueue.add(newCoordinate);

                }
            }

            if(queueSize == pollCount){
                days++;
                pollCount = 0;
                queueSize = ripenCoordinateQueue.size();
            }

        }

        boolean impossible = false;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(tomatoPlate[i][j] == 0)
                    impossible = true;
            }
        }

        if(impossible)
            System.out.println(-1);
        else
            System.out.println(days-1);

    }


    private static boolean isUnripe(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < M && tomatoPlate[x][y] == 0;
    }

    private static boolean isRipens(int tomato) {
        return tomato == 1;
    }
}

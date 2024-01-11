import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class inf_미로의_최단거리_통로_BFS {

    static boolean visited[][];
    static int miro[][];

    static int dx[] = {0, 1, -1, 0};
    static int dy[] = {-1, 0, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        miro = new int[7][7];
        visited = new boolean[7][7];

        for (int i = 0; i < 7; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 7; j++) {
                miro[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        miro[0][0] = 0;
        BFS(0, 0);

        int x = miro[6][6];
        if(x == 0) {
            System.out.println("-1");
        }else {
            System.out.println(x);
        }
    }

    private static void BFS(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();

        visited[x][y] = true;
        int[] ints = {x, y};
        queue.add(ints);


        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int nowX = now[0];
            int nowY = now[1];
            visited[nowX][nowY] = true;
            for (int i = 0; i < 4; i++) {
                int nextX = now[0] + dx[i];
                int nextY = now[1] + dy[i];

                if (canMove(nextX, nextY)) {
                    if (!visited[nextX][nextY]) {
                        miro[nextX][nextY] = miro[nowX][nowY] + 1;
                        int[] next = {nextX, nextY};
                        queue.add(next);
                    }
                }

            }
        }


    }

    private static boolean canMove(int nextX, int nextY) {
        return nextX >= 0 && nextY >= 0 && nextY < 7 && nextX < 7 && miro[nextX][nextY] != 1;
    }
}

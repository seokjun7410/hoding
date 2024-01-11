import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class inf_미로의_최단거리_통로_DFS {

    static boolean visited[][];
    static int miro[][];

    static int dx[] = {0, 1, -1, 0};
    static int dy[] = {-1, 0, 0, 1};
    static int count = 0;
    static int minValue = Integer.MAX_VALUE;

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
        DFS(0, 0);

        int x = minValue;
        if(x == Integer.MAX_VALUE) {
            System.out.println("-1");
        }else {
            System.out.println(x);
        }
    }

    private static void DFS(int x, int y) {
        if(visited[x][y])
            return;
        if(x ==6 && y == 6){
            minValue = Math.min(count,minValue);
            return;
        }

        visited(x,y);

        for (int i = 0; i < 4; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];

            if(canMove(nextX,nextY)) {
                if(!visited[nextX][nextY]){
                    count++;
                    DFS(nextX,nextY);
                    count--;
                    visited[nextX][nextY] = false;
            }
        }
    }


}

    private static void visited(int x, int y) {
        visited[x][y] = true;

    }

    private static boolean canMove(int nextX, int nextY) {
        return nextX >= 0 && nextY >= 0 && nextY < 7 && nextX < 7 && miro[nextX][nextY] != 1;
    }
}

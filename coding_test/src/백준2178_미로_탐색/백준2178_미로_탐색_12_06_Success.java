package 백준2178_미로_탐색;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 백준2178_미로_탐색_12_06_Success {

    private static int miro[][];
    private static boolean visited[][];
    private static int[] dx = {0, 1, 0, -1};//하 우 상 좌
    private static int[] dy = {1, 0, -1, 0};
    private static int N;
    private static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        miro = new int[N + 1][M + 1];
        visited = new boolean[N + 1][M + 1];

        for (int i = 1; i < N+1; i++) {
            st = new StringTokenizer(br.readLine());
            char[] charArray = st.nextToken().toCharArray();
            for (int j = 1; j < M+1; j++) {
                miro[i][j] = charArray[j-1] - '0';
            }
        }

        BFS(new MyNode(1, 1));
        System.out.println(miro[N][M]);

    }

    private static void BFS(final MyNode myNode) {
        Queue<MyNode> queue = new LinkedList<>();
        queue.add(myNode);
        visited[myNode.x][myNode.y] = true;

        while (!queue.isEmpty()) {
            MyNode now = queue.poll();

            for (MyNode next : now.canGoNodeList()) {
                if (!visited[next.x][next.y]) {
                    queue.add(next);
                    visited[next.x][next.y] = true;
                    miro[next.x][next.y] = miro[now.x][now.y] + 1;
                }
            }
        }
    }


    static class MyNode {
        int x;
        int y;

        public MyNode(final int x, final int y) {
            this.x = x;
            this.y = y;
        }

        //하 우 상 좌
        private MyNode downNode() {
            return new MyNode(this.x + dy[0], this.y + dx[0]);
        }

        private MyNode rightNode() {
            return new MyNode(this.x + dy[1], this.y + dx[1]);
        }

        private MyNode upNode() {
            return new MyNode(this.x + dy[2], this.y + dx[2]);
        }

        private MyNode leftNode() {
            return new MyNode(this.x + dy[3], this.y + dx[3]);
        }

        public ArrayList<MyNode> canGoNodeList() {
            MyNode allNode[] = {downNode(), rightNode(), upNode(), leftNode()};
            ArrayList<MyNode> canGoNodes = new ArrayList<>();
            int i = 0;
            for (MyNode myNode : allNode) {
                if (nodeValidate(myNode)) {
                    canGoNodes.add(myNode);
                }
            }
            return canGoNodes;
        }

        private boolean nodeValidate(final MyNode myNode) {
            return myNode.x > 0 && myNode.y > 0 && myNode.x <= N && myNode.y <= M && miro[myNode.x][myNode.y] != 0;
        }

    }
}

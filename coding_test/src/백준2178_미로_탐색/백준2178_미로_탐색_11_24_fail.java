package 백준2178_미로_탐색;


import java.util.ArrayList;
import java.util.Scanner;

public class 백준2178_미로_탐색_11_24_fail {
    static long miro[][];
    static int N;
    static int M;
    static int count = 2;

    //우 하 좌 상
    static int[] dy = {1, 0, -1, 0};
    static int[] dx = {0, 1, 0, -1};

    static boolean vistied[][];
    static ArrayList<MyNode> adjacentList[][];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        vistied = new boolean[N + 1][M + 1];
        miro = new long[N + 1][M + 1];
        adjacentList = new ArrayList[N + 1][M + 1];
        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < M + 1; j++) {
                adjacentList[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < N; i++) {
            char[] charArray = sc.next().toCharArray();
            for (int j = 0; j < M; j++) {
                miro[i + 1][j + 1] = charArray[j] - '0';
            }
        }

        move(1, 1);
        System.out.println(miro[N][M] - 1);

    }

    private static void move(final int a, final int b) {
        boolean minus = false;
        if (miro[a][b] == 1 || miro[a][b] >= count) {
            miro[a][b] = count++;
        }
        vistied[a][b] = true;
        if (a == N && b == M) {
            return;
        }

        if (!getICanMove(a, b)) {

            return;
        }

        for (MyNode each : adjacentList[a][b]) {
            if(each.movedA ==N && each.movedB ==M){
                move(each.movedA, each.movedB);
            }else if (miro[each.movedA][each.movedB] == 1) {
                if (!vistied[each.movedA][each.movedB]) {
                    move(each.movedA, each.movedB);
                    if (!minus) {
                        minus = true;
                        count--;
                    }
                }
            }

        }
        if (!minus) {
            minus = true;
            count--;
        }
    }

    private static boolean getICanMove(int a, int b) {
        boolean iCanMove = false;


        for (int i = 0; i < 4; i++) {
            int movedA = a + dx[i];
            int movedB = b + dy[i];

            if (movedA == N && movedB == M) {
                adjacentList[a][b].add(new MyNode(movedA, movedB));
                vistied[movedA][movedB] = false;
                iCanMove = true;
            } else if (0 < movedA && movedA <= N) {
                if (0 < movedB && movedB <= M)
                    if (miro[movedA][movedB] == 1) {
                        if (!vistied[movedA][movedB]) {
                            adjacentList[a][b].add(new MyNode(movedA, movedB));
                            iCanMove = true;
                        }
                    }
            }
        }
        return iCanMove;
    }


    private static class MyNode {
        int movedA;
        int movedB;

        public MyNode(final int movedA, final int movedB) {
            this.movedA = movedA;
            this.movedB = movedB;
        }

    }
}
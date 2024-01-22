package inf_섬나라;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_섬의개수 {

    static int dx[] = {0, -1, -1, 1};
    static int dy[] = {-1, -1, 0, -1};
    static int N;
    static int M;
    static int input[][];
    static MyNode unionFindArray[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());

            if(N == 0 && M == 0)
                break;

            input = new int[N][M];
            unionFindArray = new MyNode[N][M];


            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    unionFindArray[i][j] = new MyNode(-1, -1);

                    int land = Integer.parseInt(st.nextToken());
                    input[i][j] = land;
                    if (land == 1) {
                        unionFindArray[i][j] = new MyNode(i, j);
                        for (int k = 0; k < 4; k++) {
                            int aroundI = i + dy[k];
                            int aroundJ = j + dx[k];
                            if (isRange(aroundI, aroundJ)) {
                                union(i, j, aroundI, aroundJ);
                            }
                        }
                    }
                }
            }


            int count = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    MyNode myNode = find(i, j);
                    if (i == myNode.x && j == myNode.y) {
                        count++;
                    }
                }
            }

            System.out.println(count);
        }
    }

    private static void union(int i, int j, int aroundI, int aroundJ) {
        MyNode coordinate = find(i, j);
        unionFindArray[coordinate.x][coordinate.y] = find(aroundI, aroundJ);
    }

    private static MyNode find(int i, int j) {
        if(i < 0 || j < 0)
            return new MyNode(-1,-1);
        MyNode myNode = unionFindArray[i][j];
        if(i == myNode.x && j == myNode.y)
            return myNode;

        return unionFindArray[i][j] = find(myNode.x, myNode.y);
    }

    private static boolean isRange(int aroundI, int aroundJ) {
        return aroundI >= 0 && aroundJ >= 0 && aroundI < N && aroundJ < M && input[aroundI][aroundJ] == 1;
    }

    private static class MyNode {
        int x;
        int y;

        public MyNode(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}

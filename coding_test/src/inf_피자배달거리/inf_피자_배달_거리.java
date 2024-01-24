package inf_피자배달거리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class inf_피자_배달_거리 {

    static int map[][];
    static int distanceMap[][];

    static int N;

    static MyNode pizzaShop[] = new MyNode[2501];
    static MyNode home[] = new MyNode[2501];
    static int pizzaShopIndex = 0;
    static int homeIndex = 0;

    static int minDistance[];
    static int result = Integer.MAX_VALUE;
    static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];


        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());
                map[i][j] = num;
                if (num == 2)
                    pizzaShop[pizzaShopIndex++] = new MyNode(i, j);
                else if (num == 1)
                    home[homeIndex++] = new MyNode(i, j);
            }
        }

        distanceMap = new int[pizzaShopIndex][homeIndex];

        for (int i = 0; i < pizzaShopIndex; i++) {
            MyNode pizzaShopNode = pizzaShop[i];
            for (int j = 0; j < homeIndex; j++) {
                MyNode homeNode = home[j];
                distanceMap[i][j] = Math.abs(pizzaShopNode.i - homeNode.i) + Math.abs(pizzaShopNode.j - homeNode.j);
            }
        }

        minDistance = new int[homeIndex];
        Arrays.fill(minDistance, Integer.MAX_VALUE);
        DFS(0, 0, minDistance.clone());

        System.out.println(result);

    }

    private static void DFS(int L, int pizzaIndex, int[] distance) {

        if (L == M) {
            int sum = 0;
            for (int i = 0; i < homeIndex; i++) {
                sum += distance[i];
            }
            result = Math.min(sum, result);
            return;
        }

        if(pizzaIndex >= pizzaShopIndex)
            return;

        int newDistance[] = new int[homeIndex];
        for (int i = 0; i < homeIndex; i++) {
            newDistance[i] = Math.min(distanceMap[pizzaIndex][i], distance[i]);
        }


        DFS(L + 1, pizzaIndex + 1, newDistance.clone());
        DFS(L, pizzaIndex + 1, distance.clone());
    }



    private static class MyNode {
        int i;
        int j;

        public MyNode(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }
}

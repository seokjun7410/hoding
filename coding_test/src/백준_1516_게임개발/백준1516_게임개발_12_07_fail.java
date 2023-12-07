package 백준_1516_게임개발;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 백준1516_게임개발_12_07_fail {

    static ArrayList<Integer> adjacencyList[];
    static int timeArray[];
    static int entryArray[];
    static int temp[];
    static boolean visited[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        adjacencyList = new ArrayList[N + 1];
        timeArray = new int[N + 1];
        entryArray = new int[N + 1];
        temp = new int[N + 1];
        visited = new boolean[N + 1];


        for (int i = 1; i < N + 1; i++) {
            adjacencyList[i] = new ArrayList<>();

            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            timeArray[i] = num;
            temp[i] = num;
            while (true) {
                num = Integer.parseInt(st.nextToken());
                if (num == -1)
                    break;
                adjacencyList[num].add(i);
                entryArray[i]++;
            }
        }

        for (int i = 1; i < N + 1; i++) {
            if (entryArray[i] == 0) {
                BFS(i);
                break;
            }
        }
        for (int i = 1; i < N + 1; i++) {
            System.out.println(timeArray[i]);
        }
    }

    private static void BFS(int v) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(v);
        while (!queue.isEmpty()) {
            int now = queue.poll();
            for (int next : adjacencyList[now]) {
                entryArray[next]--;
                if(!visited[next])
                    timeArray[next] = timeArray[next] + timeArray[now];
                else
                    timeArray[next] = Math.max(timeArray[next],timeArray[now] + temp[next]);
                visited[next] = true;
                if (entryArray[next] == 0) {
                    queue.add(next);
                }

            }

        }
    }
}








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
    static int result[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        adjacencyList = new ArrayList[N + 1];
        timeArray = new int[N + 1];
        entryArray = new int[N + 1];
        result = new int[N + 1];


        for (int i = 1; i < N + 1; i++) {
            adjacencyList[i] = new ArrayList<>();

            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            timeArray[i] = num;
            while (true) {
                num = Integer.parseInt(st.nextToken());
                if (num == -1)
                    break;
                adjacencyList[num].add(i);
                entryArray[i]++;
            }
        }


        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i < N + 1; i++) {
            if (entryArray[i] == 0)
                queue.add(i);
        }

        while (!queue.isEmpty()) {
            int now = queue.poll();
            for (int next : adjacencyList[now]) {
                entryArray[next]--;
                result[next] = Math.max(result[next], timeArray[now] + result[now]);

                if (entryArray[next] == 0) {
                    queue.add(next);
                }

            }

        }


        for (int i = 1; i < N + 1; i++) {
            System.out.println(timeArray[i]+result[i]);
        }
    }


}








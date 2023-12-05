package 백준_11724_연결요소의개수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 백준11724_연결_요소_개수_12_05_Success {

    private static ArrayList<Integer> adjacencyList[];
    private static boolean visited[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        visited = new boolean[N+1];
        adjacencyList = new ArrayList[N+1];
        for (int i = 1; i < N+1; i++) {
            adjacencyList[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            adjacencyList[s].add(e);
            adjacencyList[e].add(s);
        }

        int answer = 0;
        for (int i = 1; i < N + 1; i++) {
            if(!visited[i]) {
                BFS(i);
                answer++;
            }
        }

        System.out.println(answer);
    }

    private static void BFS(final int v) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(v);
        visited[v] = true;

        while(!queue.isEmpty()){
            int now = queue.poll();
            for (int next: adjacencyList[now]) {
                if(!visited[next]) {
                    queue.add(next);
                    visited[next] = true;
                }
            }
        }
    }
}
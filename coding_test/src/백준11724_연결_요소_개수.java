import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class 백준11724_연결_요소_개수 {

    /**
     * 방향 없는 그래프가 주어졌을 때, 연결 요소 (Connected Component)의 개수를 구하는 프로그램을 작성하시오.
     * 첫째 줄에 정점의 개수 N과 간선의 개수 M이 주어진다. (1 ≤ N ≤ 1,000, 0 ≤ M ≤ N×(N-1)/2) 둘째 줄부터 M개의 줄에 간선의 양 끝점 u와 v가 주어진다.
     * (1 ≤ u, v ≤ N, u ≠ v) 같은 간선은 한 번만 주어진다.
     */

    static boolean visited[];
    static ArrayList<Integer>[] arrayLists;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        visited = new boolean[N+1];
        arrayLists = new ArrayList[N+1];

        for (int i = 1; i < N+1; i++) {
           arrayLists[i] = new ArrayList<>();
        }

        for (int i = 1; i < M+1; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            arrayLists[start].add(end);
            arrayLists[end].add(start);
        }


        int count = 0;
        for (int i = 1; i < N+1; i++) {
            if(!visited[i]){
                count++;
                DFS(i);
            }
        }

        System.out.println(count);

    }

    private static void DFS(final int v) {

        if(visited[v])
            return;

        visited[v] = true;
        for (int i : arrayLists[v]) {
            if(!visited[i])
                DFS(i);
        }

    }
}
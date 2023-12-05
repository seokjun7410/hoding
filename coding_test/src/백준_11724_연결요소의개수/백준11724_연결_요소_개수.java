package 백준_11724_연결요소의개수;

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

    static ArrayList<Integer> list[];
    static boolean visited[];
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int vertexCount = Integer.parseInt(st.nextToken());
        int edgeCount = Integer.parseInt(st.nextToken());
        visited = new boolean[vertexCount +1];
        list = new ArrayList[vertexCount+1];
        for (int i = 0; i < vertexCount+1; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < edgeCount; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            list[s].add(e);
            list[e].add(s);
        }


        int anwser = 0;
        for (int i = 1; i < vertexCount+1; i++) {
            if(!visited[i]) {
                anwser++;
                DFS(i);
            }
        }

        System.out.println(anwser);
    }

    private static void DFS(int v) {
//        if(visited[v])
//            return;

        visited[v] = true;
        for (int i : list[v]) {
            if(!visited[i]) {
                DFS(i);
            }
        }
    }
}
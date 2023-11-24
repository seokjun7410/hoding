package 백준1260_DFS와_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 백준1260_DFS와_BFS_11_24_SUCCESS {

    static ArrayList<Integer> adjacentList[];
    static boolean visited[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); //정점의 갯수
        int M = Integer.parseInt(st.nextToken()); //간선의 갯수
        int V = Integer.parseInt(st.nextToken()); //탐색을 시작할 정점 번호

        visited = new boolean[N+1]; //노드 방문 배열 1~ <= N
        adjacentList = new ArrayList[N+1]; //인접리스트 세로 1~ <=N
        for (int i = 1; i <= N; i++) {
            adjacentList[i] = new ArrayList<>();
        }

        //간선이 연결하는 두 정점의 번호 - 양방향
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            adjacentList[s].add(e);
            adjacentList[e].add(s);
        }

        //작은 노드부터 탐색하기 위한 정렬
        for (int i = 1; i < N+1; i++) {
            Collections.sort(adjacentList[i]);
        }

        DFS(V);
        System.out.println();
        visited = new boolean[N+1]; //초기화
        BFS(V);

    }

    private static void BFS(final int v) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(v);
        visited[v] = true;

        while(!queue.isEmpty()){
            Integer now = queue.poll();
            System.out.print(now+ " ");
            for(Integer each : adjacentList[now]){
                if(!visited[each]) {
                    queue.add(each);
                    visited[each] = true;
                }
            }
        }
    }

    private static void DFS(final int v) {

        System.out.print(v+" ");
        visited[v] = true;

        for (Integer each : adjacentList[v]) {
            if(!visited[each]){
                DFS(each);
            }
        }

    }

}
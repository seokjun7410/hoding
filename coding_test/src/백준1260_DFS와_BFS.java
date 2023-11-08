import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 백준1260_DFS와_BFS {
    static boolean visited[];
    static ArrayList<Integer>[] A;
    static StringBuffer stringBuffer;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int V = Integer.parseInt(st.nextToken());

        A = new ArrayList[N+1];

        for (int i = 1; i < N+1; i++) {
            A[i] = new ArrayList<>();
        }

        for (int i = 1; i< M+1; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            A[start].add(end);
            A[end].add(start);
        }

        for (int i = 1; i < N+1; i++) {
            Collections.sort(A[i]);
        }

        stringBuffer = new StringBuffer();

        visited = new boolean[N+1];
        DFS(V);
        System.out.println();
        visited = new boolean[N+1];
        BFS(V);

    }

    private static void BFS(final int v) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(v);
        visited[v] = true;

        while(!queue.isEmpty()){
            int now = queue.poll();
            System.out.print(now+" ");
            for (int i : A[now]){
                if(!visited[i]){
                    visited[i] = true;
                    queue.add(i);
                }
            }
        }
    }

    public static void DFS(int v){
        System.out.print(v+" ");
        visited[v] = true;
        for (int i : A[v]){
            if(!visited[i]){
                DFS(i);
            }
        }

    }

}
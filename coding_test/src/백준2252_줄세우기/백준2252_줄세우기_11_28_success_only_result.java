package 백준2252_줄세우기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class 백준2252_줄세우기_11_28_success_only_result {
    static boolean visited[];
    static Stack<Integer> reversResult = new Stack<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        ArrayList<Integer> adjacencyList[] = new ArrayList[N + 1];
        for (int i = 1; i < N + 1; i++) {
            adjacencyList[i] = new ArrayList<>();
        }

        int entryLevel[] = new int[N + 1];
        visited = new boolean[N + 1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int e = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            adjacencyList[s].add(e);
            entryLevel[e]++;
        }

        while (true) {
            int zeroIndex = findZeroIndex(entryLevel);
            if(zeroIndex == -1)
                break;
            visited[zeroIndex] = true;
            reversResult.add(zeroIndex);
            for (int v : adjacencyList[zeroIndex]) {
                entryLevel[v]--;
            }
        }

        while (!reversResult.isEmpty()){
            System.out.print(reversResult.pop()+" ");
        }
    }

    private static int findZeroIndex(final int[] entryLevel) {
        for (int i = 1; i < entryLevel.length; i++) {
            if (entryLevel[i] == 0)
                if(!visited[i])
                    return i;
        }

        return -1;
    }
}

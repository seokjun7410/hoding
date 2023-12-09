package 백준1753_최단_경로_구하기;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 백준1753_최단_경로_구하기_12_08 {

    static ArrayList<MyNode> adjacencyList[];
    static int distance[];
    static boolean[] vistied;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(br.readLine());

        adjacencyList = new ArrayList[V + 1];
        for (int i = 1; i < V + 1; i++) {
            adjacencyList[i] = new ArrayList<>();
        }

        distance = new int[V + 1];
        for (int i = 0; i < V + 1; i++) {
            distance[i] = Integer.MAX_VALUE;
        }

        vistied = new boolean[V + 1];

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            adjacencyList[u].add(new MyNode(v, w));

        }

        BFS(new MyNode(start, 0));

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i < V + 1; i++) {
            int x = distance[i];
            if (x == Integer.MAX_VALUE)
                stringBuilder.append("INF\n");
            else
                stringBuilder.append(x).append("\n");
        }
        System.out.println(stringBuilder);
    }

    private static void BFS(MyNode myNode) {
        PriorityQueue<MyNode> queue = new PriorityQueue<>();
        queue.add(myNode);
        distance[myNode.no] = myNode.w;
        while (!queue.isEmpty()) {
            MyNode now = queue.poll();
//            if(vistied[now.no]){
//                System.out.println(now.no);
//                continue;
//            }

            vistied[now.no] = true;
            for (MyNode next : adjacencyList[now.no]) {

                if (distance[next.no] > distance[now.no] + next.w) {
                    distance[next.no] = distance[now.no] + next.w;
                    queue.add(new MyNode(next.no,distance[next.no]));
                }
            }

        }
    }


    private static class MyNode implements Comparable<MyNode> {
        int no;
        int w;

        public MyNode(int no, int w) {
            this.no = no;
            this.w = w;
        }

        @Override
        public int compareTo(MyNode o) {
            if (this.w > o.w)
                return 1;
            else
                return -1;
        }
    }
}
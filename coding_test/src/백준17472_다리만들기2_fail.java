//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.*;
//
//public class 백준17472_다리만들기2_fail {
//    static Coordinate[][] unionFind;
//    static int[] dr = {-1, 0, 1, 0};
//    static int[] dc = {0, 1, 0, -1};
//    static int[] parent;
//    static int[][] map;
//    static int N, M, sNum;
//    static boolean[][] visited;
//    static ArrayList<ArrayList<int[]>> sumlist;
//    static ArrayList<int[]> mlist;
//    static PriorityQueue<Edge> queue;
//
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//
//        int N = Integer.parseInt(st.nextToken());
//        int M = Integer.parseInt(st.nextToken());
//
//        unionFind = new Coordinate[N + 1][M + 1];
//        for (int i = 1; i < N + 1; i++) {
//            for (int j = 1; j < M + 1; j++) {
//                unionFind[i][j] = new Coordinate(i, j);
//            }
//        }
//        int input[][] = new int[N + 1][M + 1];
//
//        for (int i = 1; i < N + 1; i++) {
//            st = new StringTokenizer(br.readLine());
//            for (int j = 1; j < M + 1; j++) {
//                input[i][j] = Integer.parseInt(st.nextToken());
//            }
//        }
//
//        sNum = 1;
//        sumlist = new ArrayList<>();
//        for (int i = 1; i < N + 1; i++) {
//            for (int j = 1; j < M + 1; j++) {
//                if (input[i][j] != 0 && visited[i][j] != true) {
//                    BFS(i, j);
//                    sNum++;
//                    sumlist.add(mlist);
//                }
//            }
//        }
//
//        queue = new PriorityQueue<>();
//        for (int i = 0; i < sumlist.size(); i++) {
//            ArrayList<int[]> now = sumlist.get(i);
//            for (int j = 0; j < now.size(); j++) {
//                int r = now.get(j)[0];
//                int c = now.get(j)[1];
//
//            }
//        }
//
//        for (int i = 1; i < N + 1; i++) {
//            for (int j = 1; j < M + 1; j++) {
//                System.out.print(unionFind[i][j] + " ");
//            }
//            System.out.println();
//        }
//    }
//
//    private static void BFS(int i, int j) {
//        Queue<int[]> queue = new LinkedList<>();
//        mlist = new ArrayList<>();
//        int[] start = {i, j};
//        queue.add(start);
//        mlist.add(start);
//
//        visited[i][j] = true;
//        input[i][j] = sNum;
//        while (!queue.isEmpty()) {
//            int[] now = queue.poll();
//            int r = now[0];
//            int c = now[1];
//            for (int d = 0; d < 4; d++) {
//                int tempR = dr[d];
//                int tempC = dc[d];
//                while (r + tempR > 0 && r + tempR < N + 1 && c + tempC > 0 && c + tempC < M + 1) {
//                    if (visited[r + tempR][c + tempC] == false && input[r + tempR][c + tempC] != 0) {
//                        addNode(r + tempR, c + tempC, queue);
//                    } else
//                        break;
//                    if (tempR < 0) tempR--;
//                    else if (tempR > 0) tempR++;
//                    else if (tempC < 0) tempC--;
//                    else if (tempC > 0) tempC++;
//                }
//            }
//        }
//    }
//
//    private static void addNode(int i, int j, Queue<int[]> queue) {
//        input[i][j] = sNum;
//        visited[i][j] = true;
//        int[] temp = {i, j};
//        mlist.add(temp);
//        queue.add(temp);
//    }
//
//    private static void union(int i, int j, int ii, int jj) {
//        Coordinate c = find(new Coordinate(i, j));
//        unionFind[c.i][c.j] = find(new Coordinate(ii, jj));
//    }
//
//    private static Coordinate find(Coordinate c) {
//        if (unionFind[c.i][c.j].equals(c))
//            return c;
//
//        return unionFind[c.i][c.j] = find(unionFind[c.i][c.j]);
//    }
//
//    private static class Coordinate {
//        int i;
//        int j;
//
//        public Coordinate(int i, int j) {
//            this.i = i;
//            this.j = j;
//        }
//
//        @Override
//        public String toString() {
//            return new StringJoiner(", ", Coordinate.class.getSimpleName() + "[", "]")
//                    .add("i=" + i)
//                    .add("j=" + j)
//                    .toString();
//        }
//
//        @Override
//        public boolean equals(Object o) {
//            if (this == o) return true;
//            if (o == null || getClass() != o.getClass()) return false;
//            Coordinate that = (Coordinate) o;
//            return i == that.i && j == that.j;
//        }
//
//        @Override
//        public int hashCode() {
//            return Objects.hash(i, j);
//        }
//    }
//
//    private static class Edge implements Comparable<Edge> {
//        int s, e, v;
//
//        public Edge(int s, int e, int v) {
//            this.s = s;
//            this.e = e;
//            this.v = v;
//        }
//
//        @Override
//        public int compareTo(Edge o) {
//            return this.v > o.v ? 1 : -1;
//        }
//    }
//}

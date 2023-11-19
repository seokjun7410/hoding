import java.io.*;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class 백준11003_최솟값_찾기 {


    public class Main {
        public static void main(String[] args) throws IOException {

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

            int N = Integer.parseInt(stringTokenizer.nextToken());
            int L = Integer.parseInt(stringTokenizer.nextToken());


            int[] A = new int[N];
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int i = 0; i < N; i++) {
                int a = Integer.parseInt(stringTokenizer.nextToken());
                A[i] = a;
            }

            Deque<Node> deq = new LinkedList<>();

            for (int i = 0; i < N; i++) {
                Node node = new Node(i, A[i]);
                while(deq.size() > 0 && deq.getLast().value > node.value){
                    deq.removeLast();
                }
                deq.addLast(node);

                if(deq.getLast().index - deq.getFirst().index >= L)
                    deq.removeFirst();

                Node peek = deq.getFirst();
                bufferedWriter.write(peek.value+" ");
            }
            bufferedWriter.flush();
            bufferedWriter.close();
        }

        private static class Node {
            int index;
            int value;

            public Node(final int index, final int value) {
                this.index = index;
                this.value = value;
            }
        }
    }

}


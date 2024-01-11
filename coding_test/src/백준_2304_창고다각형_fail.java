import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 백준_2304_창고다각형_fail {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st;
        ArrayList<Nod> input = new ArrayList<>();
        input.add(new Nod(0,0));
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int height = Integer.parseInt(st.nextToken());
            input.add(new Nod(x, height));
        }
        Collections.sort(input);
        input.add(new Nod(input.get(N).x+1,0));

        int left = 1;
        int right = N;
        int heightArray[] = new int[input.get(right+1).x + 1];
        while (left <= right) {

            Nod leftNode = input.get(left);
            Nod rightNode = input.get(right);

            for (int i = input.get(left-1).x+1; i < leftNode.x; i++) {
                heightArray[i] = heightArray[input.get(left-1).x];
            }
            for (int i = input.get(right+1).x-1; i > rightNode.x; i--) {
                heightArray[i] = heightArray[input.get(right+1).x];
            }

            if (leftNode.height > heightArray[input.get(left-1).x]) {
                heightArray[leftNode.x] = leftNode.height;
            }else{
                heightArray[leftNode.x] = heightArray[input.get(left-1).x];
            }

            if (rightNode.height > heightArray[input.get(right+1).x]) {
                heightArray[rightNode.x] = rightNode.height;
            }else{
                heightArray[rightNode.x] = heightArray[input.get(right+1).x];
            }

            left++;
            right--;
        }

        int sum = 0;
        for (int i : heightArray) {sum+= i;}
        System.out.println(sum);


    }

    private static class Nod implements Comparable<Nod> {
        int x;
        int height;

        public Nod(int x, int height) {
            this.x = x;
            this.height = height;
        }

        @Override
        public int compareTo(Nod o) {
            return this.x > o.x ? 1 : -1;
        }
    }
}

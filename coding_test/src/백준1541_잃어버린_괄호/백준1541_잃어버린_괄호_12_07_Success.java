package 백준1541_잃어버린_괄호;

import java.lang.*;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 백준1541_잃어버린_괄호_12_07_Success {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        char[] charArray = scanner.next().toCharArray();

        StringBuffer sb = new StringBuffer();
        boolean minus = false;
        int sum = 0;
        Queue<String> queue = new LinkedList<>();
        for (int i = 0; i < charArray.length; i++) {
            char c = charArray[i];

            if (c == '-' || c == '+') {
                queue.add(sb.toString());
                sb = new StringBuffer();
                queue.add(String.valueOf(c));
            } else {
                sb.append(c);
            }

        }
        queue.add(sb.toString());

        sum += Integer.parseInt(queue.poll());
        while (!queue.isEmpty()) {
            String poll = queue.poll();
            int N = Integer.parseInt(queue.poll());
            if (poll.equals("+"))
                if (minus) {
                    sum += N * -1;
                } else
                    sum += N;
            else if (poll.equals("-")) {
                minus = true;
                sum += N * -1;
            }
        }

        System.out.println(sum);
    }
}


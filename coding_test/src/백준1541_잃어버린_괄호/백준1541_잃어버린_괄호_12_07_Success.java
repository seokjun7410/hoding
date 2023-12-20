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

        StringBuffer stringBuffer = new StringBuffer();
        boolean preMinusExist = false;
        int sum = 0;
        Queue<String> queue = new LinkedList<>();
        for (int i = 0; i < charArray.length; i++) {
            char c = charArray[i];

            if (c == '-' || c == '+') {
                queue.add(stringBuffer.toString());
                stringBuffer = new StringBuffer();
                queue.add(String.valueOf(c));
            } else {
                stringBuffer.append(c);
            }

        }
        queue.add(stringBuffer.toString());

        sum += Integer.parseInt(queue.poll());
        while (!queue.isEmpty()) {
            String operation = queue.poll();
            int num = Integer.parseInt(queue.poll());
            if (operation.equals("+"))
                if (preMinusExist) {
                    sum -= num ;
                } else
                    sum += num;
            else if (operation.equals("-")) {
                preMinusExist = true;
                sum -= num;
            }
        }

        System.out.println(sum);
    }
}


import java.io.IOException;
import java.util.*;

public class 백준1918_후위_표기식 {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String question = scanner.next();

        char[] charArray = question.toCharArray();
        Stack<Character> s = new Stack<>();
        for (int i = 0; i < charArray.length; i++) {
            char c = charArray[i];
            if (c == '-' || c == '+' || c == '*' || c == '/' || c == '(' || c == ')') {
                if (s.isEmpty()) {
                    s.push(c);
                    continue;
                }
                if (c == ')') {
                    while (s.peek() != '(') {
                        Character pop = s.pop();
                        System.out.print(pop);
                    }
                    s.pop();
                    continue;
                } else if (c != '(' && priority(c) <= priority(s.peek())) {
                    while(!s.isEmpty() && priority(c) <= priority(s.peek())) {
                        Character pop = s.pop();
                        System.out.print(pop);
                    }
                }
                s.push(c);
            } else {
                System.out.print(c);
            }

        }

        while (!s.isEmpty()) {
            Character pop = s.pop();
            System.out.print(pop);
        }
    }

    private static int priority(final Character c) {
        if (c == '+') {
            return 0;
        } else if (c == '-') {
            return 0;
        } else if (c == '*') {
            return 1;
        } else if (c == '/') {
            return 1;
        } else
            return -1;
    }
}


import java.security.Key;
import java.util.Scanner;

public class 백준8892_팰린드롬 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();


        for (int i = 0; i < T; i++) {
            int k = sc.nextInt();
            String words[] = new String[k];

            for (int j = 0; j < k; j++) {
                words[j] = sc.next();
            }

            boolean ok = false;
            for (int j = 0; j < k-1; j++) {
                for (int l = j+1; l < k; l++) {
                    if (isPalindrom(words[j],words[l])) {
                        ok = true;
                        System.out.println(words[j]+words[l]);
                        break;
                    }
                    if (isPalindrom(words[l],words[j])) {
                        ok = true;
                        System.out.println(words[l]+words[j]);
                        break;
                    }
                }
                if(ok)
                    break;
            }

            if(!ok)
                System.out.println(0);
        }
    }

    private static boolean isPalindrom(final String a, final String b) {
        boolean ok = true;

        String target = a + b;
        int s = 0;
        int e = target.length()-1;

        while (s < e){
            char sChar = target.charAt(s);
            char eChar = target.charAt(e);
            if(sChar != eChar){
                ok = false;
                break;
            }
            s++;
            e--;
        }

        return ok;
    }


}
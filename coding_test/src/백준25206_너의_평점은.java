import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준25206_너의_평점은 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;


        double scoreTotal = 0;
        double majorTotal = 0;
        for (int i = 0; i < 20; i++) {

            st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            double score = Double.parseDouble(st.nextToken());
            String rank = st.nextToken();
            if (rank.equals("P")) {
                continue;
            }
            scoreTotal += score * getRankScore(rank);
            majorTotal += score;

        }

        System.out.println(scoreTotal / majorTotal);

    }

    private static double getRankScore(final String rank) {
        if (rank.equals("A+")) {
            return 4.5;
        } else if (rank.equals("A0")) {
            return 4.0;
        } else if (rank.equals("B+")) {
            return 3.5;
        } else if (rank.equals("B0")) {
            return 3.0;
        } else if (rank.equals("C+")) {
            return 2.5;
        } else if (rank.equals("C0")) {
            return 2.0;
        } else if (rank.equals("D+")) {
            return 1.5;
        } else if (rank.equals("D0")) {
            return 1.0;
        } else {
            return 0;

        }

    }
}



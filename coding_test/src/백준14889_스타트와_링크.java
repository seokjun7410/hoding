import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 백준14889_스타트와_링크 {

    static int N;
    static int[][] score;
    static ArrayList<Integer> teamA;
    static ArrayList<Integer> teamB;
    static int MIN = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        teamA = new ArrayList<>();
        teamB = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        score = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                score[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int teamNumber = N / 2;
        recuresive(0, teamNumber);
        System.out.println(MIN);

    }

    private static void recuresive(final int order, final int teamNumber) {

        if (teamNumber == 0) {
            for (int i = 0; i < N; i++) {
                if (!teamA.contains(i)) {
                    teamB.add(i);
                }
            }
            checkScore();
            teamB.clear();
            return;
        }

        for (int i = order; i < N; i++) {
            teamA.add(i);
            recuresive(i + 1, teamNumber - 1);
            teamA.remove(teamA.size() - 1);
        }
    }

    private static void checkScore() {
        int teamAScore = 0;
        int teamBScore = 0;

        for (int i = 0; i < N / 2 + 1; i++) {
            for (int j = i + 1; j < N / 2; j++) {
                int teamA_i = teamA.get(i);
                int teamA_j = teamA.get(i);
                teamAScore += score[teamA_i][teamA_j] + score[teamA_j][teamA_i];

                int teamB_i = teamB.get(i);
                int teamB_j = teamB.get(i);
                teamBScore += score[teamB_i][teamB_j] + score[teamB_j][teamB_i];
            }
        }

        int result = Math.abs(teamAScore - teamBScore);
        MIN = Math.min(MIN,result);
    }


}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class inf_최대점수_구하기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int limitTime = Integer.parseInt(st.nextToken());


        Question input[] = new Question[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int score = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            input[i] = new Question(score,time);
        }

        Arrays.sort(input);

        int currentDy[] = new int[limitTime+1];


        for (int currentQuestion = 0; currentQuestion < N; currentQuestion++) {
            for (int j = limitTime; j >= 1; j--) {
                if(j-input[currentQuestion].time >= 0)
                    currentDy[j] = Math.max(currentDy[j-input[currentQuestion].time] + input[currentQuestion].score,currentDy[j]);
            }
        }

        System.out.println(currentDy[limitTime]);

    }

    private static class Question implements Comparable<Question>{
        int score;
        int time;
        public Question(int score, int time) {
            this.score = score;
            this.time = time;
        }

        @Override
        public int compareTo(Question o) {
            return this.score > o.score ? 1 : -1;
        }
    }
}
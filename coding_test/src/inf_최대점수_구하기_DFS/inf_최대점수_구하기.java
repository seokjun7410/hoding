package inf_최대점수_구하기_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.StringTokenizer;

public class inf_최대점수_구하기 {

    static int maxValue;
    static int limit;
    static Problem[] input;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        limit = Integer.parseInt(st.nextToken());

        input = new Problem[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int score = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            input[i] = new Problem(score,time);
        }

        DFS(0,new Problem(0,0));
        System.out.println(maxValue);
    }

    private static void DFS(int index, Problem problem) {
        if (problem.time > limit) {
            return;
        }
        if(index >= N){
            maxValue = Math.max(maxValue,problem.score);
            return;
        }

        int sumScore = problem.score + input[index].score;
        int sumTime = problem.time + input[index].time;


        DFS(index+1,new Problem(sumScore,sumTime));
        DFS(index+1,problem);
    }

    private static class Problem {
        int score;
        int time;

        public Problem(int score, int time) {
            this.score = score;
            this.time = time;
        }
    }
}

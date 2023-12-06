package 백준_1516_게임개발;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 백준1516_게임개발_11_29_fail {

    static int[] timeTable;
    static int[] selfBuild;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        timeTable = new int[N + 1];
        selfBuild = new int[N + 1];
        for (int i = 1; i < N + 1; i++) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            selfBuild[i] = t;

            int count = 0;
            while (true) {
                count++;
                int v = Integer.parseInt(st.nextToken());
                if (v == -1) {
                    if(count == 1)
                        timeTable[i] = selfBuild[i];
                    break;
                }
                timeTable[i] = Math.max(timeTable[i], selfBuild[i] + timeTable[v]);
            }
        }
        for (
                int i = 1;
                i < N + 1; i++) {
            System.out.println(timeTable[i]);
        }


    }


}




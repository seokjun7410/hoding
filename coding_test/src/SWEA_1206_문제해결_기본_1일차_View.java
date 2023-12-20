
import java.util.Scanner;
import java.io.FileInputStream;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class SWEA_1206_문제해결_기본_1일차_View {
    static int N;
    static int[] building;

    public static void main(String args[]) throws Exception {

//        System.setIn(new FileInputStream("src/res/input.txt"));
        Scanner sc = new Scanner(System.in);

        int T = 10;
        for (int tc = 1; tc <= T; tc++) {
            N = sc.nextInt(); // 가로 길이
            building = new int[N];
            for (int i = 0; i < N; i++) {
                building[i] = sc.nextInt();
            }

            int cnt = 0;
            for (int i = 2; i < N - 2; i++) {
                int max = Math.max(building[i - 2], Math.max(building[i - 1], Math.max(building[i + 1], building[i + 2])));
                if (building[i] - max > 0)
                    cnt += building[i] - max;
            }

            System.out.println("#" + tc + " " + cnt);
        } // end of TC
    } // end of Main
}


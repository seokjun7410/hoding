package 백준1920_수_찾기;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 백준1920_수_찾기_10_23_fail {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int A[] = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(A);

        int M = Integer.parseInt(br.readLine());
        int question[] = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M ; i++) {
            question[i] = Integer.parseInt(st.nextToken());
        }


        for (int i = 0; i < M; i++) {
            int start = 0;
            int end = N-1;
            boolean find = false;
            while (start <= end) {
                int mid = (start + end) / 2;
                if (A[mid] > question[i])
                    end = mid-1;
                else if (A[mid] < question[i])
                    start = mid+1;
                else { //A[mid] = question[i]
                    find = true;
                    break;
                }
            }
            if(find){
                System.out.println(1);
            }else{
                System.out.println(0);
            }
        }
    }

}


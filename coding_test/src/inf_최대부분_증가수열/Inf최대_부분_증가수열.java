package inf_최대부분_증가수열;

import java.util.*;

public class Inf최대_부분_증가수열 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int arr[] = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        int dy[] = new int[n];

        dy[0] = 1;
        for (int i = 1; i < n; i++) {
            int x = arr[i];
            List<Integer> maxList = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                if(arr[j] < x)
                    maxList.add(dy[j]+1);
            }
            if(!maxList.isEmpty()) {
                dy[i] = maxList.stream().max(Integer::compareTo).get();
            }else
                dy[i] = 1;
        }

        Arrays.sort(dy);
        System.out.println(dy[n-1]);


    }
}

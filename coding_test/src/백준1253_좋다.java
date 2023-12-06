import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 백준1253_좋다 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(bufferedReader.readLine());
        int anwser = 0;
        long[] arr = new long[count];

        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < count; i++) {
            arr[i] = Long.parseLong(stringTokenizer.nextToken());
        }
        Arrays.sort(arr);

        for (int targetIndex = 0; targetIndex < count; targetIndex++) {
            long target = arr[targetIndex];
            int left = 0;
            int right = count-1;

            while(left < right){

                long S = arr[left] + arr[right];
                if(S == target){
                    if(left != targetIndex && right != targetIndex) {
                        anwser++;
                        break;
                    } else if (left == targetIndex) {
                        left++;
                    } else if (right == targetIndex) {
                        right--;
                    }
                }else if(S < target){
                    left++;
                }
                else {
                    right--;
                }

            }

        }

        System.out.println(anwser);
    }

}


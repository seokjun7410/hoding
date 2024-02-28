package sw_마에스트로;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_2630_색종이만들기 {

	private static int white = 0;
	private static int blue = 0;
	private static int[][] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		arr = new int[n + 1][n + 1];
		StringTokenizer st;
		for (int i = 1; i < n + 1; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j < n + 1; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		recursive(1, 1, n);
		System.out.println(white);
		System.out.println(blue);
	}

	public static void recursive(int startX, int startY, int size) {

		if (colorCheck(startX, startY, size)) {
			if (arr[startX][startY] == 1) {
				blue++;
			} else {
				white++;
			}
			return;
		}

		int newSize = size / 2;
		recursive(startX, startY, newSize);
		recursive(startX + newSize, startY, newSize);
		recursive(startX, startY + newSize, newSize);
		recursive(startX + newSize, startY + newSize, newSize);

	}

	public static boolean colorCheck(int row, int col, int size) {

		int color = arr[row][col];    // 첫 번째 원소를 기준으로 검사

		for (int i = row; i < row + size; i++) {
			for (int j = col; j < col + size; j++) {
				// 색상이 같이 않다면 false를 리턴
				if (arr[i][j] != color) {
					return false;
				}
			}
		}
		// 검사가 모두 통과했다는 의미이므로 true 리턴
		return true;
	}

}

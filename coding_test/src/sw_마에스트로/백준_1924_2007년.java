package sw_마에스트로;

import java.util.Scanner;

public class 백준_1924_2007년 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int x = sc.nextInt();
		int y = sc.nextInt();

		int a = 31 % 7;
		int b = 30 % 7;
		int c = 28 % 7;
		int[] month = {0, a, c, a, b, a, b, a, a, b, a, b, a};
		String[] days = {"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"};

		int index = 1;
		for (int i = 1; i < x; i++) {
			int m = month[i];
			index = (index + m) % 7;
		}

		int i = (index + y) % 7 - 1;
		if (i < 0) {
			i = 6;
		}
		System.out.println(days[i]);
	}

}

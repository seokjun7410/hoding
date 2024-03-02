package sw_마에스트로;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

//10101111
//01111101
//11001110
//00000010
//2
//3 -1
//1 1
public class 백준_14891_톱니바퀴 {

	private static final int RIGHT = 2;
	private static final int LEFT = 6;
	private static final int CLOCK = 1;
	private static final int UNCLOCK = -1;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		LinkedList<Integer>[] topNi = new LinkedList[4];
		for (int i = 0; i < 4; i++) {
			topNi[i] = new LinkedList<>();
		}
		for (int i = 0; i < 4; i++) {
			char[] charArray = br.readLine().toCharArray();
			for (int j = 0; j < 8; j++) {
				topNi[i].add(charArray[j] - '0');
			}
		}

		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int number = Integer.parseInt(st.nextToken());
			int direction = Integer.parseInt(st.nextToken());

			doWork(topNi, number, direction);
		}

		int result = 0;
		for (int i = 0; i < 4; i++) {
			if (topNi[i].get(0) == 1) {
				result += Math.pow(2, i);
			}
		}

		System.out.println(result);
	}

	private static void doWork(LinkedList[] topNi, int number, int direction) {
		int[] movable = new int[4];
		rightSectionDirection(topNi, number - 1, direction, movable);
		leftSectionDirection(topNi, number - 1, direction, movable);
		if (direction == CLOCK) {
			ClockDirection(topNi[number - 1]);
		} else {
			UnClockDirection(topNi[number - 1]);
		}
		prognationDirection(topNi, movable);
	}

	private static void leftSectionDirection(LinkedList[] topNi, int number, int direction,
		int[] movable) {
		for (int i = number; i >= 1; i--) {
			LinkedList leftTobni = topNi[i - 1];
			if (topNi[number].get(LEFT) != leftTobni.get(RIGHT)) {
				direction *= -1;
				movable[i - 1] = direction;
			} else {
				break;
			}
		}
	}

	private static void rightSectionDirection(LinkedList[] topNi, int number, int direction,
		int[] movable) {
		for (int i = number; i < 3; i++) {
			LinkedList rightTobni = topNi[i + 1];
			if (topNi[number].get(RIGHT) != rightTobni.get(LEFT)) {
				direction *= -1;
				movable[i + 1] = direction;
			} else {
				break;
			}
		}
	}

	private static void prognationDirection(LinkedList[] topNi, int[] movable) {
		for (int i = 0; i < 4; i++) {
			int index = movable[i];
			if (index != 0) {
				if (index == CLOCK) {
					ClockDirection(topNi[i]);
				} else {
					UnClockDirection(topNi[i]);
				}
			}
		}
	}

	private static void ClockDirection(LinkedList topbni) {
		topbni.offerFirst(topbni.pollLast());
	}

	private static void UnClockDirection(LinkedList tobni) {
		tobni.offerLast(tobni.pollFirst());
	}
}
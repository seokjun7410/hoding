package inf_회의실;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class inf_회의실_01_29 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		StringTokenizer st;
		Meeting[] input = new Meeting[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			input[i] = new Meeting(s,e);
		}

		Arrays.sort(input);

		int temp = 0;
		int count = 0;
		for (int i = 0; i < N; i++) {
			Meeting meeting = input[i];

			if(temp <= meeting.start) {
				temp = meeting.end;
				count ++;
			}
		}

		System.out.println(count);

	}

	private static class Meeting implements Comparable<Meeting>{
		int start;
		int end;
		public Meeting(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Meeting o) {
			if(this.end == o.end)
				return this.start-o.start;
			return this.end - o.end;
		}
	}
}

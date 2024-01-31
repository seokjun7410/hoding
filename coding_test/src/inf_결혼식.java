import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Time;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class inf_결혼식 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		StringTokenizer st;

		Time[] input = new Time[N];
		int lastTime = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			input[i] = new Time(s, e);
			lastTime = Math.max(lastTime, e);
		}

		int index = 0;
		PriorityQueue<Time> queue = new PriorityQueue(new Comparator<Time>() {
			@Override
			public int compare(Time o1, Time o2) {
				return o1.end - o2.end;
			}
		});
		int count = 0;
		int max = 0;
		Arrays.sort(input);
		for (int i = 0; i <= lastTime; i++) {
			if (!queue.isEmpty()) {
				while (queue.peek().end <= i) {
					count--;
					queue.poll();
					if(queue.size() == 0)
						break;
				}
			}
			if (index < N) {
				while (input[index].start <= i) {
					max = Math.max(++count, max);
					queue.add(input[index]);
					index++;
					if(index >= N)
						break;
				}
			}
		}

		System.out.println(max);
	}

	public static class Time implements Comparable<Time> {

		int start;
		int end;

		public Time(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Time o) {
			return this.start - o.start;
		}
	}
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

public class inf_최대_수입_스케줄 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		PriorityQueue<Work> queue = new PriorityQueue<Work>();

		int lastDeadLine = 0;
		Map<Integer, List<Work>> works = new HashMap<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int pay = Integer.parseInt(st.nextToken());
			int deadLine = Integer.parseInt(st.nextToken());
			if (works.get(deadLine) == null) {
				works.put(deadLine, new ArrayList<>());
			}
			works.get(deadLine).add(new Work(pay, deadLine));
			lastDeadLine = Math.max(deadLine, lastDeadLine);
		}

		int sum = 0;
		for (int i = lastDeadLine; i >= 1; i--) {
			List<Work> work = works.get(i);
			if (!Objects.isNull(work)) {
				queue.addAll(work);
			}

			Work now = queue.poll();
			if (now != null) {
				sum += now.pay;
			}

		}

		System.out.println(sum);
	}

	private static class Work implements Comparable<Work> {

		int pay;
		int deadLine;

		public Work(int pay, int deadLine) {
			this.pay = pay;
			this.deadLine = deadLine;
		}

		@Override
		public int compareTo(Work o) {
			return o.pay - this.pay;
		}
	}
}

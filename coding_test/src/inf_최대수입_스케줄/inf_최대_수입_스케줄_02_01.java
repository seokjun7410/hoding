package inf_최대수입_스케줄;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class inf_최대_수입_스케줄_02_01 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		StringTokenizer st;
		Map<Integer, List<Schedule>> schedules = new HashMap<>();

		int lastDay = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int money = Integer.parseInt(st.nextToken());
			int day = Integer.parseInt(st.nextToken());

			schedules.computeIfAbsent(day, k -> new ArrayList<>());
			schedules.get(day).add(new Schedule(money, day));
			lastDay = Math.max(lastDay, day);
		}

		long sum = 0;
		PriorityQueue<Schedule> queue = new PriorityQueue<>();
		for (int i = lastDay; i > 0; i--) {
			if (schedules.get(i) != null) {
				List<Schedule> scheduleList = schedules.get(i);
				queue.addAll(scheduleList);
			}
			if (!queue.isEmpty()) {
				Schedule poll = queue.poll();
				sum += poll.money;
			}
		}

		System.out.println(sum);

	}

	private static class Schedule implements Comparable<Schedule> {

		int money;
		int day;

		public Schedule(int money, int day) {
			this.money = money;
			this.day = day;
		}

		@Override
		public int compareTo(Schedule o) {
			return o.money - money;
		}
	}
}

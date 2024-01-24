package inf_피자배달거리;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class inf_피자_배달_거리_01_24 {

	static CoordinateWrapper pizzas = new CoordinateWrapper();
	static CoordinateWrapper homes = new CoordinateWrapper();
	static int[][] distance;
	static int[] nearlyPizza;

	static int minDistance = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int value = Integer.parseInt(st.nextToken());
				if (value == 1) {
					homes.add(i, j);
				} else if (value == 2) {
					pizzas.add(i, j);
				}
			}
		}

		distance = new int[homes.coordinates.size()][pizzas.coordinates.size()];
		nearlyPizza = new int[homes.coordinates.size()];
		Arrays.fill(nearlyPizza, Integer.MAX_VALUE);
		for (int home = 0; home < homes.coordinates.size(); home++) {
			for (int pizza = 0; pizza < pizzas.coordinates.size(); pizza++) {
				Coordinate homeCoordinate = homes.coordinates.get(home);
				Coordinate pizzaCoordinate = pizzas.coordinates.get(pizza);
				distance[home][pizza] = calculateDistance(homeCoordinate, pizzaCoordinate);
			}
		}

		DFS(0, Arrays.copyOf(nearlyPizza,nearlyPizza.length), M, 0);

		System.out.println(minDistance);
	}

	private static void DFS(int L, int[] nealry, int end, int selectedCount) {

		if (selectedCount == end) {
			int sum = 0;
			for (int i = 0; i < nealry.length; i++) {
				sum+=nealry[i];
			}
			minDistance = Math.min(minDistance, sum);
			return;
		}
		if(L==pizzas.coordinates.size())
			return;

		int[] neal = Arrays.copyOf(nealry, nearlyPizza.length);
		for (int home = 0; home < homes.coordinates.size(); home++) {
			neal[home] = Math.min(distance[home][L], nealry[home]);
		}

		DFS(L + 1, neal, end, selectedCount + 1);
		DFS(L + 1, nealry, end, selectedCount);
	}

	private static int calculateDistance(Coordinate homeCoordinate, Coordinate pizzaCoordinate) {
		return Math.abs(homeCoordinate.i - pizzaCoordinate.i)
			+ Math.abs(homeCoordinate.j - pizzaCoordinate.j);
	}


	private static class CoordinateWrapper {

		List<Coordinate> coordinates = new ArrayList<>();

		public CoordinateWrapper() {
		}

		public void add(int i, int j) {
			coordinates.add(new Coordinate(i, j));
		}
	}

	private static class Coordinate {

		int i;
		int j;

		public Coordinate(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
}

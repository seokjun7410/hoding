public class 프로그래머스_아이템줍기 {

	static int[][] rectangle = {{1, 1, 8, 4}, {2, 2, 4, 9}, {3, 6, 9, 8}, {6, 3, 7, 7}};
	static int characterX = 9;
	static int characterY = 7;
	static int itemX = 6;
	static int itemY = 1;
//	static int[][] rectangle = {{1, 1, 7, 4}, {3, 2, 5, 5}, {4, 3, 6, 9}, {2, 6, 8, 8}};
//	static int characterX = 1;
//	static int characterY = 3;
//	static int itemX = 7;
//	static int itemY = 8;

	static int[][] board;
	static boolean[][] visited;
	static int[] dx = {1, -1, 0, 0, 1, -1, 1, -1};
	static int[] dy = {0, 0, 1, -1, 1, -1, -1, 1};
	static int MIN = Integer.MAX_VALUE;

	public static void main(String[] args) {
		board = new int[101][101];
		visited = new boolean[101][101];
		for (int i = 0; i < rectangle.length; i++) {
			int x1 = rectangle[i][0] * 2;
			int y1 = rectangle[i][1] * 2;
			int x2 = rectangle[i][2] * 2;
			int y2 = rectangle[i][3] * 2;
			for (int j = y1; j <= y2; j++) {
				board[x1][j] += 1;
				board[x2][j] += 1;
			}
			for (int j = x1 + 1; j <= x2 - 1; j++) {
				board[j][y1] += 1;
				board[j][y2] += 1;
			}
			for (int j = x1 + 1; j < x2; j++) {
				for (int k = y1 + 1; k < y2; k++) {
					board[j][k] += 3;
				}
			}

			for (int j = board.length - 1; j >= 0; j--) {
				for (int k = 0; k < board.length - 1; k++) {
					System.out.print(board[k][j] + "\t");
				}
				System.out.println();
			}
			System.out.println();
			System.out.println();
			System.out.println();

		}

		DFS(characterX * 2, characterY * 2, 0.5);

		System.out.println(MIN);

	}

	private static void DFS(int x, int y, double distance) {
		if (x == itemX * 2 && y == itemY * 2) {
			MIN = Math.min(MIN, ((int) distance));
			return;
		}

		if (visited[x][y]) {
			return;
		}
		visited[x][y] = true;

		for (int i = 0; i < 4; i++) {
			int nextX = x + dx[i];
			int nextY = y + dy[i];
			if (nextX > -1 && nextY > -1 && nextX < 101 && nextY < 101
				&& (board[nextX][nextY] == 1 || board[nextX][nextY] == 2)) {
				if (!visited[nextX][nextY]) {
					DFS(nextX, nextY, distance + 0.5);
				}
			}
		}

	}


}

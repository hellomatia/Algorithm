
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {

	class Point {
		int x;
		int y;
		int cost = 0;
		int key;

		Point(int x, int y, int cost, int key) {
			this.x = x;
			this.y = y;
			this.cost = cost;
			this.key = key;
		}

		@Override
		public String toString() {
			return "Point [x=" + x + ", y=" + y + ", cost=" + cost + ", key=" + key + "]";
		}

	}

	private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	private int count;
	private char[][] map;
	private int N, M;
	private Point start;

	private final char WALL = '#';
	private final char EMPTY = '.';
	private final int[] dirX = { -1, 0, 1, 0 };
	private final int[] dirY = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		new Main().solution();
	}

	public void solution() throws IOException {
		init();
		printResult(calcResult());
	}

	private void init() throws IOException {
		StringTokenizer st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		for (int i = 0; i < N; i++) {
			String rowValue = bf.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = rowValue.charAt(j);
				if (map[i][j] == '0') {
					start = new Point(i, j, 0, 0);
				}
			}
		}
	}

	private int calcResult() throws IOException {
		PriorityQueue<Point> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
		int[][][] visited = new int[100][N][M];

		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < N; j++) {
				Arrays.fill(visited[i][j], Integer.MAX_VALUE);
			}
		}

		pq.offer(start);
		int result = Integer.MAX_VALUE;

		while (!pq.isEmpty()) {
			Point now = pq.poll();

			if (visited[now.key][now.x][now.y] <= now.cost) {
				continue;
			}
			visited[now.key][now.x][now.y] = now.cost;

			if (map[now.x][now.y] == '1') {
				result = Math.min(result, now.cost);
			}

			for (int i = 0; i < 4; i++) {
				int nextX = now.x + dirX[i];
				int nextY = now.y + dirY[i];

				if (isIn(nextX, nextY) && map[nextX][nextY] != WALL) {
					if (isKey(map[nextX][nextY])) {
						int nextKey = checkKey(now.key, map[nextX][nextY]);
						pq.offer(new Point(nextX, nextY, now.cost + 1, nextKey));
					} else if (isDoor(map[nextX][nextY])) {
						if (canOpen(now.key, map[nextX][nextY])) {
							pq.offer(new Point(nextX, nextY, now.cost + 1, now.key));
						}
					} else {
						pq.offer(new Point(nextX, nextY, now.cost + 1, now.key));
					}
				}

			}

		}

		if (result == Integer.MAX_VALUE) {
			return -1;
		}
		return result;
	}

	private boolean isIn(int x, int y) {
		return 0 <= x && x < N && 0 <= y && y < M;
	}

	private boolean isKey(char ch) {
		return ch == 'a' || ch == 'b' || ch == 'c' || ch == 'd' || ch == 'e' || ch == 'f';
	}

	private boolean isDoor(char ch) {
		return ch == 'A' || ch == 'B' || ch == 'C' || ch == 'D' || ch == 'E' || ch == 'F';
	}

	private int checkKey(int now, char key) {
		int intKey = 1 << (key - 'a');
		return now | intKey;
	}

	private boolean canOpen(int now, char door) {
		int intDoor = 1 << (door - 'A');
		return (now & intDoor) > 0;
	}

	private void printResult(int result) throws IOException {
		bw.write(result + "\n");
		bw.flush();
	}
}

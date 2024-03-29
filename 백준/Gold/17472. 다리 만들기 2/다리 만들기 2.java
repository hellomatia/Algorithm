import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	class Point {
		int x;
		int y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "Point [x=" + x + ", y=" + y + "]";
		}

	}

	class Edge implements Comparable<Edge> {
		int from;
		int to;
		int cost;
		boolean vertical;
		Point start;

		Edge(int from, int to, int cost, boolean vertical, Point start) {
			this.from = from;
			this.to = to;
			this.cost = cost;
			this.vertical = vertical;
			this.start = start;
		}

		@Override
		public int compareTo(Edge o) {
			return cost - o.cost;
		}

		@Override
		public String toString() {
			return "Edge [from=" + from + ", to=" + to + ", cost=" + cost + ", vertical=" + vertical + ", start="
					+ start + "]";
		}
	}

	private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	private final int[] dirX = { 0, -1, 0, 1 };
	private final int[] dirY = { -1, 0, 1, 0 };

	private int N, M;
	private int[][] map;
	private int id;
	private int[][] dist;
	private int[] parent;

	public static void main(String[] args) throws IOException {
		new Main().solution();
	}

	private void solution() throws IOException {
		init();
		printResult(calcResult());
		bw.flush();
		bw.close();
	}

	private void init() throws IOException {
		StringTokenizer st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) {
					map[i][j] = -1;
				}
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == -1) {
					initMap(i, j);
				}
			}
		}

//		for (int i = 0; i < N; i++) {
//			System.out.println(Arrays.toString(map[i]));
//		}
	}

	private void initMap(int x, int y) {
		id++;
		Queue<Point> queue = new ArrayDeque<>();
		queue.offer(new Point(x, y));
		map[x][y] = id;

		while (!queue.isEmpty()) {
			Point now = queue.poll();

			for (int i = 0; i < 4; i++) {
				int nextX = now.x + dirX[i];
				int nextY = now.y + dirY[i];
				if (isIn(nextX, nextY) && map[nextX][nextY] == -1) {
					map[nextX][nextY] = id;
					queue.offer(new Point(nextX, nextY));
				}
			}
		}
	}

	private boolean isIn(int x, int y) {
		return 0 <= x && x < N && 0 <= y && y < M;
	}

	private int calcResult() {
		PriorityQueue<Edge> pq = new PriorityQueue<>();

		for (int i = 0; i < N; i++) {
			int from = -1;
			int to = -1;
			int count = 0;
			for (int j = 0; j < M; j++) {
				if (map[i][j] != 0) {
					if (from == -1 && to == -1) {
						from = map[i][j];
						count = 0;
					} else if (map[i][j] != from) {
						to = map[i][j];
						if (count > 1) {
							pq.offer(new Edge(from, to, count, false, new Point(i, j - 1)));
//							System.out.println(from + " " + to + " " + false + " " + i + " " + j + " " + count);
						}
						from = to;
						count = 0;
					} else if (map[i][j] == from) {
						count = 0;
					}
				} else {
					count++;
				}
			}
		}

		for (int i = 0; i < M; i++) {
			int from = -1;
			int to = -1;
			int count = 0;
			for (int j = 0; j < N; j++) {
				if (map[j][i] != 0) {
					if (from == -1 && to == -1) {
						from = map[j][i];
						count = 0;
					} else if (map[j][i] != from) {
						to = map[j][i];
						if (count > 1) {
							pq.offer(new Edge(from, to, count, true, new Point(j - 1, i)));
//							System.out.println(from + " " + to + " " + true + " " + i + " " + j + " " + count);
						}
						from = to;
						count = 0;
					} else if (map[j][i] == from) {
						count = 0;
					}
				} else {
					count++;
				}
			}
		}

		parent = new int[id + 1];
		for (int i = 1; i <= id; i++) {
			parent[i] = i;
		}

		int result = 0;

		while (!pq.isEmpty()) {
			Edge now = pq.poll();
			if (union(now.from, now.to)) {
//				System.out.println(now);
				result += now.cost;
			}
		}

//		for (int i = 0; i < N; i++) {
//			System.out.println(Arrays.toString(map[i]));
//		}

		for (int i = 1; i <= id; i++) {
			if (find(i) != 1) {
				return -1;
			}
		}

		return result;
	}

	private boolean union(int x, int y) {
		x = find(x);
		y = find(y);
		if (x == y)
			return false;
		if (x < y) {
			parent[y] = x;
		} else {
			parent[x] = y;
		}
		return true;
	}

	private int find(int x) {
		if (parent[x] == x) {
			return x;
		}
		return parent[x] = find(parent[x]);
	}

	private void printResult(int result) throws IOException {
		bw.write(result + "\n");
	}
}
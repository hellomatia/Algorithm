import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {

	class Point {
		int x;
		int y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	private int t, n;
	private int[][] dist;
	private Point[] points;

	public static void main(String[] args) throws IOException {
		new Main().solution();
	}

	public void solution() throws IOException {
		t = Integer.parseInt(bf.readLine());
		for (int i = 0; i < t; i++) {
			init();
			printResult(calcResult());
		}
	}

	private void init() throws IOException {
		n = Integer.parseInt(bf.readLine());

		points = new Point[n + 2];
		for (int i = 0; i < n + 2; i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			points[i] = new Point(x, y);
		}

		dist = new int[n + 2][n + 2];
		for (int i = 0; i < n + 2; i++) {
			for (int j = 0; j < n + 2; j++) {
				dist[i][j] = calcDist(points[i], points[j]);
			}
		}

	}

	private int calcDist(Point start, Point end) {
		return Math.abs(start.x - end.x) + Math.abs(start.y - end.y);
	}

	private boolean calcResult() throws IOException {

		if (dist[0][n + 1] <= 1000) {
			return true;
		}

		HashSet<Integer> visited = new HashSet<>();
		Queue<Integer> canGo = new ArrayDeque<>();
		for (int i = 1; i <= n; i++) {
			if (dist[0][i] <= 1000) {
				canGo.offer(i);
				visited.add(i);
			}
		}

		int size = canGo.size();
		int prevSize = 0;
		while (size != prevSize) {
			for (int i = 0; i < size; i++) {
				int start = canGo.poll();
				for (int end = 1; end <= n; end++) {
					if (visited.contains(end))
						continue;
					if (dist[start][end] <= 1000) {
						canGo.offer(end);
						visited.add(end);
					}
				}
				canGo.offer(start);
			}
			prevSize = size;
			size = canGo.size();
		}

		while (!canGo.isEmpty()) {
			int start = canGo.poll();
			if (dist[start][n + 1] <= 1000) {
				return true;
			}
		}

		return false;
	}

	private void printResult(boolean result) throws IOException {
		if (result) {
			bw.write("happy\n");
		} else {
			bw.write("sad\n");
		}
		bw.flush();
	}
}
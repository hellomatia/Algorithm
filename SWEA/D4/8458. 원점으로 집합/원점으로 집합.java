import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Solution {

	class Point {
		int x;
		int y;

		Point(Point point) {
			x = point.x;
			y = point.y;
		}

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public Point clone() {
			return new Point(this);
		}
	}

	private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	private int N;
	private Point target = new Point(0, 0);
	private int[] dist;
	private int max;
	private Point[] points;

	public static void main(String[] args) throws IOException {
		new Solution().solution();
	}

	public void solution() throws IOException {
		int testCase = Integer.parseInt(bf.readLine());
		for (int i = 1; i <= testCase; i++) {
			init();
			printResult(i, calcResult());
		}
	}

	private void init() throws IOException {
		N = Integer.parseInt(bf.readLine());

		points = new Point[N];
		dist = new int[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			points[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			dist[i] = calcDist(target, points[i]);
		}

		max = Integer.MIN_VALUE;
	}

	private int calcResult() {
		for (int i = 0; i < N; i++) {
			if (i >= 1 && dist[i] % 2 != dist[i - 1] % 2) {
				max = -1;
				break;
			} else {
				max = Math.max(dist[i], max);
			}
		}
		int index = 0;

		if (max != -1) {
			long sum = 0;
			while (true) {
				sum += index;
				if (sum >= max && (sum - max) % 2 == 0) {
					break;
				}
				index++;
			}
		} else {
			index = -1;
		}

		return index;
	}

	private int calcDist(Point o1, Point o2) {
		return Math.abs(o1.x - o2.x) + Math.abs(o1.y - o2.y);
	}

	private void printResult(int testCase, int result) throws IOException {
		bw.write("#" + testCase + " ");
		bw.write(result + "\n");
		bw.flush();
	}
}
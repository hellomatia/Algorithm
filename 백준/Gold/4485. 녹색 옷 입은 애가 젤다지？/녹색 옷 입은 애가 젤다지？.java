import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {

	class Point implements Comparable<Point> {
		int x;
		int y;
		int cost;

		Point(int x, int y, int cost) {
			this.x = x;
			this.y = y;
			this.cost = cost;
		}

		@Override
		public int compareTo(Point o) {
			return cost - o.cost;
		}

	}

	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws IOException {
		new Main().solution();
	}

	public void solution() throws IOException {

		int[] dirX = { 0, -1, 0, 1 };
		int[] dirY = { -1, 0, 1, 0 };

		int N = Integer.parseInt(br.readLine());
		int testCase = 1;
		while (N != 0) {

			// 맵 초기화
			int[][] map = new int[N][N];
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			// 거리 초기화
			int[][] dist = new int[N][N];
			for (int i = 0; i < N; i++) {
				Arrays.fill(dist[i], Integer.MAX_VALUE);
			}

			// 다익스트라 실행
			PriorityQueue<Point> pq = new PriorityQueue<>();
			pq.offer(new Point(0, 0, map[0][0]));

			while (!pq.isEmpty()) {
				Point now = pq.poll();

				if (dist[now.x][now.y] <= now.cost)
					continue;
				dist[now.x][now.y] = now.cost;

				for (int i = 0; i < 4; i++) {
					int nextX = now.x + dirX[i];
					int nextY = now.y + dirY[i];

					if (0 <= nextX && nextX < N && 0 <= nextY && nextY < N) {
						pq.offer(new Point(nextX, nextY, now.cost + map[nextX][nextY]));
					}
				}
			}

			// 결과 출력
			bw.write("Problem " + testCase + ": ");
			bw.write(dist[N - 1][N - 1] + "\n");

			bw.flush();
			N = Integer.parseInt(br.readLine());
			testCase++;
		}
		bw.close();
	}
}
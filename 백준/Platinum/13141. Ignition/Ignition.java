import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	private int N, M;
	private int[][] longDist;
	private int[][] shortDist;

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

		longDist = new int[N + 1][N + 1];
		shortDist = new int[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			Arrays.fill(shortDist[i], Integer.MAX_VALUE);
			shortDist[i][i] = 0;
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(bf.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			longDist[start][end] = Math.max(longDist[start][end], cost);
			shortDist[start][end] = Math.min(shortDist[start][end], cost);

			longDist[end][start] = longDist[start][end];
			shortDist[end][start] = shortDist[start][end];
		}
	}

	private double calcResult() throws IOException {

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				for (int k = 1; k <= N; k++) {
					if (shortDist[j][i] == Integer.MAX_VALUE || shortDist[i][k] == Integer.MAX_VALUE) {
						continue;
					}
					shortDist[j][k] = Math.min(shortDist[j][i] + shortDist[i][k], shortDist[j][k]);
				}
			}
		}

		double minCost = Integer.MAX_VALUE;
		for (int i = 1; i <= N; i++) {
			double maxCost = 0;
			for (int from = 1; from <= N; from++) {
				for (int to = 1; to <= N; to++) {

					if (longDist[from][to] == 0)
						continue;

					double remain = longDist[from][to] - (shortDist[i][to] - shortDist[i][from]);

					if (remain <= 0)
						continue;

					double cost = (remain / 2) + shortDist[i][to];
					maxCost = Math.max(cost, maxCost);
				}
			}
			minCost = Math.min(maxCost, minCost);
		}

		return minCost;
	}

	private void printResult(double result) throws IOException {
		System.out.printf("%.1f", result);
	}
}
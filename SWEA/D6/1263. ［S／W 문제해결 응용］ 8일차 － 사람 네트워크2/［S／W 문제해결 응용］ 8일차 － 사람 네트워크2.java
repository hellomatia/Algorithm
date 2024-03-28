import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

	private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	private int N;
	private int[][] dist;
	private final int INF = 987654321;

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
		StringTokenizer st = new StringTokenizer(bf.readLine());

		N = Integer.parseInt(st.nextToken());
		dist = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			Arrays.fill(dist[i], INF);
			dist[i][i] = 0;
		}

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				int cost = Integer.parseInt(st.nextToken());
				if (cost == 1) {
					dist[i][j] = 1;
				}
			}
		}

	}

	private int calcResult() {
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					dist[i][j] = Math.min(dist[i][k] + dist[k][j], dist[i][j]);
				}
			}
		}

		int result = Integer.MAX_VALUE;
		for (int i = 1; i <= N; i++) {
			int sum = 0;
			for (int j = 1; j <= N; j++) {
				sum += dist[i][j];
			}
			result = Math.min(result, sum);
		}

		return result;
	}

	private void printResult(int testCase, int result) throws IOException {
		bw.write("#" + testCase + " ");
		bw.write(result + "\n");
		bw.flush();
	}
}
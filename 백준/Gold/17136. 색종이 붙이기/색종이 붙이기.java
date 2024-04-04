import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	private final BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	private final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	private int result;
	private int[][] paper;
	private int oneCount;
	private int[] confettiCount;

	public static void main(String[] args) throws IOException {
		new Main().solution();
	}

	private void solution() throws IOException {
		init();
		calcResult();
		printResult();
	}

	private void init() throws IOException {
		paper = new int[10][10];
		for (int i = 0; i < 10; i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			for (int j = 0; j < 10; j++) {
				paper[i][j] = Integer.parseInt(st.nextToken());
				if (paper[i][j] == 1) {
					oneCount++;
				}
			}
		}
		confettiCount = new int[6];
	}

	private void calcResult() {
		result = Integer.MAX_VALUE;
		calcDFS(0, 0, paper, oneCount, 0);
	}

	private void calcDFS(int x, int y, int[][] map, int remain, int count) {
		if (result <= count) {
			return;
		}

		if (remain == 0) {
			result = Math.min(result, count);
			return;
		}

		if (y == 10) {
			x++;
			y = 0;
		}

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (map[i][j] == 1) {
					for (int k = 5; k >= 1; k--) {
						if (confettiCount[k] == 5)
							continue;
						if (canPostIt(i, j, map, k)) {
							confettiCount[k]++;
							int[][] nextMap = copy(map);
							cheackMap(i, j, nextMap, k);
							calcDFS(i, j + 1, nextMap, remain - k * k, count + 1);
							confettiCount[k]--;
						}
					}
					return;
				}
			}
		}
	}

	private boolean canPostIt(int x, int y, int[][] map, int size) {
		for (int i = x; i < x + size; i++) {
			for (int j = y; j < y + size; j++) {
				if (!isIn(i, j) || map[i][j] != 1)
					return false;
				if (map[i][j] != 1)
					return false;
			}
		}
		return true;
	}

	private boolean isIn(int x, int y) {
		return 0 <= x && x < 10 && 0 <= y && y < 10;
	}

	private int[][] copy(int[][] map) {
		int[][] temp = new int[10][10];
		for (int i = 0; i < 10; i++) {
			temp[i] = map[i].clone();
		}
		return temp;
	}

	private void cheackMap(int x, int y, int[][] map, int size) {
		for (int i = x; i < x + size; i++) {
			for (int j = y; j < y + size; j++) {
				map[i][j] = 0;
			}
		}
	}

	private void printResult() throws IOException {
		if (result == Integer.MAX_VALUE) {
			bw.write("-1\n");
		} else {
			bw.write(result + "\n");
		}
		bw.flush();
	}
}
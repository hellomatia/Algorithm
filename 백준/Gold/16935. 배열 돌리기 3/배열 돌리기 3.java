import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	
	private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	private int N, M, R;
	private int[][] map;
	
	public static void main(String[] args) throws IOException {
		new Main().solution();
	}
	
	private void solution() throws IOException {
		init();
		calcResult();
		printResult();
		bw.flush();
		bw.close();
	}
	
	// 배열 초기화
	private void init() throws IOException {
		StringTokenizer st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}
	
	// 결과 계산
	private void calcResult() throws IOException {
		StringTokenizer st = new StringTokenizer(bf.readLine());
		for (int i = 0; i < R; i++) {
			int command = Integer.parseInt(st.nextToken());
			if (command == 1) {
				rotateMap1();
			} else if (command == 2) {
				rotateMap2();
			} else if (command == 3) {
				rotateMap3();
			} else if (command == 4) {
				rotateMap4();
			} else if (command == 5) {
				rotateMap5();
			} else if (command == 6) {
				rotateMap6();
			}
		}
	}
	
	private void rotateMap1() {
		int[][] newMap = new int[map.length][map[0].length];
		for (int r = map.length - 1; r >= 0; r--) {
			for (int c = 0; c < map[0].length; c++) {
				newMap[map.length - 1 - r][c] = map[r][c];
			}
		}
		map = newMap;
	}

	private void rotateMap2() {
		int[][] newMap = new int[map.length][map[0].length];
		for (int r = 0; r < map.length; r++) {
			for (int c = map[0].length - 1; c >= 0; c--) {
				newMap[r][map[0].length - 1 - c] = map[r][c];
			}
		}
		map = newMap;
	}

	private void rotateMap3() {
		int[][] newMap = new int[map[0].length][map.length];
		for (int c = 0; c < map[0].length; c++) {
			for (int r = map.length - 1; r >= 0; r--) {
				newMap[c][map.length - 1 - r] = map[r][c];
			}
		}
		map = newMap;
	}

	private void rotateMap4() {
		int[][] newMap = new int[map[0].length][map.length];
		for (int c = map[0].length - 1; c >= 0; c--) {
			for (int r = 0; r < map.length; r++) {
				newMap[map[0].length - 1 - c][r] = map[r][c];
			}
		}
		map = newMap;
	}

	private void rotateMap5() {
		int[][] newMap = new int[map.length][map[0].length];
		// 1 -> 2
		for (int r = 0; r < map.length / 2; r++) {
			for (int c = 0; c < map[0].length / 2; c++) {
				newMap[r][map[0].length / 2 + c] = map[r][c];
			}
		}
		// 2 -> 3
		for (int r = 0; r < map.length / 2; r++) {
			for (int c = map[0].length / 2; c < map[0].length; c++) {
				newMap[map.length / 2 + r][c] = map[r][c];
			}
		}
		// 3 -> 4
		for (int r = map.length / 2; r < map.length; r++) {
			for (int c = map[0].length / 2; c < map[0].length; c++) {
				newMap[r][c - map[0].length / 2] = map[r][c];
			}
		}
		// 4 -> 1
		for (int r = map.length / 2; r < map.length; r++) {
			for (int c = 0; c < map[0].length / 2; c++) {
				newMap[r - map.length / 2][c] = map[r][c];
			}
		}
		map = newMap;
	}
	
	private void rotateMap6() {
		int[][] newMap = new int[map.length][map[0].length];
		// 1 -> 4
		for (int r = 0; r < map.length / 2; r++) {
			for (int c = 0; c < map[0].length / 2; c++) {
				newMap[r + map.length / 2][c] = map[r][c];
			}
		}
		// 2 -> 1
		for (int r = 0; r < map.length / 2; r++) {
			for (int c = map[0].length / 2; c < map[0].length; c++) {
				newMap[r][c - map[0].length / 2] = map[r][c];
			}
		}
		// 3 -> 2
		for (int r = map.length / 2; r < map.length; r++) {
			for (int c = map[0].length / 2; c < map[0].length; c++) {
				newMap[r - map.length / 2][c] = map[r][c];
			}
		}
		// 4 -> 3
		for (int r = map.length / 2; r < map.length; r++) {
			for (int c = 0; c < map[0].length / 2; c++) {
				newMap[r][c + map[0].length / 2] = map[r][c];
			}
		}
		map = newMap;
	}

	private void printResult() throws IOException {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				bw.write(map[i][j] + " ");
			}
			bw.write("\n");
		}	
	}
}


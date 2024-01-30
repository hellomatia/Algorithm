import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {
	
	private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	private static final int LINE = 1;
	
	private int[][] map;
	private boolean[][] visited;
	private int target;
	
	public static void main(String[] args) throws IOException {
		new Solution().solution();
	}
	
	private void solution() throws IOException {
		int T = 10;
		for (int testCase = 1; testCase <= T; testCase++) {
			init();
			printResult(testCase, calcResult());
		}
		bw.flush();
		bw.close();
	}
	
	private void init() throws IOException {
		bf.readLine();
		map = new int[100][100];
		
		for (int i = 0; i < 99; i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			for (int j = 0; j < 100; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		StringTokenizer st = new StringTokenizer(bf.readLine());
		for (int j = 0; j < 100; j++) {
			map[99][j] = Integer.parseInt(st.nextToken());
			if (map[99][j] == 2) {
				target = j;
			}
		}
		
	}
	
	private int calcResult() {
		int nowX = 99;
		int nowY = target;
		visited = new boolean[100][100];
		while (nowX != 0) {
			visited[nowX][nowY] = true;
			if (canMoveLeft(nowX, nowY)) {
				nowY--;
				continue;
			} else if (canMoveRight(nowX, nowY)) {
				nowY++;
				continue;
			}
			nowX--;
		}
		
		return nowY;
	}
	
	private boolean canMoveLeft(int x, int y) {
		return 0 <= y - 1 && map[x][y - 1] == LINE && !visited[x][y - 1];
	}
	
	private boolean canMoveRight(int x, int y) {
		return y + 1 < 100 && map[x][y + 1] == LINE && !visited[x][y + 1];
	}
	
	private void printResult(int testCase, int result) throws IOException {
		bw.write("#" + testCase + " " + result + "\n");
	}
}

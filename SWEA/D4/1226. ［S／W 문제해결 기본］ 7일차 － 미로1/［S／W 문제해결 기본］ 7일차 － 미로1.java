
import java.io.*;
import java.util.*;

class Point {
	int x;
	int y;
	
	Point (int x, int y) {
		this.x = x;
		this.y = y;
	}
	
}

public class Solution {

	private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static int T;
	private static int[][] map;
	private static boolean[][] visited;
	private static Point start;
	private static int[] dirX = {0, 0, -1, 1};
	private static int[] dirY = {-1, 1, 0, 0};
	
	public static void main(String args[]) throws Exception {
		solution();
	}

	private static void solution() throws IOException {
		T = 10;
		for (int testCase = 1; testCase <= T; testCase++) {
			initMap();
			printResult(testCase, canSolve());
			bw.flush();
		}
		bw.close();
	}

	private static void initT() throws IOException {
		T = Integer.parseInt(bf.readLine());
	}

	private static void initMap() throws IOException {
		bf.readLine();
		map = new int[16][16];
		for (int i = 0; i < 16; i++) {
			String string = bf.readLine();
			for (int j = 0; j < 16; j++) {
				map[i][j] = string.charAt(j) - '0';
				if (map[i][j] == 2) {
					start = new Point(i, j);
				}
			}
		}
	}
	
	private static int canSolve() {
		Queue<Point> queue = new LinkedList<>();
		queue.offer(start);
		visited = new boolean[16][16];
		visited[start.x][start.y] = true;
		
		while (!queue.isEmpty()) {
			Point now = queue.poll();
			if (map[now.x][now.y] == 3) {
				return 1;
			}
			for (int i = 0; i < 4; i++) {
				int nextX = now.x + dirX[i];
				int nextY = now.y + dirY[i];
				if (canMove(nextX, nextY)) {
					visited[nextX][nextY] = true;
					queue.offer(new Point(nextX, nextY));
				}
			}
		}	
		return 0;
	}
	
	private static boolean canMove(int x, int y) {
		if (x < 0 || y < 0 || 16 <= x || 16 <= y || visited[x][y]) {
			return false;
		}
		return map[x][y] != 1;
	}

	private static void printResult(int testCase, int result) throws IOException {
		bw.write("#" + testCase + " " + result);
		bw.write("\n");
	}
}

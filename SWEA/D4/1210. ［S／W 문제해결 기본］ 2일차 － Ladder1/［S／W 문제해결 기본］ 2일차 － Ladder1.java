
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
	private static Queue<Point> starts;
	private static int result;

	public static void main(String args[]) throws Exception {
		solution();
	}

	private static void solution() throws IOException {
		T = 10;
		for (int testCase = 1; testCase <= T; testCase++) {
			initMap();
			while (!starts.isEmpty()) {
				Point start = starts.poll();
				if (isResult(start)) {
					result = start.y;
				}
			}
			printResult(testCase);
			bw.flush();
		}
		bw.close();
	}

	private static void initT() throws IOException {
		T = Integer.parseInt(bf.readLine());
	}

	private static void initMap() throws IOException {
		bf.readLine();
		map = new int[100][100];
		starts = new LinkedList<>();

		for (int i = 0; i < 100; i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			for (int j = 0; j < 100; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (i == 0 && map[0][j] == 1) {
					starts.offer(new Point (0, j));
				}
			}
		}
	}
	
	private static boolean isResult(Point start) {
		int nextX = start.x;
		int nextY = start.y;
		
		visited = new boolean[100][100];
		
		while (nextX < 100) {
			visited[nextX][nextY] = true;
			if (map[nextX][nextY] == 2) {
				return true;
			}
			if (canMove(nextX, nextY + 1)) {
				nextY += 1;
				continue;
			} else if (canMove(nextX, nextY - 1)) {
				nextY -= 1;
				continue;
			}
			nextX += 1;
		}
		
		return false;
	}
	
	private static boolean canMove(int x, int y) {
		if (x < 0 || y <0 || 100 <= x || 100 <= y || visited[x][y]) {
			return false;
		}
		return map[x][y] != 0;
	}

	private static void printResult(int testCase) throws IOException {
		bw.write("#" + testCase + " " + result);
		bw.write("\n");
	}
}

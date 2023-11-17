
import java.io.*;
import java.util.*;

class Point {
	int x;
	int y;
	
	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Solution {
	
	private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static int T, N, M;
	private static char[][] map;
	private static int[] dirX = {0, 0, -1, 1, -1, -1, 1, 1};
	private static int[] dirY = {-1, 1, 0, 0, -1, 1, -1, 1};
	
	
	public static void main(String args[]) throws Exception {
		solution();
	}
	
	private static void solution() throws IOException {
		initT();
		for (int testCase = 1; testCase <= T; testCase++) {
			initN();
			initMap();
			if (hasFive()) {
				printResult(testCase, "YES");
			} else {
				printResult(testCase, "NO");
			}
		}
		bw.flush();
		bw.close();
	}
	
	private static void initT() throws IOException {
		T = Integer.parseInt(bf.readLine());
	}
	
	private static void initN() throws IOException {
		N = Integer.parseInt(bf.readLine());
	}
	
	private static void initMap() throws IOException {
		map = new char[N][N];
		
		for (int i = 0; i < N; i++) {
			String string = bf.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = string.charAt(j);
			}
		}
	}
	
	private static boolean hasFive() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 'o' && findFive(i, j)) {
					return true;
				}
			}
		}
		return false;
	}
	
	private static boolean findFive(int x, int y) {
		int count;
		
		for (int i = 0; i < 8; i++) {
			int nextX = x + dirX[i];
			int nextY = y + dirY[i];
			count = 1;
			while (0 <= nextX && 0 <= nextY && nextX < N && nextY < N && map[nextX][nextY] == 'o') {
				count++;
				nextX += dirX[i];
				nextY += dirY[i];
			}
			
			if (count >= 5) {
				return true;
			}
			
		}
		return false;
	}
	
	
	private static void printResult(int testCase, String result) throws IOException {
		bw.write("#"+testCase + " " +result);
		bw.write("\n");
	}
}

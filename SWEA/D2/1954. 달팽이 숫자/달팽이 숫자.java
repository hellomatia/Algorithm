
import java.io.*;
import java.util.*;

public class Solution {
	
	private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static int T, N;
	private static int[][] map;
	private static int[] dirX = {0, 1, 0, -1};
	private static int[] dirY = {1, 0, -1, 0};
	
	public static void main(String args[]) throws Exception {
		solution();
	}
	
	private static void solution() throws IOException {
		initT();
		for (int testCase = 1; testCase <= T; testCase++) {
			initN();
			
			makeSnailMap(0, 0, 0, 2);
			printResult(testCase, map);
		}
		bw.flush();
		bw.close();
	}
	
	private static void initT() throws IOException {
		T = Integer.parseInt(bf.readLine());
	}

	private static void initN() throws IOException {
		N = Integer.parseInt(bf.readLine());
		map = new int[N][N];
		map[0][0] = 1;
	}
	
	private static void makeSnailMap(int x, int y, int dir, int count) {
		if (count > N * N) {
			return;
		}
		
		while (true) {
			int nextX = x + dirX[dir];
			int nextY = y + dirY[dir];
			
			if (nextX < 0 || nextY < 0 || N <= nextX || N <= nextY || map[nextX][nextY] != 0) {
				dir++;
				dir %= 4;
				makeSnailMap(x, y, dir, count);
				break;
			}
			
			map[nextX][nextY] = count;
			count++;
			x = nextX;
			y = nextY;
		}
	}
	
	
	private static void printResult(int testCase, int[][] map) throws IOException {
		bw.write("#"+testCase + "\n");
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				bw.write(map[i][j] + " ");
			}
			bw.write("\n");
		}
	}
}

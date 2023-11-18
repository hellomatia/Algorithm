
import java.io.*;
import java.util.*;

public class Solution {

	private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static int T;
	private static int[][] map;
	private static Set<String> set;
	private static int dirX[] = {0, 0, -1, 1};
	private static int dirY[] = {-1, 1, 0, 0};

	public static void main(String args[]) throws Exception {
		solution();
	}

	private static void solution() throws IOException {
		initT();
		for (int testCase = 1; testCase <= T; testCase++) {
			initMap();
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					findCount(i, j, new StringBuilder(map[i][j]), 0);
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
		map = new int[4][4];
		set = new HashSet<>();
		for (int i = 0; i < 4; i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			for (int j = 0; j < 4; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}
	
	private static void findCount(int x, int y, StringBuilder num, int count) {
		if (count == 7) {
			set.add(num.toString());
			return;
		}
		for (int i = 0; i < 4; i++) {
			int nextX = x + dirX[i];
			int nextY = y + dirY[i];
			if (nextX < 0 || nextY < 0 || 4 <= nextX || 4 <= nextY) {
				continue;
			}
			findCount(nextX, nextY, new StringBuilder(num).append(map[nextX][nextY]), count + 1);
		}
		
	}

	private static void printResult(int testCase) throws IOException {
		bw.write("#" + testCase + " " + set.size());
		bw.write("\n");
	}
}

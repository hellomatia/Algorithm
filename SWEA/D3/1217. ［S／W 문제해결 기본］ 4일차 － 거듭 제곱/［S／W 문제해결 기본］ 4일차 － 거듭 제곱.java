
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
		T = 10;
		for (int testCase = 1; testCase <= T; testCase++) {
			initNandM();
			printResult(testCase, pow(M));
		}
		bw.flush();
		bw.close();
	}
	
	private static void initT() throws IOException {
		T = Integer.parseInt(bf.readLine());
	}
	
	private static void initNandM() throws IOException {
		bf.readLine();
		StringTokenizer st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
	}
	
	private static int pow(int count) {
		if (count == 0) {
			return 1;
		}
		return N * pow(count - 1);
	}
	
	private static void printResult(int testCase, int result) throws IOException {
		bw.write("#"+testCase + " " +result);
		bw.write("\n");
	}
}


import java.io.*;
import java.util.*;

public class Solution {
	
	private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static int T, N;
	private static int[][] map;
	
	public static void main(String args[]) throws Exception {
		solution();
	}
	
	private static void solution() throws IOException {
		T = 10;
		for (int testCase = 1; testCase <= T; testCase++) {
			initMap();
			printResult(testCase, findCount());
		}
		bw.flush();
		bw.close();
	}
	
	private static void initMap() throws IOException {
		N = Integer.parseInt(bf.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}
	
	private static int findCount() {
		//deleteNandS();
		int count = 0;
		for (int col = 0; col < N; col++) {
			int pole = 0;
			for (int row = 0; row < N; row++) {
				if (map[row][col] == 1) {
					pole = map[row][col];
				}
				else if (pole != 0 && map[row][col] != 0 && pole != map[row][col]) {
					count++;
					pole = 0;
				}
			}
		}
		return count;
	}
	
	private static void deleteNandS() {
		for (int col = 0; col < N; col++) {
			for (int row = 0; row < N; row++) {
				if (map[row][col] == 2) {
					break;
				}
				if (map[row][col] == 1) {
					map[row][col] = 0;
				}
			}
			for (int row = N - 1; row >= 0; row--) {
				if (map[row][col] == 1) {
					break;
				}
				if (map[row][col] == 2) {
					map[row][col] = 0;
				}
			}
		}
		
	}

	private static void printResult(int testCase, int result) throws IOException {
		bw.write("#"+testCase + " " + result +"\n");
	}
}

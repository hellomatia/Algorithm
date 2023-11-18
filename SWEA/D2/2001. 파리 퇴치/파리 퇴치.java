
import java.io.*;
import java.util.*;

public class Solution {
	private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	private static int T, N, M;
	private static int[][] map;
	
	public static void main(String[] args) throws IOException {
		initT();
		for (int testCase = 1; testCase <= T; testCase++) {
			initNandM();
			initMap();
			printResult(testCase, findMax());
			bw.flush();
		}
		bw.close();
	}
	
	private static void initT() throws IOException {
		T = Integer.parseInt(bf.readLine());
	}
	
	private static void initNandM() throws IOException {
		StringTokenizer st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
	}
	
	
	private static void initMap() throws IOException {
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			for (int j = 0 ; j< N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}
	
	private static int findMax() {
		int max = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				max = Math.max(max, calculateSum(i, j));
			}
		}
		
		return max;
	}
	
	private static int calculateSum(int x, int y) {
		if (x + M > N || y + M > N) {
			return 0;
		}
		
		int sum = 0;
		
		for (int i = x; i < x + M; i++) {
			for (int j = y; j < y + M; j++) {
				sum += map[i][j];
			}
		}
		
		return sum;
	}
	
	private static void printResult(int testCase, int result) throws IOException {
		bw.write("#" + testCase + " " + result);
		bw.write("\n");
	}
	
	
}

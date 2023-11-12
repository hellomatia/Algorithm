
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
		initT();
		for (int testCase = 1; testCase <= T; testCase++) {
			initN();
			initMap();
			printResult(testCase, findResult());
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
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			String string = bf.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = string.charAt(j) - '0';
			}
		}
	}
	
	private static int findResult() throws IOException {
		int startCol = 0;
		int startRow = N/2;
		int result = 0;
		int index = 0;
		while (index <= N/2) {
			for (int col = startCol; col < N - startCol; col++) {
				result += map[startRow + index][col];
				result += map[startRow - index][col];
			}
			startCol++;
			index++;
		}
		
		for (int col = 0; col < N; col++) {
			result -= map[N / 2][col];
		}
		
		return result;
	}
	
	
	private static void printResult(int testCase, int result) throws IOException {
		bw.write("#"+testCase + " " + result +"\n");
		
	}
}

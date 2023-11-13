
import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Solution {
	
	private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static int T, N;
	private static char[][] map;
	
	public static void main(String args[]) throws Exception {
		solution();
	}
	
	private static void solution() throws IOException {
		T = 10;
		for (int testCase = 1; testCase <= T; testCase++) {
			initN();
			initMap();
			printResult(testCase, findPalindrome());
		}
		bw.flush();
		bw.close();
	}
	
	private static void initN() throws IOException {
		N = Integer.parseInt(bf.readLine());
	}
	
	private static void initMap() throws IOException {
		map = new char[8][8];
		for (int i = 0; i < 8; i ++) {
			String string = bf.readLine();
			for (int j = 0; j <8; j++) {
				map[i][j] = string.charAt(j);
			}
		}
	}
	
	private static int findPalindrome() {
		int count = 0;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (isRowPalidrome(i, j)) {
					count++;
				}
			}
		}
		
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (isColPalidrome(i, j)) {
					count++;
				}
			}
		}
		
		return count;
	}
	
	private static boolean isRowPalidrome(int row, int start) {
		if (start + N > 8) {
			return false;
		}
		int forward = start;
		int backward = start + N - 1;
		
		while (forward <= backward) {
			if (map[row][forward] != map[row][backward]) {
				return false;
			}
			forward++;
			backward--;
		}
		return true;
	}
	
	private static boolean isColPalidrome(int col, int start) {
		if (start + N > 8) {
			return false;
		}
		int forward = start;
		int backward = start + N - 1;
		
		while (forward <= backward) {
			if (map[forward][col] != map[backward][col]) {
				return false;
			}
			forward++;
			backward--;
		}
		return true;
	}

	private static void printResult(int testCase, int result) throws IOException {
		bw.write("#"+testCase + " " + result +"\n");
	}
}

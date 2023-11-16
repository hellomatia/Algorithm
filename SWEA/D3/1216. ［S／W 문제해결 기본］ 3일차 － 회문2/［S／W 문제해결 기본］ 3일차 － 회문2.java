
import java.io.*;
import java.util.*;

public class Solution {
	
	private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static int T, N, K;
	private static int maxLength;
	private static char[][] map;

	public static void main(String args[]) throws Exception {
		solution();
	}
	
	private static void solution() throws IOException {
		T = 10;
		for (int testCase = 1; testCase <= T; testCase++) {
			initTestCase();
			initMap();
			findMaxLength();
			printResult(testCase, maxLength);
		}
		bw.flush();
		bw.close();
	}
	
	private static void initT() throws IOException {
		T = Integer.parseInt(bf.readLine());
	}
	
	private static void initTestCase() throws IOException {
		N = Integer.parseInt(bf.readLine());
	}
	
	private static void initMap() throws IOException {
		map = new char[100][100];
		for (int i = 0; i < 100; i++) {
			String string = bf.readLine();
			for (int j = 0; j < 100; j++) {
				map[i][j] = string.charAt(j);
			}
		}
	}
	
	private static void findMaxLength() {
		maxLength = 1;
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				for (int k = maxLength + 1; k <= 100; k++) {
					if (isRowPalindrome(i, j, k) || isColPalindrome(i, j, k)) {
						maxLength = k;
					}
				}
			}
		}
	}
	
	private static boolean isRowPalindrome(int x, int y, int length) {
		if (y + length > 100) {
			return false;
		}
		int forward = y;
		int backward = y + length - 1;
		
		while (forward < backward) {
			if (map[x][forward] != map[x][backward]) {
				return false;
			}
			forward++;
			backward--;
		}
		return true;
	}
	
	private static boolean isColPalindrome(int x, int y, int length) {
		if (x + length > 100) {
			return false;
		}
		int forward = x;
		int backward = x + length - 1;
		
		while (forward < backward) {
			if (map[forward][y] != map[backward][y]) {
				return false;
			}
			forward++;
			backward--;
		}
		return true;
	}

	private static void printResult(int testCase, int result) throws IOException {
		bw.write("#"+testCase + " " + result);
		bw.write("\n");
	}
}

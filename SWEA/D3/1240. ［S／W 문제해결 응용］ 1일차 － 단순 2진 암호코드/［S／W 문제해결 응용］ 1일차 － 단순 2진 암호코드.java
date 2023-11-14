
import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Solution {
	
	private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static int T, N, M;
	private static int[][] map;
	private static int[] bacode;
	private static int[] code;
	private static String ZERO = "0001101";
	private static String ONE = "0011001";
	private static String TWO = "0010011";
	private static String THREE = "0111101";
	private static String FOUR = "0100011";
	private static String FIVE = "0110001";
	private static String SIX = "0101111";
	private static String SEVEN = "0111011";
	private static String EIGHT = "0110111";
	private static String NINE = "0001011";
	
	public static void main(String args[]) throws Exception {
		solution();
	}
	
	private static void solution() throws IOException {
		initT();
		for (int testCase = 1; testCase <= T; testCase++) {
			initNAndM();
			initMap();
			findBacode();
			findCode();
			printResult(testCase, calculateCode());
		}
		bw.flush();
		bw.close();
	}
	
	private static void initT() throws IOException {
		T = Integer.parseInt(bf.readLine());
	}
	
	private static void initNAndM() throws IOException {
		StringTokenizer st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
	}
	
	private static void initMap() throws IOException {
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			String string = bf.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = string.charAt(j) - '0';
			}
		}
	}
	
	private static void findBacode() {
		for (int i = 0; i < N; i++) {
			for (int j = M - 1; j >= 0; j--) {
				if (map[i][j] == 1) {
					readBacode(i, j);
					return;
				}
			}
		}
	}
	
	private static void readBacode(int row, int col) {
		bacode = new int[56];
		for (int i = 55; i >= 0; i--) {
			bacode[i] = map[row][col];
			col--;
		}
	}
	
	private static void findCode() {
		code = new int[8];
		int start = 0;
		for (int i = 0; i < 8; i++) {
			StringBuilder sb = new StringBuilder();
			for (int j = 0; j < 7; j++) {
				sb.append(bacode[start + j]);
			}
			code[i] = findNumber(sb.toString());
			start += 7;
		}
	}
	
	private static int findNumber(String string) {
		if (string.equals(ZERO)) {
			return 0;
		}
		if (string.equals(ONE)) {
			return 1;
		}
		if (string.equals(TWO)) {
			return 2;
		}
		if (string.equals(THREE)) {
			return 3;
		}
		if (string.equals(FOUR)) {
			return 4;
		}
		if (string.equals(FIVE)) {
			return 5;
		}
		if (string.equals(SIX)) {
			return 6;
		}
		if (string.equals(SEVEN)) {
			return 7;
		}
		if (string.equals(EIGHT)) {
			return 8;
		}
		return 9;
	}
	
	private static int calculateCode() {
		int oddSum = 0;
		for (int i = 0; i < 8; i+=2) {
			oddSum += code[i];
		}
		oddSum *= 3;
		
		int evenSum = 0;
		for (int i = 1; i < 8; i+=2) {
			evenSum += code[i];
		}
		
		if (((oddSum + evenSum) % 10) != 0) {
			return 0;
		}
		
		int sum = 0;
		for (int i = 0; i < 8; i++) {
			sum += code[i];
		}
		return sum;
	}
	
	private static void printResult(int testCase, int result) throws IOException {
		bw.write("#"+testCase + " " + result +"\n");
	}
}


import java.io.*;
import java.util.*;

public class Solution {
	private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	private static int T, N;
	private static int[][] pascal;
	
	public static void main(String[] args) throws IOException {
		initT();
		initPascal();
		for (int testCase = 1; testCase <= T; testCase++) {
			initN();
			printResult(testCase);
			bw.flush();
		}
		bw.close();
	}
	
	private static void initT() throws IOException {
		T = Integer.parseInt(bf.readLine());
	}
	
	private static void initN() throws IOException {
		N = Integer.parseInt(bf.readLine());
	}
	
	private static void initPascal() {
		pascal = new int[10][10];
		pascal[0][0] = 1;
		for (int i = 1; i < 10; i++) {
			for (int j = 0; j < i + 1; j++) {
				pascal[i][j] = getLeft(i, j) + getRight(i, j);
			}
		}
	}
	
	private static int getLeft(int x, int y) {
		if (y - 1 < 0) {
			return 0;
		}
		return(pascal[x - 1][y - 1]);
	}
	
	private static int getRight(int x, int y) {
		if (10 <= y) {
			return 0;
		}
		return(pascal[x - 1][y]);
	}

	private static void printResult(int testCase) throws IOException {
		bw.write("#" + testCase);
		bw.write("\n");
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < i + 1; j++) {
				bw.write(pascal[i][j] + " ");
			}
			bw.write("\n");
		}
	}
}

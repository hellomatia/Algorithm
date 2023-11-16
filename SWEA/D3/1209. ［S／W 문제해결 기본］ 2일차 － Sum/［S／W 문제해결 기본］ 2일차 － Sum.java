
import java.io.*;
import java.util.*;

public class Solution {
	
	private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static int T, N;
	private static int rowSum[];
	private static int colSum[];
	private static int diagonal1;
	private static int diagonal2;
	
	public static void main(String args[]) throws Exception {
		solution();
	}
	
	private static void solution() throws IOException {
		T = 10;
		for (int testCase = 1; testCase <= T; testCase++) {
			initMap();
			printResult(testCase, findMaxSum());
		}
		bw.flush();
		bw.close();
	}
	
	private static void initMap() throws IOException {
		String testCase = bf.readLine();
		rowSum = new int[100];
		colSum = new int[100];
		diagonal1 = 0;
		diagonal2 = 0;
		for (int i = 0; i < 100; i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			for (int j = 0; j < 100; j++) {
				int num = Integer.parseInt(st.nextToken());
				rowSum[i] += num;
				colSum[j] += num;
				if (i == j) {
					diagonal1 += num;
				}
				if (N - 1 - j == i) {
					diagonal2 += num;
				}
			}
		}
	}

	private static int findMaxSum() {
		int max = Math.max(diagonal1, diagonal2);
		for (int i = 0; i < 100; i++) {
			max = Math.max(rowSum[i], max);
			max = Math.max(colSum[i], max);
		}
		return max;
	}
	
	private static void printResult(int testCase, int result) throws IOException {
		bw.write("#"+testCase + " " + result +"\n");
	}
}

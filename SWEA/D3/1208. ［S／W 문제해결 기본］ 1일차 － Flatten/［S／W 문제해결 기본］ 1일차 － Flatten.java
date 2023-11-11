
import java.io.*;
import java.util.*;

public class Solution {
	
	private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static int T, N;
	private static int min;
	private static int[] land;
	private static int boxTotal;
	private static boolean isFinished;
	
	public static void main(String args[]) throws Exception {
		solution();
	}
	
	private static void solution() throws IOException {
		T = 10;
		for (int testCase = 1; testCase <= T; testCase++) {
			initN();
			initLand();
			isFinished = false;
			for (int count = 0; count < N; count++) {
				toPungTan();
				if (isFinished) {
					break;
				}
			}
			printResult(testCase);
		}
		bw.flush();
		bw.close();
	}
	
	private static void initN() throws IOException {
		N = Integer.parseInt(bf.readLine());
	}
	
	private static void initLand() throws IOException {
		land = new int[100];
		StringTokenizer st = new StringTokenizer(bf.readLine());
		for (int index = 0; index < 100; index++) {
			land[index] = Integer.parseInt(st.nextToken());
			boxTotal += land[index];
		}
		Arrays.sort(land);
		min = land[99] - land[0];
	}
	
	private static void toPungTan() {
		if (boxTotal % 100 == 0 && min == 0) {
			isFinished = true;
		} else if (boxTotal % 100 != 0 && min == 1) {
			isFinished = true;
		}
		land[0]++;
		land[99]--;
		Arrays.sort(land);
		min = land[99] - land[0];
	}
	
	
	private static void printResult(int testCase) throws IOException {
		bw.write("#"+testCase + " " + min +"\n");
		
	}
}

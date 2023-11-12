
import java.io.*;
import java.util.*;

public class Solution {
	
	private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static int T, N, L;
	private static int[] memory;
	private static int[][] food;
	private static boolean[] visited;
	private static int bestScore;
	
	public static void main(String args[]) throws Exception {
		solution();
	}
	
	private static void solution() throws IOException {
		initT();
		for (int testCase = 1; testCase <= T; testCase++) {
			initNAndL();
			initFood();
			findBestScore(0, 0, 0);
			printResult(testCase, bestScore);
		}
		bw.flush();
		bw.close();
	}
	
	private static void initT() throws IOException {
		T = Integer.parseInt(bf.readLine());
	}
	
	private static void initNAndL() throws IOException {
		StringTokenizer st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
	}
	
	private static void initFood() throws IOException {
		food = new int[N][2];
		visited = new boolean[N];
		bestScore = 0;
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			int score = Integer.parseInt(st.nextToken());
			int cal = Integer.parseInt(st.nextToken());
			food[i][0] = score;
			food[i][1] = cal;
		}
	}
	
	
	private static void findBestScore(int start, int totalScore, int totalCal) {
		if (totalCal > L) {
			return;
		}
		
		bestScore = Math.max(bestScore, totalScore);
		
		for (int i = start; i < N; i++) {
			findBestScore(i + 1, totalScore + food[i][0], totalCal + food[i][1]);
		}
	}
		
	private static void printResult(int testCase, int result) throws IOException {
		bw.write("#"+testCase + " " + result +"\n");
	}
}

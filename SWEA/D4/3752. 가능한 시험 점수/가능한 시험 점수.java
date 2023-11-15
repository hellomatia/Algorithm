
import java.io.*;
import java.util.*;

public class Solution {
	
	private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static int T, N;
	private static int[] scores;
	private static Set<Integer> set;
	
	public static void main(String args[]) throws Exception {
		solution();
	}
	
	private static void solution() throws IOException {
		initT();
		for (int testCase = 1; testCase <= T; testCase++) {
			initN();
			initScores();
			set = new HashSet<>();
			set.add(0);
			for (int score : scores) {
				findAllScoreCount(score);
			}
			printResult(testCase, set.size());
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
	
	private static void initScores() throws IOException {
		scores = new int[N];
		StringTokenizer st = new StringTokenizer(bf.readLine());
		for (int i = 0; i < N; i++) {
			scores[i] = Integer.parseInt(st.nextToken());
		}	
	}
	
	private static void findAllScoreCount(int score) {
		Set<Integer> temp = new HashSet<>();
		temp.addAll(set);
		for (int s : temp) {
			set.add(s + score);
		}
	}
	
	private static void printResult(int testCase, int result) throws IOException {
		bw.write("#"+testCase + " " + result +"\n");
	}
}

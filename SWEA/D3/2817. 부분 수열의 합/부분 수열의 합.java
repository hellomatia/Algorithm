
import java.io.*;
import java.util.*;

public class Solution {
	
	private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static int T, N, K;
	private static int[] nums;
	private static int count;
	
	public static void main(String args[]) throws Exception {
		solution();
	}
	
	private static void solution() throws IOException {
		initT();
		for (int testCase = 1; testCase <= T; testCase++) {
			initNandK();
			initNums();
			count = 0;
			findSumK(0, 0);
			printResult(testCase, count);
		}
		bw.flush();
		bw.close();
	}
	
	private static void initT() throws IOException {
		T = Integer.parseInt(bf.readLine());
	}
	
	private static void initNandK() throws IOException {
		StringTokenizer st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
	}
	
	private static void initNums() throws IOException {
		nums = new int[N];
		StringTokenizer st = new StringTokenizer(bf.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
	}
	
	private static void findSumK(int start, int sum) {
		if (sum > K) {
			return;
		}
		if (sum == K) {
			count++;
			return;
		}
		
		for (int i = start; i < N; i++) {
			findSumK(i + 1, sum + nums[i]);
		}
	}

	private static void printResult(int testCase, int result) throws IOException {
		bw.write("#"+testCase + " " + result +"\n");
	}
}


import java.io.*;
import java.util.*;

public class Solution {
	
	private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static int T, N;
	private static int[] nums;
	private static int max;
	
	public static void main(String args[]) throws Exception {
		solution();
	}
	
	private static void solution() throws IOException {
		initT();
		for (int testCase = 1; testCase <= T; testCase++) {
			initNandNums();
			initPriorityQueue();
			printResult(testCase);
			bw.flush();
		}
		bw.close();
	}
	
	private static void initT() throws IOException {
		T = Integer.parseInt(bf.readLine());
	}
	
	private static void initNandNums() throws IOException {
		N = Integer.parseInt(bf.readLine());
		StringTokenizer st = new StringTokenizer(bf.readLine());
		nums = new int[N];
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
	}
	
	private static void initPriorityQueue() {
		max = -1;
		for (int i = N - 1; i >= 0; i--) {
			for (int j = i - 1; j >= 0; j--) {
				int num = nums[i] * nums[j];
				isDanjo(num);
			}
		}
	}
	
	private static boolean isDanjo(int num) {
		String string = String.valueOf(num);
		
		int lastNum = string.charAt(0) - '0';
		int nowIndex = 1;
		
		while (nowIndex < string.length()) {
			if (lastNum > string.charAt(nowIndex) - '0') {
				return false;
			}
			lastNum = string.charAt(nowIndex) - '0';
			nowIndex++;
		}
		max = Math.max(num, max);
		return true;
	}
	
	private static void printResult(int testCase) throws IOException {
		bw.write("#"+testCase + " " + max);
		bw.write("\n");
	}
}

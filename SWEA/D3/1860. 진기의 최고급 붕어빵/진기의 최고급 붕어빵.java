
import java.io.*;
import java.util.*;

public class Solution {
	
	private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static int T, N, M, K;
	private static int[] nums;

	public static void main(String args[]) throws Exception {
		solution();
	}
	
	private static void solution() throws IOException {
		initT();
		for (int testCase = 1; testCase <= T; testCase++) {
			initNandMandK();
			initNums();
			if (isPossible()) {
				printResult(testCase, "Possible");
			} else {
				printResult(testCase, "Impossible");
			}
		}
		bw.flush();
		bw.close();
	}
	
	private static void initT() throws IOException {
		T = Integer.parseInt(bf.readLine());
	}
	
	private static void initNandMandK() throws IOException {
		StringTokenizer st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
	}
	
	private static void initNums() throws IOException {
		nums = new int[N];
		StringTokenizer st = new StringTokenizer(bf.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(nums);
	}
	
	private static boolean isPossible() {
		int fishCount = 0;
		int customerCount = 0;
		for (int time = 0; time <= nums[N - 1]; time++) {
			if (time != 0 && time % M == 0) {
				fishCount += K;
			}
			while (customerCount < N && nums[customerCount] <= time) {
				if (fishCount == 0) {
					return false;
				}
				fishCount--;
				customerCount++;
			}
		}
		return true;
	}	
	
	private static void printResult(int testCase, String result) throws IOException {
		bw.write("#"+testCase + " " + result);
		bw.write("\n");
	}
}

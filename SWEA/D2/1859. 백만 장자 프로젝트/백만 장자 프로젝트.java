
import java.io.*;
import java.util.*;

public class Solution {
	
	private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static int T, N;
	private static int[] days;
	private static int max;
	
	public static void main(String args[]) throws Exception {
		solution();
	}
	
	private static void solution() throws IOException {
		initT();
		for (int testCase = 1; testCase <= T; testCase++) {
			initN();
			readPrice();
			printResult(testCase, findMax());
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
	
	
	private static void readPrice() throws IOException {
		days = new int[N];
		StringTokenizer st = new StringTokenizer(bf.readLine());
		for (int day = 0; day < N; day++) {
			days[day] = Integer.parseInt(st.nextToken());
		}
	}
	
	private static long findMax() {
		max = days[N - 1];
		long ans = 0;
		for (int day = N - 2; day >= 0; day--) {
			
			if (max < days[day]) {
				max = days[day];
			} else {
				ans += max - days[day];
			}
		}
		return ans;
	}
	
	
	private static void printResult(int testCase, long result) throws IOException {
		bw.write("#"+testCase+" "+result + "\n");
	}
}

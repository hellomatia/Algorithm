
import java.io.*;
import java.util.*;


public class Solution {
	private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static int T;
	private static int[] nums;
	private static Set<Integer> set;
	private static PriorityQueue<Integer> pq;
	
	
	public static void main(String[] args) throws IOException {
		initT();
		for (int testCase = 1; testCase <= T; testCase++) {
			initNums();
			findMax(0, 0, 0);
			for (int i = 0; i < 4; i++) {
				pq.poll();
			}
			printResult(testCase, pq.poll());
			bw.flush();
		}
		bw.close();
	}
	
	private static void initT() throws IOException {
		T = Integer.parseInt(bf.readLine());
	}
	
	private static void initNums() throws IOException {
		nums = new int[7];
		StringTokenizer stringTokenizer = new StringTokenizer(bf.readLine());
		for (int i = 0; i < 7; i++) {
			nums[i] = Integer.parseInt(stringTokenizer.nextToken());
		}
		set = new HashSet<>();
		pq = new PriorityQueue<>((o1, o2) -> o2 - o1);
	}
	
	private static void findMax(int start, int count, int number) {
		if (count == 3 && !set.contains(number)) {
			pq.offer(number);
			set.add(number);
			return;
		}
		
		for (int i = start; i < 7; i++) {
			findMax(i + 1, count + 1, number + nums[i]);
		}
	}
	
	private static void printResult(int testCase, int result) throws IOException {
		bw.write("#" + testCase + " " + result);
		bw.write("\n");
	}
}

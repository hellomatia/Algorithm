
import java.io.*;
import java.util.*;

public class Solution {
	
	private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static int T, N, K;
	private static Queue<Integer> queue;
	
	public static void main(String args[]) throws Exception {
		solution();
	}
	
	private static void solution() throws IOException {
		T = 10;
		for (int testCase = 1; testCase <= T; testCase++) {
			initTestCase();
			initNums();
			findCode();
			printResult(testCase);
		}
		bw.flush();
		bw.close();
	}
	
	private static void initT() throws IOException {
		T = Integer.parseInt(bf.readLine());
	}
	
	private static void initTestCase() throws IOException {
		N = Integer.parseInt(bf.readLine());
	}
	
	private static void initNums() throws IOException {
		queue = new LinkedList<>();
		StringTokenizer st = new StringTokenizer(bf.readLine());
		for (int i = 0; i < 8; i++) {
			queue.offer(Integer.parseInt(st.nextToken()));
		}
	}
	
	private static void findCode() {
		int count = 1;
		while (true) {
			if (count == 6) {
				count = 1;
			}
			int now = queue.poll();
			int newNum = now - count;
			if (newNum <= 0) {
				queue.offer(0);
				break;
			}
			queue.offer(newNum);
			count++;
		}
	}

	private static void printResult(int testCase) throws IOException {
		bw.write("#"+testCase + " ");
		while (!queue.isEmpty()) {
			bw.write(queue.poll() +" ");
		}
		bw.write("\n");
	}
}

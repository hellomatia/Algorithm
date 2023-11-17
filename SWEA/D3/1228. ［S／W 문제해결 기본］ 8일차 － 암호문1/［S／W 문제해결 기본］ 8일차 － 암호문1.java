
import java.io.*;
import java.util.*;

public class Solution {

	private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static int T, N, K;
	private static Deque<String> dq;

	public static void main(String args[]) throws Exception {
		solution();
	}

	private static void solution() throws IOException {
		T = 10;
		for (int testCase = 1; testCase <= T; testCase++) {
			initNums();
			readCommand();
			printResult(testCase);
			bw.flush();
		}
		bw.close();
	}

	private static void initT() throws IOException {
		T = Integer.parseInt(bf.readLine());
	}

	private static void initNums() throws IOException {
		dq = new LinkedList<>();
		int count = Integer.parseInt(bf.readLine());
		StringTokenizer st = new StringTokenizer(bf.readLine());
		for (int i = 0; i < count; i++) {
			dq.offer(st.nextToken());
		}
	}
	
	private static void readCommand() throws IOException {
		int count = Integer.parseInt(bf.readLine());
		StringTokenizer st = new StringTokenizer(bf.readLine());
		for (int i = 0; i < count; i++) {
			st.nextToken();
			int start = Integer.parseInt(st.nextToken());
			int addCount = Integer.parseInt(st.nextToken());
			Deque<String> temp = new LinkedList<>();
			for (int j = 0; j < start; j++) {
				temp.offer(dq.poll());
			}
			for (int j = 0; j < addCount; j++ ) {
				temp.offer(st.nextToken());
			}
			while (!temp.isEmpty()) {
				dq.offerFirst(temp.pollLast());
			}
		}
	}

	private static void printResult(int testCase) throws IOException {
		bw.write("#" + testCase + " ");
		int count = 10;
		while (count-- > 0) {
			bw.write(dq.poll() + " ");
		}
		bw.write("\n");
	}
}

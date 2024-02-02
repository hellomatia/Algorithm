import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	
	private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	private Queue<Integer> queue;
	private boolean isFinish;
	
	public static void main(String[] args) throws IOException {
		new Solution().solution();
	}
	
	private void solution() throws IOException {
		int T = 10;
		for (int testCase = 1; testCase <= T; testCase++) {
			init();
			calcResult();
			printResult(testCase);
		}
		bw.flush();
		bw.close();
	}
	
	private void init() throws IOException {
		bf.readLine();
		queue = new ArrayDeque<>();
		StringTokenizer st = new StringTokenizer(bf.readLine());
		for (int i = 0; i < 8; i++) {
			queue.offer(Integer.parseInt(st.nextToken()));
		}
		isFinish = false;
	}
	
	private void calcResult() {
		while (!isFinish) {
			oneCycle();
		}
	}
	
	private void oneCycle() {
		for (int i = 1; i <= 5; i++) {
			int newNum = queue.poll() - i;
			if (newNum <= 0) {
				queue.offer(0);
				isFinish = true;
				return;
			}
			queue.offer(newNum);
		}
	}
	
	private void printResult(int testCase) throws IOException {
		bw.write("#" + testCase + " ");
		while (!queue.isEmpty()) {
			bw.write(queue.poll() + " ");
		}
		bw.write("\n");
	}
}

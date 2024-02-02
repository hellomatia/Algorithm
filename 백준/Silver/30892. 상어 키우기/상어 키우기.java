import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	
	private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	private int N, K;
	private long T;
	private PriorityQueue<Long> pq;
	
	public static void main(String[] args) throws IOException {
		new Main().solution();
	}
	
	private void solution() throws IOException {
		init();
		calcResult();
		printResult(T);
		bw.flush();
		bw.close();
	}
	
	private void init() throws IOException {
		StringTokenizer st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		T = Long.parseLong(st.nextToken());
		
		pq = new PriorityQueue<>();
		st = new StringTokenizer(bf.readLine());
		for (int i = 0; i < N; i++) {
			pq.offer(Long.parseLong(st.nextToken()));
		}
	}
	
	private void calcResult() {
		Stack<Long> stack = new Stack<>();
		int count = 0;
		while (!pq.isEmpty() && count < K) {
			if (pq.peek() < T) {
				stack.push(pq.poll());
			} else {
				if (stack.isEmpty()) {
					break;
				}
				while (!stack.isEmpty() && T <= pq.peek()) {
					T += stack.pop();
					count++;
				}
			}
		}
		
		while (!stack.isEmpty() && count < K) {
			T += stack.pop();
			count++;
		}
	}
	
	private void printResult(long result) throws IOException {
		bw.write(result + "\n");
	}
}


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	private int N;
	private int K;
	
	public static void main(String[] args) throws IOException {
		new Main().solution();
	}
	
	private void solution() throws IOException {
		init();
		printResult(calcResult());
		bw.flush();
		bw.close();
	}
	
	private void init() throws IOException {
		StringTokenizer st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
	}
	
	private String calcResult() {
		StringBuilder sb = new StringBuilder();
		sb.append("<");
		Queue<Integer> queue = new ArrayDeque<>();
		for (int i = 1; i <= N; i++) {
			queue.offer(i);
		}
		
		int count = 1;
		while (queue.size() != 1) {
			if (count == K) {
				sb.append(queue.poll() + ", ");
				count = 1;
				continue;
			}
			queue.offer(queue.poll());
			count++;
		}
		
		sb.append(queue.poll()).append(">");
	
		return sb.toString();
	}
	
	private void printResult(String result) throws IOException {
		bw.write(result);
	}
}


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
	
	private int n, w, L;
	private Queue<Integer> trucks;
	private Queue<Integer> times;
	
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
		n = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
	
		trucks = new ArrayDeque<Integer>();
		st = new StringTokenizer(bf.readLine());
		for (int i = 0; i < n; i++) {
			trucks.offer(Integer.parseInt(st.nextToken()));
		}
	}
	
	private int calcResult() {
		Queue<Integer> bridge = new ArrayDeque<>();
		times = new ArrayDeque<>();
		int time = 0;
		int nowL = 0;
		int nowW = 0;
		
		while (!trucks.isEmpty() || !bridge.isEmpty()) {
			time++;
			if (!trucks.isEmpty() && trucks.peek() + nowL <= L && nowW < w) {
				nowL += trucks.peek();
				nowW++;
				bridge.offer(trucks.poll());
				times.offer(0);
			}
			updateTime();
			if (times.peek() == w) {
				nowL -= bridge.peek();
				nowW--;
				bridge.poll();
				times.poll();
			}
		}
		return time + 1;
	}
	
	private void updateTime() {
		int count = times.size();
		for (int i = 0; i < count; i++) {
			times.offer(times.poll() + 1);
		}
	}
	
	private void printResult(long result) throws IOException {
		bw.write(result + "\n");
	}
}


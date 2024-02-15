
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {

	private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	private int N, M;
	private int[] graph;
	private int[] d;
	private List<Integer>[] nearNodes;
	private boolean[] isFinished;
	private int id;
	private int size;
	private Stack<Integer> stack;
	
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
		M = Integer.parseInt(st.nextToken());
		nearNodes = new List[(N + 1) * 2 + 1];
		for (int i = 2; i < (N + 1) * 2 + 1; i++) {
			nearNodes[i] = new LinkedList<>();
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(bf.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			
			int from = x1 > 0 ? x1 * 2 : (-x1 * 2) + 1;
			int to = x2 > 0 ? x2 * 2 : (-x2 * 2) + 1;
			
			nearNodes[oppo(from)].add(to);
			nearNodes[oppo(to)].add(from);
		}
		graph = new int[(N + 1) * 2 + 1];
		d = new int[(N + 1) * 2 + 1];
		isFinished = new boolean[(N + 1) * 2 + 1];
		stack = new Stack<>();
	}
	
	private int calcResult() {
		for (int i = 1; i <= N; i++) {
			if (!isFinished[i * 2]) css(i * 2);
			if (!isFinished[i * 2 + 1]) css(i * 2 + 1);
		}
		for (int i = 1; i <= N; i++) {
			if (graph[i * 2] == graph[i * 2 + 1]) {
				return 0;
			}
		}
		return 1;
	}
	
	private int oppo(int num) {
		return num % 2 == 0 ? num + 1 : num - 1;
	}
	
	private int css(int now) {
		d[now] = ++id;
		int parent = d[now];
		stack.push(now);
		
		for (int to : nearNodes[now]) {
			if (d[to] == 0) parent = Math.min(parent, css(to));
			else if (!isFinished[to]) parent = Math.min(parent, d[to]);
		}
		
		if (parent == d[now]) {
			size++;
			while (!stack.isEmpty()) {
				int num = stack.pop();
				graph[num] = size;
				isFinished[num] = true;
				if (num == now) {
					break;
				}
			}
		}

		return parent;
	}

	private void printResult(int result) throws IOException {
		bw.write(result + "\n");
	}
}

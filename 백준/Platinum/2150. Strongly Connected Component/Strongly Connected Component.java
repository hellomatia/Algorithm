
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
	
	private int V, E;
	private List<Integer>[] nearNodes;
	private PriorityQueue<PriorityQueue<Integer>> SCC;
	private Stack<Integer> stack;
	private boolean[] isFinished;
	private int[] d;
	private int id;
	
	public static void main(String[] args) throws IOException {
		new Main().solution();
	}

	private void solution() throws IOException {
		init();
		for (int i = 1; i <= V; i++) {
			if (isFinished[i]) continue;
			calcResult(i);
		}
		printResult();
		bw.flush();
		bw.close();
	}

	private void init() throws IOException {
		StringTokenizer st = new StringTokenizer(bf.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		nearNodes = new List[V + 1];
		for (int i = 1; i <= V; i++) {
			nearNodes[i] = new LinkedList<>();
		}
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(bf.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			nearNodes[from].add(to);
		}
		
		isFinished = new boolean[V + 1];
		d = new int[V + 1];
		stack = new Stack<>();
		SCC = new PriorityQueue<>((o1, o2) -> o1.peek() - o2.peek());
	}
	
	private int calcResult(int from) {
		d[from] = ++id;
		stack.push(from);
		int parent = d[from];
		for (int to : nearNodes[from]) {
			if (d[to] == 0) parent = Math.min(parent, calcResult(to));
			else if (!isFinished[to]) parent = Math.min(parent, d[to]);
		}
		
		if (parent == d[from]) {
			PriorityQueue pq = new PriorityQueue<>();
			while (true) {
				int node = stack.pop();
				pq.offer(node);
				isFinished[node] = true;
				if (node == from) break;
			}
			SCC.offer(pq);
		}
		
		return parent;
	}

	private void printResult() throws IOException {
		bw.write(SCC.size() + "\n");
		while (!SCC.isEmpty()) {
			PriorityQueue pq = SCC.poll();
			while (!pq.isEmpty()) {
				bw.write(pq.poll() + " ");
			}
			bw.write("-1\n");
		}
	}
}

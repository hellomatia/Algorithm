
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	private int N, M, id, size;
	private List<Integer>[] nearNodes;
	private PriorityQueue<Integer> result;
	private int[] d, graph, indegree;
	private boolean[] isFinished;
	private Stack<Integer> stack;
	private Map<Integer, List<Integer>> sccList;
	
	public static void main(String[] args) throws IOException {
		new Main().solution();
	}

	private void solution() throws IOException {
		int testCase = Integer.parseInt(bf.readLine());
		for (int t = 1; t <= testCase; t++) {
			init();
			calcResult();
			printResult();
			if (t != testCase) bf.readLine();
		}
		bw.flush();
		bw.close();
	}

	private void init() throws IOException {
		StringTokenizer st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		nearNodes = new List[N];
		for (int i = 0; i < N; i++) {
			nearNodes[i] = new LinkedList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(bf.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			nearNodes[from].add(to);
		}
		
		size = id = 0;
		d = new int[N];
		graph = new int[N];
		isFinished = new boolean[N];
		stack = new Stack<>();
		sccList = new HashMap<>();
		result = new PriorityQueue<>();
	}
	
	private void calcResult() {
		for (int i = 0; i < N; i++) {
			if (!isFinished[i]) {
				scc(i);
			}
		}
		
		indegree = new int[size];
		
		for (int from = 0; from < N; from++) {
			for (int to : nearNodes[from]) {
				if (graph[from] == graph[to]) continue;
				indegree[graph[to]]++;
			}

		}
		
		int zeroIndegreeCount = 0;

		for (int i = 0; i < size; i++) {
			if (indegree[i] == 0) {
				zeroIndegreeCount++;
				result.addAll(sccList.get(i));
			}
		}
		
		if (zeroIndegreeCount != 1) {
			result.clear();
		}
	}
	
	private int scc(int from) {
		d[from] = ++id;
		int parent = d[from];
		stack.push(from);
		
		for (int to : nearNodes[from]) {
			if (d[to] == 0) parent = Math.min(parent, scc(to));
			else if (!isFinished[to]) parent = Math.min(parent, d[to]);
		}
		
		if (parent == d[from]) {
			List<Integer> list = new LinkedList<>();
			while (!stack.isEmpty()) {
				int node = stack.pop();
				graph[node] = size;
				isFinished[node] = true;
				list.add(node);
				if (node == from) break;
			}
			sccList.put(size, list);
			size++;
		}
		
		return parent;
	}
	
	private void printResult() throws IOException {
		if (result.isEmpty()) {
			bw.write("Confused\n\n");
			return;
		} else while (!result.isEmpty()) bw.write(result.poll() + "\n");
		bw.write("\n");
	}
}

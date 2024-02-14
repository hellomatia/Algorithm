
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
	
	private int N, M, id, sccSize;
	private List<Integer>[] nearNodes;
	private boolean[] isFinish;
	private int[] d;
	private Stack<Integer> stack;
	private int[] graph;
	private int[] indegree;

	
	public static void main(String[] args) throws IOException {
		new Main().solution();
	}

	private void solution() throws IOException {
		int testCase = Integer.parseInt(bf.readLine());
		for (int i = 0; i < testCase; i++) {
			init();
			printResult(calcResult());
		}

		bw.flush();
		bw.close();
	}

	private void init() throws IOException {
		StringTokenizer st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		nearNodes = new List[N + 1];
		for (int i = 1; i <= N; i++) {
			nearNodes[i] = new LinkedList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(bf.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			nearNodes[from].add(to);
		}
		
		isFinish = new boolean[N + 1];
		d = new int[N + 1];
		stack = new Stack<>();
		id = 0;
		graph = new int[N + 1];
		sccSize = 0;
	}
	
	private int calcResult() {
		int result = 0;
		for (int i = 1; i <= N; i++) {
			if (isFinish[i]) continue;
			css(i);
		}

		indegree = new int[sccSize + 1];
		
		for (int i = 1; i <= N; i++) {
			for (int j : nearNodes[i]) {
				if (graph[i] != graph[j]) {
					indegree[graph[j]]++;
				}
			}
		}
		
		for (int i = 1; i <= sccSize; i++) {
			if (indegree[i] == 0) result++;
		}

		return result;
	}
	
	private int css(int now) {
		d[now] = ++id;
		
		int parent = d[now];
		stack.push(now);
		
		for (int to : nearNodes[now]) {
			if (d[to] == 0) parent = Math.min(parent, css(to));
			else if (!isFinish[to]) parent = Math.min(parent, d[to]);
		}
		
		if (d[now] == parent) {
			while (!stack.isEmpty()) {
				int num = stack.pop();
				isFinish[num] = true;
				graph[num] = sccSize + 1;
				if (num == now) break;
			}
			sccSize++;
		}
		return parent;
	}

	private void printResult(int result) throws IOException {
		bw.write(result + "\n");
	}
}

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	class SegTree {
		long[] tree;

		SegTree(int N) {
			int treeSize = 1 << (int) Math.ceil(Math.log(N) / Math.log(2) + 1);
			tree = new long[treeSize];
		}

		public void update(int node, int start, int end, int index) {
			int mid = start + end >>> 1;
			if (index < start || end < index) {
				return;
			} else if (start == end) {
				tree[node]++;
				return;
			}

			update(node * 2, start, mid, index);
			update(node * 2 + 1, mid + 1, end, index);

			tree[node] = tree[node * 2] + tree[node * 2 + 1];
		}

		public long sum(int node, int start, int end, int left, int right) {
			if (right < start || end < left) {
				return 0;
			}

			if (left <= start && end <= right) {
				return tree[node];
			}

			int mid = start + end >>> 1;

			return sum(node * 2, start, mid, left, right) + sum(node * 2 + 1, mid + 1, end, left, right);
		}
	}

	private final BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	private final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	private int N, C;
	private SegTree segTree;
	private List<Integer>[] adj;
	private int[][] range;
	private int id;
	private long[] depth;
	private StringBuilder sb;

	public static void main(String[] args) throws IOException {
		new Main().solution();
	}

	private void solution() throws IOException {
		init();
		calcResult();
		printResult();
	}

	private void init() throws IOException {
		StringTokenizer st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		adj = new List[N + 1];
		for (int i = 1; i <= N; i++) {
			adj[i] = new ArrayList<>();
		}
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(bf.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			adj[from].add(to);
			adj[to].add(from);
		}

		range = new int[N + 1][2];
		depth = new long[N + 1];
		dfs(C, C, 1);
		segTree = new SegTree(N);
		sb = new StringBuilder();
	}

	private void dfs(int now, int from, int level) {
		range[now][0] = ++id;
		depth[now] = level;
		for (int to : adj[now]) {
			if (to == from)
				continue;
			dfs(to, now, level + 1);
		}
		range[now][1] = id;
	}

	private void calcResult() throws IOException {
		int Q = Integer.parseInt(bf.readLine());
		for (int i = 0; i < Q; i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			int command = Integer.parseInt(st.nextToken());
			int A = Integer.parseInt(st.nextToken());
			if (command == 1) {
				segTree.update(1, 1, N, range[A][0]);
			} else {
				long result = segTree.sum(1, 1, N, range[A][0], range[A][1]) * depth[A];
				sb.append(result).append("\n");
			}
		}
	}

	private void printResult() throws IOException {
		bw.write(sb.toString());
		bw.flush();
	}
}
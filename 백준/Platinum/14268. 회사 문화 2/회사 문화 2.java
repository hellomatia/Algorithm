import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;


public class Main {
	
	class SegmentTree {
		
		int[] tree;
		int[] lazy;
		
		SegmentTree(int n) {
			int treeSize = 1 << (int) Math.ceil(Math.log(n + 1) / Math.log(2) + 1);
			tree = new int[treeSize];
			lazy = new int[treeSize];
		}
		
		void lazyUpdate(int node, int start, int end) {
			if (lazy[node] != 0) {
				tree[node] += lazy[node];
				if (start != end) {
					lazy[node * 2] += lazy[node];
					lazy[node * 2 + 1] += lazy[node];
				}
				lazy[node] = 0;
			}
		}
		
		void update(int node, int start, int end, int left, int right, int diff) {
			lazyUpdate(node, start, end);
			if (right < start || end < left) {
				return;
			}
			if (left <= start && end <= right) {
				lazy[node] = diff;
				lazyUpdate(node, start, end);
				return;
			}
			
			int mid = (start + end) >>> 1;
			update(node * 2, start, mid, left, right, diff);
			update(node * 2 + 1, mid + 1, end, left, right, diff);
		}

		int query(int node, int start, int end, int index) {
			lazyUpdate(node, start, end);
			int mid = (start + end) >>> 1;
			if (start == end) {
				return tree[node];
			} else if (index <= mid) {
				return query(node * 2, start, mid, index);
			} else {
				return query(node * 2 + 1, mid + 1, end, index);
			}
		}
	}
	
	private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	private int n, m;
	private int[][] range;
	private int id;
	private List<Integer>[] adj;
	private SegmentTree segmentTree;
	
	public static void main(String[] args) throws IOException {
		new Main().solution();
	}

	private void solution() throws IOException {
		init();
		calcResult();
		bw.close();
	}

	private void init() throws IOException {
		StringTokenizer st = new StringTokenizer(bf.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		adj = new List[n + 1];
		for (int i = 1; i <= n; i++) {
			adj[i] = new ArrayList<>();
		}
		
		st = new StringTokenizer(bf.readLine());
		st.nextToken();
		for (int i = 2; i <= n; i++) {
			int parent = Integer.parseInt(st.nextToken());
			adj[parent].add(i);
		}
		
		range = new int[n + 1][2];
		dfs(1);
		
		segmentTree = new SegmentTree(n);
	}
	
	private void dfs(int from) {
		range[from][0] = ++id;
		for (int to : adj[from]) {
			dfs(to);
		}
		range[from][1] = id;
	}

	private void calcResult() throws IOException {
		for (int i = 0; i < m; i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			int command = Integer.parseInt(st.nextToken());
			if (command == 1) {
				int a = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());
				segmentTree.update(1, 1, n, range[a][0], range[a][1], w);
			} else {
				int a = Integer.parseInt(st.nextToken());
				printResult(segmentTree.query(1, 1, n, range[a][0]));
			}
		}
	}

	private void printResult(int result) throws IOException {
		bw.write(result + "\n");
		bw.flush();
	}
}

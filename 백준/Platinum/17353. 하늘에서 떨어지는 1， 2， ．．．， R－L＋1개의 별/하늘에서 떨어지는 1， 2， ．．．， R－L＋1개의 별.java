import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	class SegTree {
		long[] tree;
		double[] lazy;

		public SegTree(int N) {
			int treeSize = 1 << (int) Math.ceil(Math.log(N) / Math.log(2) + 1);
			tree = new long[treeSize];
			lazy = new double[treeSize];
		}

		public void lazyUpdate(int node, int start, int end) {
			if (lazy[node] != 0) {
				int count = (end - start) + 1;

				tree[node] += lazy[node] * count;

				if (start != end) {
					lazy[node * 2] += lazy[node];
					lazy[node * 2 + 1] += lazy[node];
				}
				lazy[node] = 0;
			}
		}

		public void update(int node, int start, int end, int left, int right, int diff) {
			lazyUpdate(node, start, end);

			if (right < start || end < left) {
				return;
			}

			if (left <= start && end <= right) {
				lazy[node] += diff;
				lazyUpdate(node, start, end);
				return;
			}

			int mid = (start + end) >>> 1;

			update(node * 2, start, mid, left, right, diff);
			update(node * 2 + 1, mid + 1, end, left, right, diff);

			tree[node] = tree[node * 2] + tree[node * 2 + 1];
		}

		public long query(int node, int start, int end, int left, int right) {
			lazyUpdate(node, start, end);

			if (right < start || end < left) {
				return 0;
			}

			if (left <= start && end <= right) {
				return tree[node];
			}

			int mid = (start + end) >>> 1;

			return query(node * 2, start, mid, left, right) + query(node * 2 + 1, mid + 1, end, left, right);
		}
	}

	private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	private int N, Q;
	private int[] arr;
	private SegTree segTree;

	public static void main(String[] args) throws IOException {
		new Main().solution();
	}

	private void solution() throws IOException {
		init();
		calcResult();
		bw.flush();
		bw.close();
	}

	private void init() throws IOException {
		N = Integer.parseInt(bf.readLine());
		arr = new int[N + 1];
		StringTokenizer st = new StringTokenizer(bf.readLine());
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		segTree = new SegTree(N);
		for (int i = 1; i <= N; i++) {
			segTree.update(1, 1, N, i, i, arr[i] - arr[i - 1]);
		}

		Q = Integer.parseInt(bf.readLine());
	}

	private void calcResult() throws IOException {
		for (int i = 0; i < Q; i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			int command = Integer.parseInt(st.nextToken());
			if (command == 1) {
				int left = Integer.parseInt(st.nextToken());
				int right = Integer.parseInt(st.nextToken());
				segTree.update(1, 1, N, left, right, 1);
				segTree.update(1, 1, N, right + 1, right + 1, -(right - left + 1));
			} else {
				int index = Integer.parseInt(st.nextToken());
				printResult(segTree.query(1, 1, N, 1, index));
			}
		}
	}

	private void printResult(long result) throws IOException {
		bw.write(result + "\n");
	}
}
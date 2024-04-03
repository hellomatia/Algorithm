import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.TreeMap;

class Main {

	class SegTree {

		TreeMap<Integer, Long>[] tree;
		int[] version;

		SegTree(long[] arr) {
			int treeSize = 1 << (int) Math.ceil(Math.log(arr.length) / Math.log(2) + 1);
			tree = new TreeMap[treeSize];
			for (int i = 0; i < treeSize; i++) {
				tree[i] = new TreeMap<>();
			}
			version = new int[treeSize];
			init(1, 1, arr.length - 1, arr);
		}

		private void init(int node, int start, int end, long[] arr) {
			if (start == end) {
				tree[node].put(0, arr[start]);
				return;
			}

			int mid = start + end >>> 1;
			init(node * 2, start, mid, arr);
			init(node * 2 + 1, mid + 1, end, arr);

			long value = tree[node * 2].get(0) + tree[node * 2 + 1].get(0);

			tree[node].put(0, value);
		}

		public void update(int node, int start, int end, int left, int right, long value, int sequence) {
			if (right < start || end < left) {
				return;
			}

			if (start == end) {
				version[node] = sequence;
				tree[node].put(sequence, value);
				return;
			}

			int mid = start + end >>> 1;

			update(node * 2, start, mid, left, right, value, sequence);
			update(node * 2 + 1, mid + 1, end, left, right, value, sequence);

			long newValue = tree[node * 2].get(version[node * 2]) + tree[node * 2 + 1].get(version[node * 2 + 1]);

			version[node] = sequence;
			tree[node].put(sequence, newValue);
		}

		public long sum(int node, int start, int end, int left, int right, int sequence) {
			if (right < start || end < left) {
				return 0;
			}

			if (left <= start && end <= right) {
				Entry<Integer, Long> value = tree[node].lowerEntry(sequence + 1);

				return value.getValue();
			}

			int mid = start + end >>> 1;
			return sum(node * 2, start, mid, left, right, sequence)
					+ sum(node * 2 + 1, mid + 1, end, left, right, sequence);
		}

	}

	private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	private int N, M;
	private long[] arr;
	private int sequence;
	private SegTree segTree;

	public static void main(String[] args) throws IOException {
		new Main().solution();
	}

	public void solution() throws IOException {
		init();
		calcResult();
	}

	private void init() throws IOException {
		N = Integer.parseInt(bf.readLine());
		arr = new long[N + 1];
		StringTokenizer st = new StringTokenizer(bf.readLine());
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		M = Integer.parseInt(bf.readLine());

		segTree = new SegTree(arr);
	}

	private void calcResult() throws IOException {
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			int command = Integer.parseInt(st.nextToken());
			if (command == 1) {
				int index = Integer.parseInt(st.nextToken());
				int value = Integer.parseInt(st.nextToken());
				segTree.update(1, 1, N, index, index, value, ++sequence);
			} else {
				int k = Integer.parseInt(st.nextToken());
				int left = Integer.parseInt(st.nextToken());
				int right = Integer.parseInt(st.nextToken());
				printResult(segTree.sum(1, 1, N, left, right, k));
			}
		}
	}

	private void printResult(long result) throws IOException {
		bw.write(result + "\n");
		bw.flush();
	}
}
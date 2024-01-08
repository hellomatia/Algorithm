import java.io.*;
import java.util.*;

class SegmentTree {
	
	private static final int MOD = 1_000_000_007;
	
	int[] arr;
	int treeSize;
	long[] tree;
	
	SegmentTree(int[] arr) {
		this.arr = arr;
		this.treeSize = (int) Math.pow(2, (int) Math.ceil(Math.log(arr.length) / Math.log(2)) + 1);
		this.tree = new long[treeSize];
	}
	
	public void init(int node, int start, int end) {
		if (start == end) {
			tree[node] = arr[start];
			return;
		}
		init(node * 2, start, (start + end) / 2);
		init(node * 2 + 1, (start + end) / 2 + 1, end);
		tree[node] = (tree[node * 2] * tree[node * 2 + 1]) % MOD;
	}
	
	public long multiple(int node, int start, int end, int left, int right) {
		if (right < start || end < left) {
			return 1;
		}
		if (left <= start && end <= right) {
			return tree[node];
		}
		
		long leftValue = multiple(node * 2, start, (start + end) / 2, left, right);
		long rightValue = multiple(node * 2 + 1, (start + end) / 2 + 1, end, left, right);
		return (leftValue * rightValue) % MOD;
	}
	
	public void update(int node, int start, int end, int index, int value) {
		if (index < start || end < index) {
			return;
		}
		if (start == end) {
			tree[node] = value;
			return;
		}
		
		update(node * 2, start, (start + end) / 2, index, value);
		update(node * 2 + 1, (start + end) / 2 + 1, end, index, value);
		
		tree[node] = (tree[node * 2] * tree[node * 2 + 1]) % MOD;
	}
}

public class Main {
	
	private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	
	private static int N, M, K;
	private static int[] arr;
	private static SegmentTree segmentTree;
	
	public static void main(String[] args) throws IOException {
		new Main().solution();
	}
	
	private void solution() throws IOException {
		init();
		for (int i = 0; i < M + K; i++) {
			readCommand();
		}
		bw.flush();
		bw.close();
	}
	
	private void init() throws IOException {
		StringTokenizer st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arr = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(bf.readLine());
		}
		segmentTree = new SegmentTree(arr);
		segmentTree.init(1, 1, N);
	}
	
	private void readCommand() throws IOException {
		StringTokenizer st = new StringTokenizer(bf.readLine());
		int command = Integer.parseInt(st.nextToken());
		if (command == 1) {
			int index = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());
			segmentTree.update(1, 1, N, index, value);
		}
		if (command == 2) {
			int left = Integer.parseInt(st.nextToken());
			int right = Integer.parseInt(st.nextToken());
			printResult(segmentTree.multiple(1, 1, N, left, right));
		}
		
	}
	
	private void printResult(long result) throws IOException {
		bw.write(result + "\n");
	}
}

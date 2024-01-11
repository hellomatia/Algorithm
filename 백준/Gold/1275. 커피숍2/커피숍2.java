
import java.io.*;
import java.util.*;

class SegmentTree {
	
	long[] arr; 
	int treeSize;
	long[] tree;
	
	SegmentTree(long[] arr) {
		this.arr = arr;
		this.treeSize = (int) Math.pow(2, (int) Math.ceil(Math.log(arr.length)/Math.log(2)) + 1);
		this.tree = new long[treeSize];
	}
	
	void init(int node, int start, int end) {
		if (start == end) {
			tree[node] = arr[start];
			return;
		}
		int mid = (start + end) / 2;
		init(node * 2, start, mid);
		init(node * 2 + 1, mid + 1, end);
		tree[node] = tree[node * 2] + tree[node * 2 + 1];
	}
	
	void update(int node, int start, int end, int index, long value) {
		if (index < start || end < index) {
			return;
		}
		if (start == end) {
			tree[node] = value;
			return;
		}
		int mid = (start + end) / 2;
		update(node * 2, start, mid, index, value);
		update(node * 2 + 1, mid + 1, end, index, value);
		tree[node] = tree[node * 2] + tree[node * 2 + 1];
	}
	
	long sum(int node, int start, int end, int left, int right) {
		if (right < start || end < left) {
			return 0L;
		}
		if (left <= start && end <= right) {
			return tree[node];
		}
		int mid = (start + end) / 2;
		long leftValue = sum(node * 2, start, mid, left, right);
		long rightValue = sum(node * 2 + 1, mid + 1, end, left, right);
		return leftValue + rightValue;
	}
}

public class Main {

    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    
    private static int N;
    private static int Q;
    private static long[] arr;
    private static SegmentTree segmentTree;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
    	init();
    	for (int i = 0; i < Q; i++) {
    		readCommand();
    	}
    	bw.flush();
        bw.close();
    }

    private void init() throws IOException {
    	StringTokenizer st = new StringTokenizer(bf.readLine());
    	N = Integer.parseInt(st.nextToken());
    	Q = Integer.parseInt(st.nextToken());
    	
    	st = new StringTokenizer(bf.readLine());
    	arr = new long[N + 1];
    	for (int i = 1; i <= N; i++) {
    		arr[i] = Integer.parseInt(st.nextToken());
    	}
    	
    	segmentTree = new SegmentTree(arr);
    	segmentTree.init(1, 1, N);
    }

    private void readCommand() throws IOException {
    	StringTokenizer st = new StringTokenizer(bf.readLine());
    	int left = Integer.parseInt(st.nextToken());
    	int right = Integer.parseInt(st.nextToken());
    	if (left > right) {
    		int temp = left;
    		left = right;
    		right = temp;
    	}
    	printResult(segmentTree.sum(1, 1, N, left, right));
    	
    	int index = Integer.parseInt(st.nextToken());
    	long value = Long.parseLong(st.nextToken());
    	segmentTree.update(1, 1, N, index, value);
    }

    private void printResult(long result) throws IOException {
        bw.write(result + "\n");
    }
}
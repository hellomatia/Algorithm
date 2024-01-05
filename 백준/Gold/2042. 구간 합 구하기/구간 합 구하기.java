import java.io.*;
import java.util.*;

class SegmentTree{
	
	int treeSize;
	long[] arr;
	long[] tree;
	
	SegmentTree(long[] arr) {
		int treeHight = (int) Math.ceil(Math.log(arr.length) / Math.log(2));
		this.treeSize = (int) Math.pow(2, treeHight + 1);
		this.tree = new long[treeSize];
		this.arr = arr;
	}
	
	void init(int node, int start, int end) {
		if (start == end) {
			tree[node] = arr[start];
			return;
		}
		
		init(node * 2, start, (start + end) / 2);
		init(node * 2 + 1, (start + end) / 2 + 1, end);
		tree[node] = tree[node * 2] + tree[node * 2 + 1];
	}
	
	long sum(int node, int start, int end, int left, int right) {
		if (right < start || end < left) {
			return 0L;
		}
		if (left <= start && end <= right) {
			return tree[node];
		}
		
		long leftValue = sum(node * 2, start, (start + end) / 2, left, right);
		long rightValue = sum(node * 2 + 1, (start + end) / 2 + 1, end, left, right);
		return leftValue + rightValue;
	}
	
	void update(int node, int start, int end, int index, long value) {
		if (index < start || end < index) {
			return;
		}
		if (start == end) {
			tree[node] = value;
			return;
		}
		
		update(node * 2, start, (start + end) / 2, index, value);
		update(node * 2 + 1, (start + end) / 2 + 1, end, index, value);
		
		tree[node] = tree[node * 2] + tree[node * 2 + 1];
	}
	
}

public class Main {

    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int N, M, K;
    private static long[] arr;
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
    	
    	arr = new long[N + 1];
    	for (int i = 1; i <= N; i++) {
    		arr[i] = Long.parseLong(bf.readLine());
    	}
    	segmentTree = new SegmentTree(arr);
    	segmentTree.init(1, 1, N);
    }
    
    private void readCommand() throws IOException {
    	StringTokenizer st = new StringTokenizer(bf.readLine());
    	int command = Integer.parseInt(st.nextToken());
    	
    	if (command == 1) {
    		int index = Integer.parseInt(st.nextToken());
    		long value = Long.parseLong(st.nextToken());
    		segmentTree.update(1, 1, N, index, value);
    		return;
    	}
    	
    	if (command == 2) {
    		int left = Integer.parseInt(st.nextToken());
    		int right = Integer.parseInt(st.nextToken());
    		printResult(segmentTree.sum(1, 1, N, left, right));
    		return;
    	}
    }

    private void printResult(long result) throws IOException {
        bw.write(result + "\n");
    }
}
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

class Segtree{
	
	int[] tree;
	boolean[] lazy;
	
	Segtree(int n) {
		int treeSize = 1 << (int) (Math.ceil(Math.log(n) / Math.log(2)) + 1);
		tree = new int[treeSize];
		lazy = new boolean[treeSize];
	}
	
	void lazyUpdate(int node, int start, int end) {
		if (lazy[node]) {
			tree[node] = (end - start + 1) - tree[node];
			lazy[node] = false;
			if (start != end) {
				lazy[node * 2] = !(lazy[node * 2]);
				lazy[node * 2 + 1] = !(lazy[node * 2 + 1]);
			}
		}
	}
	
	void update(int node, int start, int end, int left, int right) {
		lazyUpdate(node, start, end);

		if (right < start || end < left) {
			return;
		}
		
		if (left <= start && end <= right) {
			lazy[node] = !(lazy[node]);
			lazyUpdate(node, start, end);
			return;
		}
		
		int mid = start + (end - start) / 2;
		update(node * 2, start, mid, left, right);
		update(node * 2 + 1, mid + 1, end, left, right);
		
		tree[node] = tree[node * 2] + tree[node * 2 + 1];
	}
	
	int getCount(int node, int start, int end, int left, int right) {
		lazyUpdate(node, start, end);
		
		if (right < start || end < left) {
			return 0;
		}
		
		if (left <= start && end <= right) {
			return tree[node];
		}
		
		int mid = start + (end - start) / 2;
		
		return getCount(node * 2, start, mid, left, right) + 
				getCount(node * 2 + 1, mid + 1, end, left, right);
	}
}

public class Main {

	private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	private int N, M;
	private Segtree segtree;
	private StringBuilder sb;

	public static void main(String[] args) throws IOException {
		new Main().solution();
	}

	private void solution() throws IOException {
		init();
		calcResult();
		printResult();
		bw.close();
	}

	private void init() throws IOException {
		StringTokenizer st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		segtree = new Segtree(N);
		sb = new StringBuilder();
	}

	private void calcResult() throws IOException {
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			int O = Integer.parseInt(st.nextToken());
			int S = Integer.parseInt(st.nextToken());
			int T = Integer.parseInt(st.nextToken());
			
			if (O == 0) {
				segtree.update(1, 1, N, S, T);
			} else {
				sb.append(segtree.getCount(1, 1, N, S, T)).append("\n");
			}
		}
    }

	private void printResult() throws IOException {
		bw.write(sb.toString());
		bw.flush();
	}
}


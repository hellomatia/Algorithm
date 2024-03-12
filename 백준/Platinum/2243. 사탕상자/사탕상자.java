import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	class Segtree {
		
		int[] tree;
		
		Segtree(int n) {
			int treeSize = 1 << (int) (Math.ceil(Math.log(n) / Math.log(2)) + 1);
			this.tree = new int[treeSize];
		}
		
		void update(int node, int start, int end, int index, int diff) {
			if (index < start || end < index) return;
			
			tree[node] += diff;
			
			if (start != end) {
				int mid = start + (end - start) / 2;
				update(node * 2, start, mid, index, diff);
				update(node * 2 + 1, mid + 1, end, index, diff);
			}
		}
		
		int sum(int node, int start, int end, int left, int right) {
			if (right < start || end < left) return 0;
			if (left <= start && end <= right) return tree[node];
			
			int mid = start + (end - start) / 2;
			return sum(node * 2, start, mid, left, right) +
					sum(node * 2 + 1, mid + 1, end, left, right);
		}
	}

	private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	private int n;
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
		n = Integer.parseInt(bf.readLine());
		segtree = new Segtree(1_000_000);
		sb = new StringBuilder();
	}

	private void calcResult() throws IOException {
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			int A = Integer.parseInt(st.nextToken());
			if (A == 1) {
				int B = Integer.parseInt(st.nextToken());
				int candy = calcKthCandy(B);
				sb.append(candy).append("\n");
				segtree.update(1, 1, 1_000_000, candy, -1);
			} else {
				int B = Integer.parseInt(st.nextToken());
				int C = Integer.parseInt(st.nextToken());
				segtree.update(1, 1, 1_000_000, B, C);
			}
		}
	}
	
	private int calcKthCandy(int k) {
		int start = 1;
		int end = 1_000_000;
		
		while (start <= end) {
			int mid = start + (end - start) / 2;
			
			int count = segtree.sum(1, 1, 1_000_000, 1, mid);
			
			if (count < k) {
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}
		
		return start;
	}

	private void printResult() throws IOException {
		bw.write(sb.toString());
		bw.flush();
	}
}

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;


public class Main {
	
	class MergeSortTree{
		
		int tree[][];
		
		
		MergeSortTree(int[] arr) {
			int treeSize = 1 << (int) Math.ceil(Math.log(arr.length) / Math.log(2) + 1);
			this.tree = new int[treeSize][];
			init(1, 1, arr.length - 1, arr);
		}
		
		void init(int node, int start, int end, int[] arr) {
			if (start == end) {
				tree[node] = new int[] {arr[start]};
				return;
			}
			
			int mid = (start + end) >>> 1;
			init(node * 2, start, mid, arr);
			init(node * 2 + 1, mid + 1, end, arr);
			
			tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
		}
		
		int[] merge(int[] arr1, int[] arr2) {
			int[] result = new int[arr1.length + arr2.length];
			
			int index = 0;
			int index1 = 0;
			int index2 = 0;
			
			while (index < result.length) {
				if (index1 == arr1.length) {
					result[index++] = arr2[index2++];
					continue;
				} else if (index2 == arr2.length) {
					result[index++] = arr1[index1++];
					continue;
				}
				
				if (arr1[index1] <= arr2[index2]) {
					result[index++] = arr1[index1++];
				} else {
					result[index++] = arr2[index2++];
				}
			}
			return result;
		}
		
		int query(int node, int start, int end, int left, int right, int k) {
			if (right < start || end < left) {
				return 0;
			}
			
			if (left <= start && end <= right) {
				return getKCount(tree[node], k);
			}
			
			int mid = (start + end) >>> 1;
			return query(node * 2, start, mid, left, right, k) + 
					query(node * 2 + 1, mid + 1, end, left, right, k);
		}
		
		int getKCount(int[] arr, int k) {
			int start = 0;
			int end = arr.length;
			
			while (start < end) {
				int mid = (start + end) >>> 1;
				
				if (arr[mid] <= k) {
					start = mid + 1;
				} else {
					end = mid;
				}
			}
			return start;
		}
	}

	private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	private static final int MOD = 1_000_000_007;
	
	private int N, M, C;
	private int[] colors;
	private int[] arr;
	private int[][] range;
	private List<Integer>[] nearNode;
	private int id;
	private MergeSortTree mergeSortTree;

	public static void main(String[] args) throws IOException {
		new Main().solution();
	}

	private void solution() throws IOException {
		init();
		printResult(calcResult());
		bw.close();
	}

	private void init() throws IOException {
		StringTokenizer st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		colors = new int[N + 1];
		st = new StringTokenizer(bf.readLine());
		for (int i = 1; i <= N; i++) {
			colors[i] = Integer.parseInt(st.nextToken());
		}
		
		nearNode = new List[N + 1];
		for (int i = 1; i <= N; i++) {
			nearNode[i] = new LinkedList<>();
		}
		
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(bf.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			nearNode[from].add(to);
			nearNode[to].add(from);
		}
		arr = new int[N + 1];
		range = new int[N + 1][2];
		dfs(1, 1);
		
		mergeSortTree = new MergeSortTree(arr);
	}
	
	private void dfs(int now, int from) {
		range[now][0] = ++id;
		arr[id] = colors[now];
		for (int next : nearNode[now]) {
			if (next == from) continue;
			dfs(next, now);
		}
		range[now][1] = id;
	}

	private int calcResult() throws IOException {
		
		int sum = 0;
		
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			int num = Integer.parseInt(st.nextToken());
			int color = Integer.parseInt(st.nextToken());
			
			sum += mergeSortTree.query(1, 1, N, range[num][0], range[num][1], color);
			
			sum %= MOD;
		}
		
		return sum;
	}

	private void printResult(int result) throws IOException {
		bw.write(result + "\n");
		bw.flush();
	}
}

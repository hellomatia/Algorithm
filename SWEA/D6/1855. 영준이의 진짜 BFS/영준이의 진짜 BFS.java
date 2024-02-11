import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	
	private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	private int N, maxDepth;
	private int[][] parent;
	private List<Integer>[] childList;
	private int[] depth;
	private Queue<Integer> queue;
	
	public static void main(String[] args) throws IOException {
		new Solution().solution();
	}
	
	private void solution() throws IOException {
		int T = Integer.parseInt(bf.readLine());
		for (int testCase = 1; testCase <= T; testCase++) {
			init();
			calcResult();
			printResult(testCase, calcResult());
			bw.flush();
		}
		bw.close();
	}
	
	private void init() throws IOException {
		N = Integer.parseInt(bf.readLine());
		StringTokenizer st = new StringTokenizer(bf.readLine());
		maxDepth = (int) Math.ceil(Math.log(N)/Math.log(2)) + 1;
		parent = new int[N + 1][maxDepth + 1];
		childList = new List[N + 1];
		for (int i = 1; i <= N; i++) {
			childList[i] = new LinkedList<>();
		}
		for (int i = 2; i <= N; i++) {
			int p = Integer.parseInt(st.nextToken());
			parent[i][0] = p;
			childList[p].add(i);
		}
		depth = new int[N + 1];
	}
	
	private long calcResult() {
		long result = 0;
		
		bfs();
		initParent();
		
		int start = queue.poll();
		while (!queue.isEmpty()) {
			int target = queue.poll();
			result += calcMinDist(start, target);
			start = target;
		}
		
		return result;
	}
	
	private void bfs() {
		queue = new ArrayDeque<>();
		Queue<Integer> tempQ = new ArrayDeque<>();
		tempQ.offer(1);
		int depthCount = 0;
		while (!tempQ.isEmpty()) {
			int size = tempQ.size();
			for (int i = 0; i < size; i++) {
				int now = tempQ.poll();
				depth[now] = depthCount;
				for (int child : childList[now]) {
					tempQ.offer(child);
				}
				queue.offer(now);
			}
			depthCount++;
		}
	}
	
	private long calcMinDist(int n1, int n2) {
		if (depth[n2] < depth[n1]) {
			int temp = n1;
			n1 = n2;
			n2 = temp;
		}
		
		// 높이 조정
		long diffDepth = depth[n2] - depth[n1];
		for (int i = 0; i <= maxDepth; i++) {
			if (((1 << i) & diffDepth) > 0) {
				n2 = parent[n2][i];
			}
		}
		
		if (n1 == n2) {
			return diffDepth;
		}

		long dist = 1;
		for (int i = maxDepth; i >= 0; i--) {
			if (parent[n1][i] != parent[n2][i]) {
				n1 = parent[n1][i];
				n2 = parent[n2][i];
				dist += 1 << i;
			}
		}
		
		dist *= 2;
		
		return diffDepth + dist;
	}
	
	private void initParent() {
		for (int i = 2; i <= N; i++) {
			for (int j = 1; j <= maxDepth; j++) {
				parent[i][j] = parent[parent[i][j - 1]][j - 1];
			}
		}
	}

	private void printResult(int testCase, long result) throws IOException {
		bw.write("#" + testCase + " ");
		bw.write(result + "\n");
	}
}

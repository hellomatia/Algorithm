import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution {

	class Count {
		Set<Integer> set = new HashSet<>();

		public void add(int num) {
			set.add(num);
		}

		public boolean contains(int num) {
			return set.contains(num);
		}

		public int getValue() {
			return set.size();
		}
	}

	private final BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	private final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	private int N, M;
	private List<Integer>[] adj;
	private List<Integer>[] reverseAdj;

	public static void main(String[] args) throws IOException {
		new Solution().solution();
	}

	private void solution() throws IOException {
		int T = Integer.parseInt(bf.readLine());
		for (int i = 1; i <= T; i++) {
			init();
			printResult(i, calcResult());
		}
	}

	private void init() throws IOException {
		N = Integer.parseInt(bf.readLine());
		M = Integer.parseInt(bf.readLine());

		adj = new List[N + 1];
		reverseAdj = new List[N + 1];

		for (int i = 1; i <= N; i++) {
			adj[i] = new ArrayList<>();
			reverseAdj[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			adj[a].add(b);
			reverseAdj[b].add(a);
		}
	}

	private int calcResult() {
		int result = 0;

		for (int i = 1; i <= N; i++) {
			if (bfs(i) == N - 1) {
				result++;
			}
		}
		return result;
	}

	private int bfs(int start) {
		Queue<Integer> queue = new ArrayDeque<>();
		Count count = new Count();

		queue.offer(start);
		while (!queue.isEmpty()) {
			int now = queue.poll();
			for (int next : adj[now]) {
				if (!count.contains(next)) {
					count.add(next);
					queue.offer(next);
				}
			}
		}

		queue.offer(start);
		while (!queue.isEmpty()) {
			int now = queue.poll();
			for (int next : reverseAdj[now]) {
				if (!count.contains(next)) {
					count.add(next);
					queue.offer(next);
				}
			}
		}

		return count.getValue();
	}

	private void printResult(int testCase, int result) throws IOException {
		bw.write("#" + testCase + " ");
		bw.write(result + "\n");
		bw.flush();
	}
}
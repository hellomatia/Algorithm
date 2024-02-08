
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	private int n, m;
	private List<Integer>[] nearNodes;
	private int maxDepth;
	private int[][] parent;
	private int[] depth;

	public static void main(String[] args) throws IOException {
		new Main().solution();
	}

	private void solution() throws IOException {
		init();
		m = Integer.parseInt(bf.readLine());
		int result = 0;
		int from = Integer.parseInt(bf.readLine());
		for (int i = 0; i < m - 1; i++) {
			int to = Integer.parseInt(bf.readLine());
			result += calcResult(from, to);
			from = to;
		}
		printResult(result);
		bw.flush();
		bw.close();
	}

	private void init() throws IOException {
		n = Integer.parseInt(bf.readLine());
		nearNodes = new List[n + 1];
		for (int i = 1; i <= n; i++) {
			nearNodes[i] = new LinkedList<>();
		}
		for (int i = 0; i < n - 1; i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			nearNodes[from].add(to);
			nearNodes[to].add(from);
		}
		maxDepth = (int) Math.ceil(Math.log(n) / Math.log(2)) + 1;
		parent = new int[n + 1][maxDepth];
		depth = new int[n + 1];
		initTree(1, 1);
		initParent();
	}

	private void initTree(int now, int from) {
		for (int to : nearNodes[now]) {
			if (to == from)
				continue;
			parent[to][0] = now;
			depth[to] = depth[now] + 1;
			initTree(to, now);
		}
	}

	private void initParent() {
		for (int i = 1; i < maxDepth; i++) {
			for (int j = 2; j <= n; j++) {
				parent[j][i] = parent[parent[j][i - 1]][i - 1];
			}
		}
	}

	private int calcResult(int n1, int n2) {
		if (depth[n1] > depth[n2]) {
			int temp = n1;
			n1 = n2;
			n2 = temp;
		}

		int diffDepth = depth[n2] - depth[n1];
		for (int i = 0; i < maxDepth; i++) {
			if (((1 << i) & diffDepth) > 0) {
				n2 = parent[n2][i];
			}
		}

		if (n1 == n2)
			return diffDepth;

		int count = 1;
		for (int i = maxDepth - 1; i >= 0; i--) {
			if (parent[n1][i] != parent[n2][i]) {
				n1 = parent[n1][i];
				n2 = parent[n2][i];
				count += 1 << i;
			}
		}

		return diffDepth + (2 * count);
	}

	private void printResult(int result) throws IOException {
		bw.write(result + "\n");
	}
}

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Point {
	
	int num;
	int x;
	int y;
	int z;
	
	Point(int num, int x, int y, int z) {
		this.num = num;
		this.x = x;
		this.y = y;
		this.z = z;
	}
}

class Edge implements Comparable<Edge> {
	
	int from;
	int to;
	int cost;

	Edge(int from, int to, int cost) {
		this.from = from;
		this.to = to;
		this.cost = cost;
	}

	@Override
	public int compareTo(Edge o) {
		return cost - o.cost;
	}
}

public class Main {

	private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	private int N;
	private Point[] points;
	private int[] parent;
	
	public static void main(String[] args) throws IOException {
		new Main().solution();
	}

	private void solution() throws IOException {
		init();
		printResult(calcResult());
		bw.close();
	}

	private void init() throws IOException {
		N = Integer.parseInt(bf.readLine());
		points = new Point[N];
		parent = new int[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			points[i] = new Point(
					i,
					Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()));
			parent[i] = i;
		}
	}

	private int calcResult() {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		
		Arrays.sort(points, (o1, o2) -> o1.x - o2.x);
		for (int i = 1; i < N; i++) {
			int from = points[i - 1].num;
			int to = points[i].num;
			int cost = Math.abs(points[i - 1].x - points[i].x);
			pq.offer(new Edge(from, to, cost));
		}
		
		Arrays.sort(points, (o1, o2) -> o1.y - o2.y);
		for (int i = 1; i < N; i++) {
			int from = points[i - 1].num;
			int to = points[i].num;
			int cost = Math.abs(points[i - 1].y - points[i].y);
			pq.offer(new Edge(from, to, cost));
		}
		
		Arrays.sort(points, (o1, o2) -> o1.z - o2.z);
		for (int i = 1; i < N; i++) {
			int from = points[i - 1].num;
			int to = points[i].num;
			int cost = Math.abs(points[i - 1].z - points[i].z);
			pq.offer(new Edge(from, to, cost));
		}
		
		int result = 0;
		
		while (!pq.isEmpty()) {
			Edge now = pq.poll();
			
			if (find(now.from) == find(now.to)) continue;
			
			union(now.from, now.to);
			result += now.cost;
		}
		
		return result;
	}
	
	private void union(int x, int y) {
		x = find(x);
		y = find(y);
		if (x == y) return;
		if (x < y) parent[y] = x;
		else parent[x] = y;
	}
	
	private int find(int x) {
		if (parent[x] == x) return x;
		else return parent[x] = find(parent[x]);
	}

	private void printResult(int result) throws IOException {
		bw.write(result + "\n");
		bw.flush();
	}
}

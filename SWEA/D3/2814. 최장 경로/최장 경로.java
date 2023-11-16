
import java.io.*;
import java.util.*;

public class Solution {
	
	private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static int T, N, M;
	private static int maxLength;
	private static List<Integer>[] nodes;
	private static boolean[] visited;

	public static void main(String args[]) throws Exception {
		solution();
	}
	
	private static void solution() throws IOException {
		initT();
		for (int testCase = 1; testCase <= T; testCase++) {
			initNandM();
			initNodes();
			findMaxLength();
			printResult(testCase, maxLength);
		}
		bw.flush();
		bw.close();
	}
	
	private static void initT() throws IOException {
		T = Integer.parseInt(bf.readLine());
	}
	
	private static void initNandM() throws IOException {
		StringTokenizer st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
	}
	
	private static void initNodes() throws IOException {
		nodes = new List[N + 1];
		for (int i = 1; i <= N; i++) {
			nodes[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			nodes[x].add(y);
			nodes[y].add(x);
		}
		visited = new boolean[N + 1];
	}
	
	private static void findMaxLength() {
		maxLength = 0;
		for (int i = 1; i <= N; i++) {
			visited[i] = true;
			exploreNode(i, 1);
			visited[i] = false;
		}
	}
	
	private static void exploreNode(int start, int count) {
		maxLength = Math.max(maxLength, count);
		for (int i = 0; i < nodes[start].size(); i++) {
			int nextNode = nodes[start].get(i);	
			if (visited[nextNode]) {
				continue;
			}
			visited[nextNode] = true;
			exploreNode(nextNode, count + 1);
			visited[nextNode] = false;
		}
	}
	
	private static void printResult(int testCase, int result) throws IOException {
		bw.write("#"+testCase + " " + result);
		bw.write("\n");
	}
}

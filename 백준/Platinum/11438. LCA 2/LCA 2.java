import java.io.*;
import java.util.*;

public class Main {

    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int N, M;
    private static List<Integer>[] linkedNodes;
    private static int depthMax;
    private static int[][] tree;
    private static int[] depth;


    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();

        M = Integer.parseInt(bf.readLine());
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            printResult(calcResult(
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())));
        }

        bw.flush();
        bw.close();
    }

    private void init() throws IOException {
        N = Integer.parseInt(bf.readLine());
        linkedNodes = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            linkedNodes[i] = new ArrayList<>();
        }

        for (int i = 1; i < N; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            linkedNodes[node1].add(node2);
            linkedNodes[node2].add(node1);
        }

        depthMax = (int) Math.ceil(Math.log(N)/Math.log(2)) + 1;
        tree = new int[N + 1][depthMax];
        depth = new int[N + 1];

        initTree();
        fillTree();
    }

    private void initTree() {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);

        boolean[] visited = new boolean[N + 1];
        visited[1] = true;

        int depthCount = 1;
        int depthNodeCount = 1;
        int depthNextNodeCount = 0;

        while(!queue.isEmpty()) {
            int parent = queue.poll();

            if (depthNodeCount == 0) {
                depthCount++;
                depthNodeCount = depthNextNodeCount;
                depthNextNodeCount = 0;
            }
            depth[parent] = depthCount;
            depthNodeCount--;

            for (int child : linkedNodes[parent]) {
                if (visited[child]) {
                    continue;
                }
                visited[child] = true;
                depthNextNodeCount++;
                tree[child][0] = parent;
                queue.offer(child);
            }
        }
    }

    private void fillTree() {
        for (int i = 1; i < depthMax; i++) {
            for (int j = 1; j <= N; j++) {
                tree[j][i] = tree[tree[j][i - 1]][i - 1];
            }
        }
    }

    private int calcResult(int node1, int node2) {
        if (depth[node1] < depth[node2]) {
            int temp = node1;
            node1 = node2;
            node2 = temp;
        }

        for (int i = depthMax - 1; i >= 0; i--) {
            if (Math.pow(2, i) <= depth[node1] - depth[node2]) {
                node1 = tree[node1][i];
            }
        }

        if (node1 == node2) {
            return node1;
        }

        for (int i = depthMax - 1; i >= 0; i--) {
            if (tree[node1][i] != tree[node2][i]) {
                node1 = tree[node1][i];
                node2 = tree[node2][i];
            }
        }

        return tree[node1][0];
    }

    private void printResult(int result) throws IOException {
        bw.write(result + "\n");
    }
}

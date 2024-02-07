import java.io.*;
import java.util.*;

class Node {
    int num;
    int cost;

    public Node(int num, int cost) {
        this.num = num;
        this.cost = cost;
    }
}

public class Main {

    private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private int N, M;
    private List<Node>[] nearNodes;
//    private boolean[] visited;
    private int maxDepth;
    private int[] depth;
    private int[][] parent;
    private int[] costs;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        M = Integer.parseInt(bf.readLine());
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            printResult(calcResult(start, end));
        }

        bw.flush();
        bw.close();
    }

    private void init() throws IOException {
        N = Integer.parseInt(bf.readLine());
        nearNodes = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            nearNodes[i] = new LinkedList<>();
        }
        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            nearNodes[node1].add(new Node(node2, cost));
            nearNodes[node2].add(new Node(node1, cost));
        }
//        visited = new boolean[N + 1];
        depth = new int[N + 1];
        maxDepth = (int) Math.ceil(Math.log(N)/Math.log(2)) + 1;
        parent = new int[N + 1][maxDepth];
//        for (int i = 0; i <= N; i++) {
//            Arrays.fill(parent[i], -1);
//        }
        costs = new int[N + 1];
        initTree(1, 1,0);
        initParent();
    }

    private void initTree(int now, int prev, int cost) {
        costs[now] = cost;
//        visited[now] = true;
        for (Node child : nearNodes[now]) {
            if (child.num == prev) {
                continue;
            }
            depth[child.num] = depth[now] + 1;
            parent[child.num][0] = now;
            initTree(child.num, now, cost + child.cost);
        }
    }

    private void initParent() {
        for (int i = 1; i < maxDepth; i++) {
            for (int j = 2; j <= N; j++) {
                parent[j][i] = parent[parent[j][i - 1]][i - 1];
            }
        }
    }

    private int calcResult(int n1, int n2) {
        int start = n1;
        int end = n2;

        if (depth[n1] > depth[n2]) {
            int temp = n1;
            n1 = n2;
            n2 = temp;
        }

        int diffDepth = depth[n2] - depth[n1];
        for (int i = maxDepth - 1; i >= 0; i--) {
            if (((1 << i) & diffDepth) > 0) {
                n2 = parent[n2][i];
            }
        }

        for (int i = maxDepth - 1; i >= 0; i--) {
            if (parent[n1][i] != parent[n2][i]) {
                n1 = parent[n1][i];
                n2 = parent[n2][i];
            }
        }

        if (n1 != n2) {
            n1 = parent[n1][0];
        }

        return costs[start] + costs[end] - (costs[n1] * 2);
    }

    private void printResult(int result) throws IOException {
        bw.write(result + "\n");
    }
}

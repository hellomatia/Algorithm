import java.io.*;
import java.util.*;
class Node {
    int num;
    long cost;

    Node(int num, long cost) {
        this.num = num;
        this.cost = cost;
    }
}

public class Main {

    private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private int N, M;
    private List<Node>[] nearNodes;
    private int maxDepth;
    private int[] depth;
    private int[][] parent;
    private long[] costs;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        M = Integer.parseInt(bf.readLine());
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int command = Integer.parseInt(st.nextToken());
            if (command == 1) {
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                printResult(calcMinCost(from, to, lca(from, to)));
            } else if (command == 2) {
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int k = Integer.parseInt(st.nextToken());
                printResult(calcKthNodeNum(from, to, lca(from, to), k));
            }
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
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            long cost = Long.parseLong(st.nextToken());
            nearNodes[from].add(new Node(to, cost));
            nearNodes[to].add(new Node(from, cost));
        }
        maxDepth = (int) Math.ceil(Math.log(N) / Math.log(2)) + 1;
        depth = new int[N + 1];
        parent = new int[N + 1][maxDepth];
        costs = new long[N + 1];
        initTree(1, 1);
        initParent();
    }

    private void initTree(int now, int from) {
        for (Node to : nearNodes[now]) {
            if (to.num == from) continue;
            depth[to.num] = depth[now] + 1;
            parent[to.num][0] = now;
            costs[to.num] = costs[now] + to.cost;
            initTree(to.num, now);
        }
    }

    private void initParent() {
        for (int i = 1; i < maxDepth; i++) {
            for (int j = 2; j <= N; j++) {
                parent[j][i] = parent[parent[j][i - 1]][i - 1];
            }
        }
    }

    private int lca(int n1, int n2) {
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

        if (n1 == n2) return n1;

        for (int i = maxDepth - 1; i >= 0; i--) {
            if (parent[n1][i] != parent[n2][i]) {
                n1 = parent[n1][i];
                n2 = parent[n2][i];
            }
        }

        return parent[n1][0];
    }

    private long calcMinCost(int from, int to, int mid) {
        return costs[from] + costs[to] - (2 * costs[mid]);
    }

    private int calcKthNodeNum(int from, int to, int mid, int k) {
        k--;
        int fromToMidDiff = depth[from] - depth[mid];
        if (fromToMidDiff >= k) {
            for (int i = 0; i < maxDepth; i++) {
                if (((1 << i) & k) > 0) {
                    from = parent[from][i];
                }
            }
            return from;
        } else {
            k -= fromToMidDiff;
            int kToTo = depth[to] - depth[mid] - k;
            for (int i = 0; i < maxDepth; i++) {
                if (((1 << i) & kToTo) > 0) {
                    to = parent[to][i];
                }
            }
            return to;
        }
    }

    private void printResult(long result) throws IOException {
        bw.write(result+ "\n");
    }
}

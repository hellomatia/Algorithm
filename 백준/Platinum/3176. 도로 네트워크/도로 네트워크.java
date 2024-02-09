import java.io.*;
import java.util.*;

class Node {
    int num;
    int cost;

    Node(int num, int cost) {
        this.num = num;
        this.cost = cost;
    }
}

public class Main {

    private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private int N, M;
    private List<Node>[] nearNodes;
    private int[] depth;
    private int[][] parent;
    private int[][] minCost;
    private int[][] maxCost;
    private int maxDepth;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        M = Integer.parseInt(bf.readLine());
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            calcResult(from, to);
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
            int cost = Integer.parseInt(st.nextToken());
            nearNodes[from].add(new Node(to, cost));
            nearNodes[to].add(new Node(from, cost));
        }
        depth = new int[N + 1];
        maxDepth = (int) Math.ceil(Math.log(N) / Math.log(2)) + 1;
        parent = new int[N + 1][maxDepth];
        minCost = new int[N + 1][maxDepth];
        maxCost = new int[N + 1][maxDepth];

        initTree(1, 1);
        initParent();
        initMin();
        initMax();
    }

    private void initTree(int now, int from) {
        for (Node to : nearNodes[now]) {
            if (to.num == from) continue;
            depth[to.num] = depth[now] + 1;
            parent[to.num][0] = now;
            minCost[to.num][0] = to.cost;
            maxCost[to.num][0] = to.cost;
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

    private void initMin() {
        for (int i = 1; i < maxDepth; i++) {
            for (int j = 2; j <= N; j++) {
                minCost[j][i] = Math.min(minCost[parent[j][i - 1]][i - 1] , minCost[j][i - 1]);
            }
        }
    }

    private void initMax() {
        for (int i = 1; i < maxDepth; i++) {
            for (int j = 2; j <= N; j++) {
                maxCost[j][i] = Math.max(maxCost[parent[j][i - 1]][i - 1] , maxCost[j][i - 1]);
            }
        }
    }

    private void calcResult(int n1, int n2) throws IOException {

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        if (depth[n1] > depth[n2]) {
            int temp = n1;
            n1 = n2;
            n2 = temp;
        }

        int diffDepth = depth[n2] - depth[n1];

        for (int i = 0; i < maxDepth; i++) {
            if (((1 << i) & diffDepth) > 0) {
                min = Math.min(min, minCost[n2][i]);
                max = Math.max(max, maxCost[n2][i]);
                n2 = parent[n2][i];
            }
        }

        if (n1 == n2) {
            printResult(min, max);
            return;
        }

        for (int i = maxDepth - 1; i >= 0; i--) {
            if (parent[n1][i] != parent[n2][i]) {
                min = Math.min(min, minCost[n1][i]);
                max = Math.max(max, maxCost[n1][i]);

                min = Math.min(min, minCost[n2][i]);
                max = Math.max(max, maxCost[n2][i]);

                n1 = parent[n1][i];
                n2 = parent[n2][i];
            }
        }

        min = Math.min(min, minCost[n1][0]);
        max = Math.max(max, maxCost[n1][0]);

        min = Math.min(min, minCost[n2][0]);
        max = Math.max(max, maxCost[n2][0]);

        printResult(min, max);
    }

    private void printResult(long min, long max) throws IOException {
        bw.write(min + " " + max + "\n");
    }
}

import java.io.*;
import java.util.*;

public class Main {

    private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private int N, M;
    private int[] candyType;
    private List<Integer>[] nearNodes;
    private int maxDepth;
    private int[][] parent;
    private int[] depth;
    private boolean[][][] canBuy;
    private boolean[] canBuyFirstCandy;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        M = Integer.parseInt(bf.readLine());
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int from = Integer.parseInt(st.nextToken());
        int type = Integer.parseInt(st.nextToken());
        if (canBuyFirstCandy[type]) {
            printResult("PLAY");
        } else {
            printResult("CRY");
        }
        for (int i = 0; i < M - 1; i++) {
            st = new StringTokenizer(bf.readLine());
            int to = Integer.parseInt(st.nextToken());
            type = Integer.parseInt(st.nextToken());
            printResult(calcResult(from, to, type));
            from = to;
        }
        bw.flush();
        bw.close();
    }

    private void init() throws IOException {
        N = Integer.parseInt(bf.readLine());

        StringTokenizer st = new StringTokenizer(bf.readLine());
        canBuyFirstCandy = new boolean[6];
        candyType = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            int type = Integer.parseInt(st.nextToken());
            candyType[i] = type;
            canBuyFirstCandy[type] = true;
        }

        nearNodes = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            nearNodes[i] = new LinkedList<>();
        }
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(bf.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            nearNodes[from].add(to);
            nearNodes[to].add(from);
        }

        maxDepth = (int) Math.ceil(Math.log(N) / Math.log(2)) + 1;
        canBuy = new boolean[6][N + 1][maxDepth];
        parent = new int[N + 1][maxDepth];
        depth = new int[N + 1];
        initTree(1, 1);
        initParent();
        initCanBuy();
    }

    private void initTree(int now, int from) {
        canBuy[candyType[now]][now][0] = true;
        for (int to : nearNodes[now]) {
            if (to == from) continue;
            depth[to] = depth[now] + 1;
            parent[to][0] = now;
            canBuy[candyType[now]][to][0] = true;
            initTree(to, now);
        }
    }

    private void initParent() {
        for (int i = 1; i < maxDepth; i++) {
            for (int j = 2; j <= N; j++) {
                parent[j][i] = parent[parent[j][i - 1]][i - 1];
            }
        }
    }

    private void initCanBuy() {
        for (int type = 1; type <= 5; type++) {
            for (int i = 1; i < maxDepth; i++) {
                for (int j = 1; j <= N; j++) {
                    canBuy[type][j][i] = canBuy[type][parent[j][i - 1]][i - 1] || canBuy[type][j][i - 1];
                }
            }
        }
    }

    private String calcResult(int n1, int n2, int type) {
        if (candyType[n1] == type || candyType[n2] == type) {
            return "PLAY";
        }

        if (depth[n1] > depth[n2]) {
            int temp = n1;
            n1 = n2;
            n2 = temp;
        }

        int diffDepth = depth[n2] - depth[n1];
        for (int i = 0; i < maxDepth; i++) {
            if (((1 << i) & diffDepth) > 0) {
                if (canBuy[type][n2][i]) {
                    return "PLAY";
                }
                n2 = parent[n2][i];
            }
        }

        if (n1 == n2) {
            return "CRY";
        }

        for (int i = maxDepth - 1; i >= 0; i--) {
            if (parent[n1][i] != parent[n2][i]) {
                if (canBuy[type][n1][i] || canBuy[type][n2][i]) {
                    return "PLAY";
                }
                n1 = parent[n1][i];
                n2 = parent[n2][i];
            }
        }

        if (candyType[parent[n1][0]] == type) {
            return "PLAY";
        }
        return "CRY";
    }

    private void printResult(String result) throws IOException {
        bw.write(result+ "\n");
    }
}

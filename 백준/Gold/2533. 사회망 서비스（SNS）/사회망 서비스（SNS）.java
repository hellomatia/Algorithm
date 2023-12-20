import java.io.*;
import java.util.*;

class Node {
    int num;
    List<Integer> linkedNodes = new ArrayList<>();

    public Node(int num) {
        this.num = num;
    }

    public void addLinkedNode(int num) {
        linkedNodes.add(num);
    }
}

public class Main {

    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int N;
    private static Node[] tree;
    private static boolean[] visited;
    private static int[][] dp;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        calcMinEarlyAdopter(1);
        printResult(Math.min(dp[1][0], dp[1][1]));
        bw.flush();
        bw.close();
    }

    private void init() throws IOException {
        N = Integer.parseInt(bf.readLine());
        tree = new Node[N + 1];
        for (int i = 1; i <= N; i++) {
            tree[i] = new Node(i);
        }
        for (int i = 1; i < N; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            tree[u].addLinkedNode(v);
            tree[v].addLinkedNode(u);
        }
        dp = new int[N + 1][2];
        visited = new boolean[N + 1];
    }

    private void calcMinEarlyAdopter(int now) {
        visited[now] = true;

        dp[now][0] = 0;
        dp[now][1] = 1;

        for (int next : tree[now].linkedNodes) {
            if (visited[next]) {
                continue;
            }
            calcMinEarlyAdopter(next);
            dp[now][0] += dp[next][1];
            dp[now][1] += Math.min(dp[next][0], dp[next][1]);
        }
    }

    private void printResult(int result) throws IOException {
        bw.write(result + "\n");
    }
}
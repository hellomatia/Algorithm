import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private final static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private final static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private List<Node>[] adj;
    private int n, m;
    private int ans;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            printAns(calcAns(from, to));
        }
    }

    private void init() throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        adj = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(bf.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            adj[node1].add(new Node(node2, weight));
            adj[node2].add(new Node(node1, weight));
        }
    }

    private String calcAns(int from, int to) {
        ans = Integer.MAX_VALUE;
        dfs(from, from, to, 0);
        return ans + "";
    }

    private void dfs(int now, int from, int target, int weight) {
        if (now == target) {
            ans = Math.min(ans, weight);
            return;
        }

        for (Node to : adj[now]) {
            if (to.getNum() == from) {
                continue;
            }
            dfs(to.getNum(), now, target, weight + to.getWeight());
        }
    }

    private void printAns(String ans) throws IOException {
        bw.write(ans + "\n");
        bw.flush();
    }

    private class Node {
        private final int num;
        private final int wight;

        public Node(int num, int wight) {
            this.num = num;
            this.wight = wight;
        }

        public int getNum() {
            return num;
        }

        public int getWeight() {
            return wight;
        }
    }
}
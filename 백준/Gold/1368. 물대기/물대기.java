import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static final BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private int N;
    private int[] cost;
    private PriorityQueue<Edge> pq;
    private int[] parent;
    private long ans;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        printAns(calcAns());
    }

    private void init() throws IOException {
        N = Integer.parseInt(bf.readLine());

        cost = new int[N + 1];
        pq = new PriorityQueue<>();
        for (int i = 1; i <= N; i++) {
            cost[i] = Integer.parseInt(bf.readLine());
            pq.offer(new Edge(0, i, cost[i]));
        }

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for (int j = 1; j <= N; j++) {
                int c = Integer.parseInt(st.nextToken());
                if (i >= j) {
                    continue;
                }
                pq.offer(new Edge(i, j, c));
                ans += c;
            }
        }

        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }
    }

    private String calcAns() {
        int ans = 0;
        while (!pq.isEmpty()) {
            Edge now = pq.poll();
            if (union(now.from, now.to)) {
                ans += now.cost;
            }
        }
        return ans + "";
    }

    private boolean union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x == y) { return false; }
        if (x < y) {
            parent[y] = x;
        } else {
            parent[x] = y;
        }
        return true;
    }

    private int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    private void printAns(String ans) throws IOException {
        bw.write(ans + "\n");
        bw.flush();
    }

    static class Edge implements Comparable<Edge> {
        int from, to;
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
}

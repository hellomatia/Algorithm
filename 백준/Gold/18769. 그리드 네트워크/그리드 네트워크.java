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

    private int T;
    private int R, C;
    private PriorityQueue<Edge> pq;
    private int[] parent;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        T = Integer.parseInt(bf.readLine());
        for (int t = 1; t <= T; t++) {
            init();
            printAns(calcAns());
        }
    }

    private void init() throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        pq = new PriorityQueue<>();
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < C - 1; j++) {
                int cost = Integer.parseInt(st.nextToken());
                int from = getId(i, j);
                int to = getId(i, j + 1);
                pq.offer(new Edge(from, to, cost));
            }
        }
        for (int i = 0; i < R - 1; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < C; j++) {
                int cost = Integer.parseInt(st.nextToken());
                int from = getId(i, j);
                int to = getId(i + 1, j);
                pq.offer(new Edge(from, to, cost));
            }
        }
    }

    private int getId(int x, int y) {
        return x * C + y;
    }

    private String calcAns() {
        parent = new int[R * C];
        for (int i = 0; i < R * C; i++) {
            parent[i] = i;
        }

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

        if (x == y) {
            return false;
        }
        if (x < y) {
            parent[x] = y;
        } else {
            parent[y] = x;
        }
        return true;
    }

    private int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    private void printAns(String ans) throws IOException {
        bw.write(ans + "\n");
        bw.flush();
    }

    static class Edge implements Comparable<Edge> {
        private int from, to;
        private int cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        public int compareTo(Edge o) {
            if (cost == o.cost) {
                return from - o.from;
            }
            return cost - o.cost;
        }
    }
}

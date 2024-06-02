import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private final static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private final static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private boolean isNotFinished;
    private int[] parent;
    private PriorityQueue<Edge> edges;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        isNotFinished = true;
        for (init(); isNotFinished; init()) {
            printAns(calcAns());
        }
    }

    private void init() throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int p = Integer.parseInt(st.nextToken());
        if (p == 0) {
            isNotFinished = false;
            return;
        }
        int r = Integer.parseInt(st.nextToken());

        parent = new int[p + 1];
        for (int i = 1; i <= p; i++) {
            parent[i] = i;
        }

        edges = new PriorityQueue<>();
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(bf.readLine());
            int to = Integer.parseInt(st.nextToken());
            int from = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            Edge edge = new Edge(from, to, weight);
            edges.add(edge);
        }

        bf.readLine();
    }

    private String calcAns() {
        int ans = 0;
        while (!edges.isEmpty()) {
            Edge edge = edges.poll();
            if (union(edge.getFrom(), edge.getTo())) {
                ans += edge.getWeight();
            }
        }
        return ans + "";
    }

    private boolean union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x == y) {
            return false;
        } else if (x < y) {
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

    private static class Edge implements Comparable<Edge> {
        private final int from;
        private final int to;
        private final int weight;

        Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        public int getFrom() {
            return from;
        }

        public int getTo() {
            return to;
        }

        public int getWeight() {
            return weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }
}
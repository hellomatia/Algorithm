import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static final BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private int[] parent;
    private int N, M, K;
    private Line[] lines;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        printAns(calcAns());
    }

    private void init() throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < K; i++) {
            int city = Integer.parseInt(st.nextToken());
            parent[city] = -city;
        }

        lines = new Line[M];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            lines[i] = new Line(from, to, cost);
        }
        Arrays.sort(lines);
    }

    private String calcAns() {
        long ans = 0;
        int cityCount = K;
        for (int i = 0; i < M; i++) {
            if (cityCount == N) {
                break;
            }
            Line now = lines[i];
            int city1 = find(now.from);
            int city2 = find(now.to);
            if (city1 > 0 && city2 > 0) {
                continue;
            } else if (city1 < 0 && city2 < 0) {
                continue;
            }
            if (union(now.from, now.to)) {
                i = -1;
                cityCount++;
                ans += now.cost;
            }
        }
        return ans + "";
    }

    private boolean union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x < 0 && y < 0) {
            return false;
        }
        if (x < y) {
            parent[y] = x;
        } else {
            parent[x] = y;
        }
        return true;
    }

    private int find(int x) {
        if (parent[x] < 0 || parent[x] == x) {
            return parent[x];
        }
        return parent[x] = find(parent[x]);
    }

    private void printAns(String ans) throws IOException {
        bw.write(ans + "\n");
        bw.flush();
    }

    static class Line implements Comparable<Line> {
        int from, to;
        int cost;

        public Line(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Line o) {
            return cost - o.cost;
        }
    }
}

import java.io.*;
import java.util.*;

public class Main {

    class Edge {
        int to, c, f;
        Edge reverse;

        Edge(int to, int c) {
            this.to = to;
            this.c = c;
            f = 0;
        }

        public int residual() {
            return c - f;
        }
    }

    private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private int N, M;
    private int S, T;
    private int[] dirX = {-1, 0, 1, 0};
    private int[] dirY = {0, 1, 0, -1};
    private char[][] map;

    private List<Edge>[] adj;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        printResult(calcResult());
        bw.flush();
        bw.close();
    }

    private void init() throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            String rowValue = bf.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = rowValue.charAt(j);
            }
        }

        adj = new List[getIndex(N, M)];
        for (int i = 0; i < 2 * N * M; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == '#') continue;

                if (map[i][j] == 'K') {
                    S = getIndex(i, j) + 1;
                } else if (map[i][j] == 'H') {
                    T = getIndex(i, j);
                }

                for (int d = 0; d < 4; d++){
                    int nextX = i + dirX[d];
                    int nextY = j + dirY[d];
                    if (!isIn(nextX, nextY) || map[i][j] == '#') continue;
                    initEdge(getIndex(i, j) + 1, getIndex(nextX, nextY));
                }
            }
        }

        for(int i = 0; i < N * M; i++) {
            initEdge(2 * i, 2 * i + 1);
        }
    }

    private int getIndex(int x, int y) {
        return 2 * (x * M + y);
    }

    private boolean isIn(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }

    private void initEdge(int u, int v) {
        Edge e1 = new Edge(v, 1);
        Edge e2 = new Edge(u, 0);

        e1.reverse = e2;
        e2.reverse = e1;

        adj[u].add(e1);
        adj[v].add(e2);
    }

    private int calcResult() {
        if (Math.abs((S / 2 / M) - (T / 2 / M)) + Math.abs((S / 2 % M) - (T / 2 % M)) == 1) {
            return -1;
        }

        int result = 0;
        while (true) {
            List<Edge> prev = new ArrayList<>(Collections.nCopies(2 * N * M, null));
            Queue<Integer> queue = new ArrayDeque<>();
            queue.offer(S);

            while (!queue.isEmpty()) {
                int now = queue.poll();
                for (Edge edge : adj[now]) {
                    if (prev.get(edge.to) != null) continue;
                    if (edge.residual() <= 0) continue;

                    prev.set(edge.to, edge);
                    queue.offer(edge.to);
                }
            }

            if (prev.get(T) == null) break;

            int flow = 1;
            result += flow;
            for (int cur = T; cur != S; cur = prev.get(cur).reverse.to) {
                prev.get(cur).f += flow;
                prev.get(cur).reverse.f -= flow;
            }
        }

        return result;
    }

    public void printResult(long result) throws IOException {
        bw.write(result + "\n");
    }
}
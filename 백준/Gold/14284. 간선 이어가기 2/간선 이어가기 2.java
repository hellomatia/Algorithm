import java.io.*;
import java.util.*;

public class Main {

    class Node {
        int to;
        int dist;

        public Node(int to, int dist) {
            this.to = to;
            this.dist = dist;
        }
    }

    private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private final int INF = 1_000_000_000;

    private int n, m, s, t;
    private List<Node>[] adj;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        printAns(calcAns());
    }

    private void init() throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        adj = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new LinkedList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            adj[a].add(new Node(b, c));
            adj[b].add(new Node(a, c));
        }

        st = new StringTokenizer(bf.readLine());
        s = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
    }

    private int calcAns() {
        int[] minDist = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            minDist[i] = INF;
        }
        boolean[] visited = new boolean[n + 1];
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.dist));

        pq.offer(new Node(s, 0));
        minDist[s] = 0;

        while (!pq.isEmpty()) {
            Node now = pq.poll();

            if (visited[now.to]) { continue; }
            visited[now.to] = true;

            for (Node next : adj[now.to]) {
                int dist = next.dist + minDist[now.to];
                if (!visited[next.to] && dist < minDist[next.to]) {
                    minDist[next.to] = dist;
                    pq.offer(new Node(next.to, dist));
                }
            }
        }

        return minDist[t];
    }

    private void printAns(int ans) throws IOException {
        bw.write(ans + "\n");
        bw.flush();
    }
}
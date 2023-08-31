import java.io.*;
import java.util.*;
class Node {
    int v;
    int w;
    Node(int v, int w) {
        this.v = v;
        this.w = w;
    }
}

public class Main {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public void solution() throws IOException {

        StringTokenizer st = new StringTokenizer(bf.readLine());

        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        int K = Integer.parseInt(bf.readLine());

        ArrayList<Node>[] nodes = new ArrayList[V + 1];

        for (int i = 1; i <= V; i++) {
            nodes[i] = new ArrayList<>();

        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(bf.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            nodes[u].add(new Node(v, w));
        }

        boolean[] visited = new boolean[V  + 1];
        int[] dp = new int[V + 1];

        //Queue<Node> queue = new LinkedList<>();
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> {
            return o1.w - o2.w;
        });

        Arrays.fill(dp, Integer.MAX_VALUE);

        visited[K] = true;
        dp[K] = 0;
        for (int i = 0; i < nodes[K].size(); i++) {
            Node node = nodes[K].get(i);
            dp[node.v] = Math.min(dp[node.v], node.w);
            pq.offer(new Node(node.v, node.w));
        }

        while (!pq.isEmpty()) {
            Node now = pq.poll();

            if (visited[now.v]) continue;

            visited[now.v] = true;

            for (int i = 0; i < nodes[now.v].size(); i++) {
                Node next = nodes[now.v].get(i);

                if (dp[next.v] > dp[now.v] + next.w) {
                    dp[next.v] = dp[now.v] + next.w;
                    pq.offer(new Node(next.v, dp[next.v]));
                }
            }

        }

        for (int i = 1; i <= V; i++) {
            if (dp[i] == Integer.MAX_VALUE) {
                bw.write("INF" + "\n");
                continue;
            }
            bw.write(dp[i] + "\n");

        };

        bw.flush();
        bw.close();
    }


    public static void main (String[] args) throws IOException {
        new Main().solution();
    }
}
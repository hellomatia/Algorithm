import java.util.*;
import java.io.*;

public class Main {

    private static final int MC = -1;
    private static final int SB = 1;

    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private int V, E;
    private List<Node>[] adj;

    private int[] whatIsIt;

    private int[] mcdonald;
    private int M, x;

    private int[] starbucks;
    private int S, y;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    public void solution() throws IOException {
        init();
        printAns(calcAns());
        close();
    }

    private void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        adj = new List[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken());

            adj[u].add(new Node(v, w));
            adj[v].add(new Node(u, w));
        }

        whatIsIt = new int[V];

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        mcdonald = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int mc = Integer.parseInt(st.nextToken()) - 1;
            whatIsIt[mc] = MC;
            mcdonald[i] = mc;
        }

        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        starbucks = new int[S];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < S; i++) {
            int sb = Integer.parseInt(st.nextToken()) - 1;
            whatIsIt[sb] = SB;
            starbucks[i] = sb;
        }
    }

    private String calcAns() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[V];

        // 맥세권
        int[] dist1 = new int[V];
        Arrays.fill(dist1, 987654321);
        for (int i = 0; i < M; i++) {
            int mc = mcdonald[i];
            if (visited[mc]) { continue; }
            visited[mc] = true;
            pq.offer(new Node(mc, 0));
            dist1[mc] = 0;
            while (!pq.isEmpty()) {
                Node now = pq.poll();

                for (int j = 0; j < adj[now.getNum()].size(); j++) {
                    Node next = adj[now.getNum()].get(j);

                    if (whatIsIt[next.getNum()] == MC && !visited[next.getNum()]) {
                        visited[next.getNum()] = true;
                        dist1[next.getNum()] = 0;
                        pq.offer(new Node(next.getNum(),0));
                    } else {
                        int weight = now.getWeight() + next.getWeight();
                        if (weight > x || dist1[next.getNum()] < weight) {
                            continue;
                        }
                        dist1[next.getNum()] = weight;
                        pq.offer(new Node(next.getNum(), weight));
                    }
                }
            }
        }

        // 스세권
        pq.clear();
        int[] dist2 = new int[V];
        Arrays.fill(dist2, 987654321);
        for (int i = 0; i < S; i++) {
            int sb = starbucks[i];
            if (visited[sb]) { continue; }
            visited[sb] = true;
            pq.offer(new Node(sb, 0));
            dist2[sb] = 0;
            while (!pq.isEmpty()) {
                Node now = pq.poll();

                for (int j = 0; j < adj[now.getNum()].size(); j++) {
                    Node next = adj[now.getNum()].get(j);

                    if (whatIsIt[next.getNum()] == SB && !visited[next.getNum()]) {
                        visited[next.getNum()] = true;
                        dist2[next.getNum()] = 0;
                        pq.offer(new Node(next.getNum(),0));
                    } else {
                        int weight = now.getWeight() + next.getWeight();
                        if (weight > y || dist2[next.getNum()] < weight) {
                            continue;
                        }
                        dist2[next.getNum()] = weight;
                        pq.offer(new Node(next.getNum(), weight));
                    }
                }
            }
        }

        int minWeight = Integer.MAX_VALUE;
        for (int i = 0; i < V; i++) {
            if (whatIsIt[i] != 0) { continue; }
            if (dist1[i] == 987654321 || dist2[i] == 987654321) { continue; }
            minWeight = Math.min(minWeight, dist1[i] + dist2[i]);
        }

        return (minWeight != Integer.MAX_VALUE ? minWeight : -1) + "\n";
    }

    private void printAns(String ans) throws IOException {
        bw.write(ans);
        bw.flush();
    }

    private void close() throws IOException {
        bw.close();
        br.close();
    }

    static class Node implements Comparable<Node> {

        private int num;
        private int weight;

        public Node(int num, int weight) {
            this.num = num;
            this.weight = weight;
        }

        public int getNum() {
            return num;
        }

        public int getWeight() {
            return weight;
        }

        @Override
        public int compareTo(Node o) {
            return weight - o.weight;
        }
    }
}
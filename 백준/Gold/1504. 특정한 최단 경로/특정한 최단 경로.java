import java.io.*;
import java.util.*;
class Vertex {
    int num;
    int dist;
    Vertex(int num, int dist) {
        this.num = num;
        this.dist = dist;
    }
}

public class Main {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static final int INF = Integer.MAX_VALUE;
    public void solution() throws IOException {

        StringTokenizer st = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        int[][] dist = new int[N + 1][N + 1];
        for (int i  = 1; i <= N; i++) {
            Arrays.fill(dist[i], INF);
        }


        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(bf.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            dist[a][b] = c;
            dist[b][a] = c;
        }

        st = new StringTokenizer(bf.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        PriorityQueue<Vertex> pq = new PriorityQueue<>((o1, o2) -> {
            return o1.dist - o2.dist;
        });

        int[] from1 = new int[N + 1];
        boolean[] visited = new boolean[N + 1];
        Arrays.fill(from1, INF);

        pq.offer(new Vertex(1, 0));
        from1[1] = 0;

        while (!pq.isEmpty()) {
            Vertex now = pq.poll();

            if (visited[now.num]) continue;

            visited[now.num] = true;

            for (int i = 1; i <= N; i++) {
                if (dist[now.num][i] == INF || visited[i]) continue;

                int newDist = now.dist + dist[now.num][i];

                if (from1[i] > newDist) {
                    from1[i] = newDist;
                    pq.offer(new Vertex(i, newDist));
                }
            }
        }

        int[] fromV1 = new int[N + 1];
        Arrays.fill(fromV1, INF);

        Arrays.fill(visited, false);

        pq.offer(new Vertex(v1, 0));
        fromV1[v1] = 0;

        while (!pq.isEmpty()) {
            Vertex now = pq.poll();

            if (visited[now.num]) continue;

            visited[now.num] = true;

            for (int i = 1; i <= N; i++) {
                if (dist[now.num][i] == INF || visited[i]) continue;

                int newDist = now.dist + dist[now.num][i];

                if (fromV1[i] > newDist) {
                    fromV1[i] = newDist;
                    pq.offer(new Vertex(i, newDist));
                }
            }
        }

        int[] fromV2 = new int[N + 1];
        Arrays.fill(fromV2, INF);

        Arrays.fill(visited, false);

        pq.offer(new Vertex(v2, 0));
        fromV2[v2] = 0;

        while (!pq.isEmpty()) {
            Vertex now = pq.poll();

            if (visited[now.num]) continue;

            visited[now.num] = true;

            for (int i = 1; i <= N; i++) {
                if (dist[now.num][i] == INF || visited[i]) continue;

                int newDist = now.dist + dist[now.num][i];

                if (fromV2[i] > newDist) {
                    fromV2[i] = newDist;
                    pq.offer(new Vertex(i, newDist));
                }
            }
        }

        int dist1 = from1[v1] + fromV1[v2] + fromV2[N];
        if (from1[v1] == INF || fromV1[v2] == INF || fromV2[N] == INF) {
            dist1 = INF;
        }

        int dist2 = from1[v2] + fromV2[v1] + fromV1[N];
        if (from1[v2] == INF || fromV2[v1] == INF || fromV1[N] == INF) {
            dist2 = INF;
        }

        int minDist = 0;
        minDist = Math.min(dist1, dist2);
        if (dist1 == INF && dist2 == INF) {
            minDist = -1;
        }

        bw.write(minDist + "\n");

        bw.flush();
        bw.close();
    }


    public static void main (String[] args) throws IOException {

        new Main().solution();

    }
}
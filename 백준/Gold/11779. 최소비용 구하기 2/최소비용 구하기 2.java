import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int[] target = new int[2];
            int[][] graph = init(br, target);

            int start = target[0];
            int end = target[1];
            int n = graph.length - 1;

            int[] dist = new int[n + 1];
            Arrays.fill(dist, Integer.MAX_VALUE);
            dist[start] = 0;

            int[] prev = new int[n + 1];
            Arrays.fill(prev, -1);

            PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
            pq.offer(new int[]{start, 0});

            while (!pq.isEmpty()) {
                int[] current = pq.poll();
                int node = current[0];
                int distance = current[1];

                if (distance > dist[node]) continue;

                for (int next = 1; next <= n; next++) {
                    if (graph[node][next] != Integer.MAX_VALUE) {
                        int newDist = dist[node] + graph[node][next];
                        if (newDist < dist[next]) {
                            dist[next] = newDist;
                            prev[next] = node;
                            pq.offer(new int[]{next, newDist});
                        }
                    }
                }
            }

            // Reconstruct path
            List<Integer> path = new ArrayList<>();
            for (int at = end; at != -1; at = prev[at]) {
                path.add(at);
            }
            Collections.reverse(path);

            bw.write(dist[end] + "\n");
            bw.write(path.size() + "\n");
            for (int city : path) {
                bw.write(city + " ");
            }
            bw.flush();
        }
    }

    private int[][] init(BufferedReader br, int[] target) throws IOException {
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        int[][] graph = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(graph[i], Integer.MAX_VALUE);
            graph[i][i] = 0;
        }

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph[from][to] = Math.min(graph[from][to], cost);
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        target[0] = Integer.parseInt(st.nextToken());
        target[1] = Integer.parseInt(st.nextToken());

        return graph;
    }
}
import java.io.*;
import java.util.*;
class Village {
    int num;
    int time;
    Village(int num, int time) {
        this.num = num;
        this.time = time;
    }
}

public class Main {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public void solution() throws IOException {

        StringTokenizer st = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        int[][] roads = new int[N + 1][N + 1];
        for (int i = 0; i <= N; i++) {
            Arrays.fill(roads[i], Integer.MAX_VALUE);
        }

        for (int i = 0; i < M; i++) {
             st = new StringTokenizer(bf.readLine());
             int start = Integer.parseInt(st.nextToken());
             int end = Integer.parseInt(st.nextToken());
             int time = Integer.parseInt(st.nextToken());

             roads[start][end] = Math.min(roads[start][end], time);
        }

        PriorityQueue<Village> pq = new PriorityQueue<>((o1, o2) -> {
            return o1.time - o2.time;
        });

        int[] toX = new int[N + 1];
        Arrays.fill(toX, Integer.MAX_VALUE);

        boolean[] visited = new boolean[N + 1];
        pq.offer(new Village(X, 0));

        while (!pq.isEmpty()) {
            Village now = pq.poll();

            if (visited[now.num]) continue;

            visited[now.num] = true;

            for (int i = 1; i <= N; i++) {
                if (roads[i][now.num] == Integer.MAX_VALUE || visited[i]) continue;

                int newTime = now.time + roads[i][now.num];

                if (toX[i] > newTime) {
                    toX[i] = newTime;
                    pq.offer(new Village(i, newTime));
                }
            }
        }

        int[] fromX = new int[N +1];
        Arrays.fill(fromX, Integer.MAX_VALUE);

        Arrays.fill(visited, false);
        pq.offer(new Village(X, 0));

        while (!pq.isEmpty()) {
            Village now = pq.poll();

            if (visited[now.num]) continue;

            visited[now.num] = true;

            for (int i = 1; i <= N; i++) {
                if (roads[now.num][i] == Integer.MAX_VALUE || visited[i]) continue;

                int newTime = now.time + roads[now.num][i];

                if (fromX[i] > newTime) {
                    fromX[i] = newTime;
                    pq.offer(new Village(i, newTime));
                }
            }
        }

        int maxTime = 0;

        for (int i = 1; i <= N; i++) {
            if (toX[i] == Integer.MAX_VALUE || fromX[i] == Integer.MAX_VALUE) {
                continue;
            }

            maxTime = Math.max(maxTime, toX[i] + fromX[i]);
        }

        bw.write(maxTime + "\n");
        bw.flush();
        bw.close();
    }


    public static void main (String[] args) throws IOException {

        new Main().solution();

    }
}
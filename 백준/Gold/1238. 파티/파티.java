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

        int[][] minTime = new int[N + 1][N + 1];
        for (int i = 0; i <= N; i++) {
            Arrays.fill(minTime[i], Integer.MAX_VALUE);
        }

        for (int start = 1; start <= N; start++) {

            for (int target = 1; target <= N; target++) {
                if (roads[start][target] == Integer.MAX_VALUE) continue;

                minTime[start][target] = roads[start][target];
                pq.offer(new Village(target, roads[start][target]));
            }

            boolean[] visited = new boolean[N + 1];
            visited[start] = true;

            while (!pq.isEmpty()) {
                Village now = pq.poll();

                if (visited[now.num]) continue;

                visited[now.num] = true;

                for (int next = 1; next <= N; next++) {
                    if (roads[now.num][next] == Integer.MAX_VALUE || visited[next]) continue;

                    int nextTime = now.time + roads[now.num][next];

                    if (minTime[start][next] > nextTime) {
                        minTime[start][next] = nextTime;
                        pq.offer(new Village(next, nextTime));
                    }

                }

            }
        }

        int maxTime = 0;

        for (int i = 1; i <= N; i++) {
            int time = minTime[i][X] + minTime[X][i];

            if (time == Integer.MAX_VALUE) continue;

            maxTime = Math.max(maxTime, time);
        }


        bw.write(maxTime + "\n");


        bw.flush();
        bw.close();
    }


    public static void main (String[] args) throws IOException {

        new Main().solution();

    }
}
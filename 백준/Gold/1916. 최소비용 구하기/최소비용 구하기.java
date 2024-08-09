import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static final BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int INF = 987654321;
    private int N, M;
    private ArrayList<City>[] cities;
    private int start, end;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        printAns(calcAns());
    }

    private void init() throws IOException {
        N = Integer.parseInt(bf.readLine());
        M = Integer.parseInt(bf.readLine());

        cities = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            cities[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            cities[start].add(new City(end, cost));
        }

        StringTokenizer st = new StringTokenizer(bf.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
    }

    private String calcAns() {
        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);

        PriorityQueue<City> pq = new PriorityQueue<>();
        for (int i = 0; i < cities[start].size(); i++) {
            City city = cities[start].get(i);
            dist[city.num] = Math.min(dist[city.num], city.cost);
            pq.offer(city);
        }

        boolean[] visited = new boolean[N + 1];
        visited[start] = true;

        while (!pq.isEmpty()) {
            City now = pq.poll();
            if (visited[now.num]) {
                continue;
            }
            visited[now.num] = true;

            for (int i = 0; i < cities[now.num].size(); i++) {
                City next = cities[now.num].get(i);
                int cost = next.cost + dist[now.num];

                if (!visited[next.num] && dist[next.num] > cost) {
                    dist[next.num] = cost;
                    pq.offer(new City(next.num, cost));
                }
            }
        }

        return dist[end] + "";
    }

    private void printAns(String ans) throws IOException {
        bw.write(ans + "\n");
        bw.flush();
    }

    static class City implements Comparable<City> {
        int num;
        int cost;

        public City(int num, int cost) {
            this.num = num;
            this.cost = cost;
        }

        @Override
        public int compareTo(City o) {
            return cost - o.cost;
        }
    }
}

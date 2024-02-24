import java.io.*;
import java.util.*;
class City implements Comparable<City> {

    int num;
    int cost;

    City(int num, int cost) {
        this.num = num;
        this.cost = cost;
    }

    @Override
    public int compareTo(City o) {
        return cost - o.cost;
    }
}

public class Main {

    private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private int N, M, S, D;
    private List<City>[] city;
    private Set<Integer>[] route;
    private boolean[][] canNotGo;

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }

    public void solution() throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        while (((N = Integer.parseInt(st.nextToken())) != 0) &&
                ((M = Integer.parseInt(st.nextToken())) != 0)) {
            init();
            printResult(calcResult());
            st = new StringTokenizer(bf.readLine());
        }
        bw.flush();
        bw.close();
    }

    private void init() throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        S = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        city = new List[N];
        for (int i = 0; i < N; i++) {
            city[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            city[from].add(new City(to, cost));
        }

        route = new Set[N];
        for (int i = 0; i < N; i++) {
            route[i] = new HashSet<>();
        }

        canNotGo = new boolean[N][N];
    }

    private int calcResult() {
        dijkstra(S);
        removeMinRoute(S, D);
        return dijkstra(S);
    }

    private void removeMinRoute(int from, int to) {
        if (from == to) return;
        for (int next : route[to]) {
            if (!canNotGo[next][to]) {
                canNotGo[next][to] = true;
                removeMinRoute(from, next);
            }
        }
    }

    private int dijkstra(int start) {
        int[] d = new int[N];

        PriorityQueue<City> pq = new PriorityQueue<>();

        Arrays.fill(d, Integer.MAX_VALUE);

        for (City to : city[start]) {
            if (canNotGo[S][to.num]) continue;
            d[to.num] = to.cost;
            route[to.num].add(S);
            pq.offer(to);
        }

        while(!pq.isEmpty()) {
            City now = pq.poll();

            if (now.cost > d[now.num] ) continue;

            for (City next : city[now.num]) {
                if (canNotGo[now.num][next.num]) continue;
                int cost = d[now.num] + next.cost;

                if (d[next.num] == cost) {
                    route[next.num].add(now.num);
                } else if (d[next.num] > cost) {
                    d[next.num] = cost;
                    route[next.num].clear();
                    route[next.num].add(now.num);
                    pq.offer(new City(next.num, cost));
                }
            }
        }

        return d[D];
    }

    private void printResult(int result) throws IOException {
        if (result == Integer.MAX_VALUE) bw.write("-1\n");
        else bw.write(result + "\n");
    }
}
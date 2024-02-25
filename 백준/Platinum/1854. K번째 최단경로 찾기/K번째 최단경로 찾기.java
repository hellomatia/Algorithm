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

    private int n, m, k;

    private List<City>[] cityList;
    private PriorityQueue<Integer>[] dist;


    public static void main (String[] args) throws IOException {
        new Main().solution();
    }

    public void solution() throws IOException {
        init();
        calcResult();
        printResult();
        bw.flush();
        bw.close();
    }

    private void init() throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        cityList = new List[n + 1];
        dist = new PriorityQueue[n + 1];

        for (int i = 1; i <= n; i++) {
            cityList[i] = new ArrayList<>();
            dist[i] = new PriorityQueue<>(Collections.reverseOrder());
        }


        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(bf.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            cityList[from].add(new City(to, cost));
        }

    }

    private void calcResult() {
        dijkstra(1);
    }

    private void dijkstra(int start) {
        PriorityQueue<City> pq = new PriorityQueue<>();
        pq.add(new City(start, 0));
        dist[start].add(0);

        while (!pq.isEmpty()) {

            City now = pq.poll();

            for (City next : cityList[now.num]) {
                int nextCost = now.cost + next.cost;
                if (dist[next.num].size() < k) {
                    dist[next.num].offer(nextCost);
                    pq.offer(new City(next.num, nextCost));
                } else if (dist[next.num].peek() > nextCost) {
                    dist[next.num].poll();
                    dist[next.num].offer(nextCost);
                    pq.offer(new City(next.num, nextCost));
                }
            }
        }
    }

    private void printResult() throws IOException {
        for (int i = 1; i <= n; i++) {
            if (dist[i].size() != k) {
                bw.write("-1\n");
            } else {
                bw.write(dist[i].peek() + "\n");
            }
        }
    }
}
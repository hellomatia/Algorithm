import java.io.*;
import java.util.*;
class City {
    int num;
    int cost;
    City(int num, int cost) {
        this.num = num;
        this.cost = cost;
    }
}

public class Main {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static final int INF = Integer.MAX_VALUE;
    public void solution() throws IOException {
        int N = Integer.parseInt(bf.readLine());
        int M = Integer.parseInt(bf.readLine());

        ArrayList<City>[] citys = new ArrayList[N+1];
        for (int i = 1; i <= N; i++) {
            citys[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());

            int start = Integer.parseInt(st.nextToken());
            int target = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            citys[start].add(new City(target, cost));
        }

        StringTokenizer st = new StringTokenizer(bf.readLine());
        int start = Integer.parseInt(st.nextToken());
        int target = Integer.parseInt(st.nextToken());

        PriorityQueue<City> pq = new PriorityQueue<>((o1, o2) -> {
            return o1.cost - o2.cost;
        });

        int[] minDist = new int[N + 1];
        Arrays.fill(minDist, INF);

        for (int i = 0; i < citys[start].size(); i++) {
            City city = citys[start].get(i);
            minDist[city.num] = Math.min(minDist[city.num], city.cost);
            pq.offer(city);
        }

        boolean[] visited = new boolean[N + 1];
        visited[start] = true;

        while(!pq.isEmpty()) {
            City now = pq.poll();

            if (visited[now.num]) continue;
            visited[now.num] = true;

            for (int i = 0; i < citys[now.num].size(); i++) {
                City next = citys[now.num].get(i);
                int newCost = now.cost + next.cost;

                if (!visited[next.num] && minDist[next.num] > newCost) {
                    minDist[next.num] = newCost;
                    pq.offer(new City(next.num, newCost));
                }
            }
        }

        bw.write(minDist[target] + "\n");
        bw.flush();
        bw.close();
    }


    public static void main (String[] args) throws IOException {

        new Main().solution();

    }
}
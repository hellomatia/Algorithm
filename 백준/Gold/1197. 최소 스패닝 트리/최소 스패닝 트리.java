import java.io.*;
import java.util.*;
class Edge {
    int vertex1;
    int vertex2;
    int cost;
    Edge(int vertex1, int vertex2, int cost) {
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.cost = cost;
    }
}

public class Main {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int[] parents;

    public void solution() throws IOException {

        StringTokenizer st = new StringTokenizer(bf.readLine());

        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        PriorityQueue<Edge> edges = new PriorityQueue<>((o1, o2) -> {
            return o1.cost - o2.cost;
        });

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(bf.readLine());

            int vertex1 = Integer.parseInt(st.nextToken());
            int vertex2 = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            edges.offer(new Edge(vertex1, vertex2, cost));
        }

        parents = new int[V + 1];
        for (int i = 1; i <= V; i++) {
            parents[i] = i;
        }


        int ans = 0;

        while(!edges.isEmpty()) {
            Edge now = edges.poll();

            if (!union(now.vertex1, now.vertex2)) {
                continue;
            }

            ans += now.cost;
        }

        bw.write(ans + "\n");
        bw.flush();
        bw.close();
    }

    public boolean union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x == y) return false;

        if (x <= y) parents[y] = x;
        else parents[x] = y;
        return true;
    }

    public int find(int x) {
        if (parents[x] == x) return x;
        return find(parents[x]);
    }


    public static void main (String[] args) throws IOException {

        new Main().solution();

    }
}
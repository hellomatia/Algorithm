import java.io.*;
import java.util.*;
class Line {
    int computerNum1;
    int computerNum2;
    int cost;
    Line (int computerNum1, int computerNum2, int cost) {
        this.computerNum1 = computerNum1;
        this.computerNum2 = computerNum2;
        this.cost = cost;
    }
}
public class Main {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static final int INF = Integer.MAX_VALUE;
    static int[] parent;
    public void solution() throws IOException {

        int N = Integer.parseInt(bf.readLine());
        int M = Integer.parseInt(bf.readLine());

        PriorityQueue<Line> lines = new PriorityQueue<>((o1, o2) -> {
            return o1.cost - o2.cost;
        });

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int computerNum1 = Integer.parseInt(st.nextToken());
            int computerNum2 = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            lines.offer(new Line(computerNum1, computerNum2, cost));
        }

        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        int totalMinCost = 0;

        while (!lines.isEmpty()) {
            Line line = lines.poll();

            if (find(line.computerNum1) == find(line.computerNum2)) continue;

            union(line.computerNum1, line.computerNum2);

            totalMinCost += line.cost;
        }


        bw.write(totalMinCost + "\n");

        bw.flush();
        bw.close();
    }

    public boolean union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x == y) return false;

        if (x <= y) parent[y] = x;
        else parent[x] = y;
        return false;
    }

    public int find(int x) {
        if (parent[x] == x) return x;
        return find(parent[x]);
    }


    public static void main (String[] args) throws IOException {

        new Main().solution();

    }
}
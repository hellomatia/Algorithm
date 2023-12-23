import java.io.*;
import java.util.*;

class Star {
    int num;
    double x;
    double y;

    Star(int num, double x, double y) {
        this.num = num;
        this.x = x;
        this.y = y;
    }
}

class Line {
    Star start;
    Star end;
    double cost;

    Line(Star start, Star end, double cost) {
        this.start = start;
        this.end = end;
        this.cost = cost;
    }
}

public class Main {

    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int n;
    private static Star[] stars;
    private static double[][] dist;
    private static int[] parents;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        printResult(calcMinCost());
        bw.flush();
        bw.close();
    }

    private void init() throws IOException {
        n = Integer.parseInt(bf.readLine());
        stars = new Star[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            stars[i] = new Star(
                    i,
                    Double.parseDouble(st.nextToken()),
                    Double.parseDouble(st.nextToken())
            );
        }
        parents = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }
    }

    private double calcMinCost() {
        PriorityQueue<Line> pq = new PriorityQueue<>(Comparator.comparingDouble(o -> o.cost));

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                pq.offer(
                        new Line(
                                stars[i],
                                stars[j],
                                calcDist(stars[i], stars[j])
                        )
                );
            }
        }

        double cost = 0D;

        while (!pq.isEmpty()) {
            Line now = pq.poll();
            if (find(now.start.num) == find(now.end.num)) {
                continue;
            }
            union(now.start.num, now.end.num);
            cost += now.cost;
        }

        return cost;
    }

    private double calcDist(Star star1, Star star2) {
        return Math.sqrt(Math.pow((star1.x - star2.x), 2) + Math.pow((star1.y - star2.y), 2));
    }

    private void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x == y) {
            return;
        }

        if (x < y) {
            parents[y] = x;
        }
        else parents[x] = y;
    }

    private int find(int x) {
        if (parents[x] == x) {
            return x;
        }
        return find(parents[x]);
    }

    private void printResult(double result) throws IOException {
        bw.write(String.format("%.2f", result) + "\n");
    }
}
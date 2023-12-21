import java.io.*;
import java.util.*;

class Point {
    int x;
    int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {

    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int N;
    private static PriorityQueue<Point> pq;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        printResult();
        bw.flush();
        bw.close();
    }

    private void init() throws IOException {
        N = Integer.parseInt(bf.readLine());
        pq = new PriorityQueue<>((o1, o2) -> {
            if (o1.y == o2.y) {
                return o1.x - o2.x;
            }
            return o1.y - o2.y;
        });
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            pq.offer(
                    new Point(
                            Integer.parseInt(st.nextToken()),
                            Integer.parseInt(st.nextToken())
                    )
            );
        }
    }

    private void printResult() throws IOException {
        while (!pq.isEmpty()) {
            Point now = pq.poll();
            bw.write(now.x + " " + now.y);
            bw.write("\n");
        }
    }
}
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
    private static PriorityQueue<Point> pq;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        while (!pq.isEmpty()) {
            printResult(pq.poll());
        }
        bw.flush();
        bw.close();
    }

    private void init() throws IOException {
        pq = new PriorityQueue<>((o1, o2) -> {
            if (o1.x == o2.x) {
                return o1.y - o2.y;
            }
            return o1.x - o2.x;
        });

        int count = Integer.parseInt(bf.readLine());
        for (int i = 0; i < count; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            pq.offer(new Point(x, y));
        }
    }

    private void printResult(Point result) throws IOException {
        bw.write(result.x + " " + result.y + "\n");
    }
}

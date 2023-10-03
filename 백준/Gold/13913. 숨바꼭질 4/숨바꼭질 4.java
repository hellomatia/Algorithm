import java.io.*;
import java.util.*;
class Point {
    int x;
    int count;
    ArrayList<String> history = new ArrayList<>();
    public Point() {
        count = -1;
    }
    public void setX(int x) {
        this.x = x;
        addHistory();
        count++;
    }
    public void addHistory() {
        this.history.add(String.valueOf(x));
    }

    public Point clone() {
        Point clone = new Point();
        clone.history = (ArrayList<String>) this.history.clone();
        clone.count = this.count;
        return clone;
    }

}

public class Main {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public void solution() throws IOException {

        StringTokenizer st = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Queue<Point> queue = new LinkedList<>();
        boolean[] visited = new boolean[100_001];

        Point start = new Point();
        start.setX(N);

        visited[start.x] = true;
        queue.offer(start);

        if (N <= K) {
            while (!queue.isEmpty()) {

                Point now = queue.poll();

                if (now.x == K) {
                    bw.write(now.count + "\n");

                    for (String str : now.history) {
                        bw.write(str + " ");
                    }

                    break;
                }

                int forward = now.x + 1;
                int backward = now.x - 1;
                int teleport = now.x * 2;

                if (forward <= 100_000 && !visited[forward]) {

                    Point forwardPoint = now.clone();
                    forwardPoint.setX(forward);

                    visited[forward] = true;
                    queue.offer(forwardPoint);
                }

                if (0 <= backward && !visited[backward]) {

                    Point backwardPoint = now.clone();
                    backwardPoint.setX(backward);

                    visited[backward] = true;
                    queue.offer(backwardPoint);
                }

                if (teleport <= 100_000 && !visited[teleport]) {

                    Point teleportPoint = now.clone();
                    teleportPoint.setX(teleport);

                    visited[teleport] = true;
                    queue.offer(teleportPoint);
                }
            }
        } else {
            bw.write(N - K + "\n");
            for (int i = N; i >= K; i--) {
                bw.write(i + " ");
            }
        }


        bw.flush();
        bw.close();
    }




    public static void main (String[] args) throws IOException {

        new Main().solution();

    }
}
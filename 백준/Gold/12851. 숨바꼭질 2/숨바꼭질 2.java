import java.io.*;
import java.util.*;
class Point{
    int x;
    int count = 0;
    public Point(int x) {
        this.x = x;
    }
    public Point(int x, int count) {
        this.x = x;
        this.count = count;
    }
}

public class Main {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public void solution() throws IOException {

        StringTokenizer st = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());


        if (K < N) {
            bw.write((N - K) + "\n");
            bw.write(1 + "\n");
        } else {
            Queue<Point> queue = new LinkedList<>();
            queue.offer(new Point(N));

            boolean[] visited = new boolean[100_001];
            visited[N] = true;

            int minCount = Integer.MAX_VALUE;
            int numOfMinCount = 0;

            while(!queue.isEmpty()) {
                Point now = queue.poll();

                if (now.x == K) {
                    if (now.count > minCount) {
                        break;
                    } else {
                        minCount = now.count;
                        numOfMinCount++;
                    }
                    continue;
                }

                visited[now.x] = true;

                int forward = now.x + 1;
                int backward = now.x - 1;
                int teleport = now.x * 2;

                if (forward <= 100_000 && !visited[forward]) {
                    queue.offer(new Point(forward, now.count + 1));
                }

                if (0 <= backward && !visited[backward]) {
                    queue.offer(new Point(backward, now.count + 1));
                }

                if (teleport <= 100_000 && !visited[teleport]) {
                    queue.offer(new Point(teleport, now.count + 1));
                }
            }

            bw.write(minCount + "\n");
            bw.write(numOfMinCount + "\n");
        }

        bw.flush();
        bw.close();
    }

    public static void main (String[] args) throws IOException {

        new Main().solution();

    }
}
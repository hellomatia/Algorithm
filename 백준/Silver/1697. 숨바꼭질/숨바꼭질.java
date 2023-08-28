import java.io.*;
import java.util.*;

class Point {
    int x;
    int min;
    Point(int x, int min) {
        this.x = x;
        this.min = min;
    }
}

public class Main {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static boolean[] visited;
    public void solution() throws IOException {

        StringTokenizer st = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        visited = new boolean[100_001];

        bw.write(bfs(N, K) + "\n");


        bw.flush();
        bw.close();
    }

    public int bfs(int N, int K) {
        
        Queue<Point> queue = new LinkedList<>();
        
        queue.offer(new Point(N, 0));
        visited[N] = true;

        int minMin = 0;

        while(!queue.isEmpty()) {

            Point now = queue.poll();

            if (now.x == K) {
                minMin = now.min;
                break;
            }

            if (now.x + 1 <= 100_000 && !visited[now.x + 1]) {
                visited[now.x + 1] = true;
                queue.offer(new Point(now.x + 1, now.min + 1));
            }

            if (now.x * 2 <= 100_000 && !visited[now.x * 2]) {
                visited[now.x * 2] = true;
                queue.offer(new Point(now.x * 2, now.min + 1));
            }

            if (0 <= now.x - 1 && !visited[now.x - 1]) {
                visited[now.x - 1] = true;
                queue.offer(new Point(now.x - 1, now.min + 1));
            }
        }

        return minMin;
    }


    public static void main (String[] args) throws IOException {
        new Main().solution();
    }
}
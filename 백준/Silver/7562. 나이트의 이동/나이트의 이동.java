import java.io.*;
import java.util.*;
class Point {
    int x;
    int y;
    int count;
    Point (int x, int y, int count) {
        this.x = x;
        this.y = y;
        this.count = count;
    }

}

public class Main {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int[] dirX = {2, 2, 1, 1, -1, -1, -2, -2};
    static int[] dirY = {-1, 1, -2, 2, -2, 2, -1, 1};

    public void solution() throws IOException {

        int tc = Integer.parseInt(bf.readLine());

        for (int i = 0; i < tc; i++) {
             int n = Integer.parseInt(bf.readLine());

             StringTokenizer st = new StringTokenizer(bf.readLine());

             int nowX = Integer.parseInt(st.nextToken());
             int nowY = Integer.parseInt(st.nextToken());

             st = new StringTokenizer(bf.readLine());

             int targetX = Integer.parseInt(st.nextToken());
             int targetY = Integer.parseInt(st.nextToken());

            bw.write(getMinCount(n, nowX, nowY, targetX, targetY) + "\n");

        }


        bw.flush();
        bw.close();
    }

    public int getMinCount(int n, int nowX, int nowY, int targetX, int targetY) {

        boolean[][] visited = new boolean[n][n];

        Queue<Point> queue = new LinkedList<>();
        visited[nowX][nowY] = true;
        queue.offer(new Point(nowX, nowY, 0));

        while (!queue.isEmpty()) {

            Point now = queue.poll();

            if (now.x == targetX && now.y == targetY) {
                return now.count;
            }

            for (int i = 0; i < 8; i++) {
                int nextX = now.x + dirX[i];
                int nextY = now.y + dirY[i];

                if (nextX < 0 || nextY < 0 ||
                        n <= nextX || n <= nextY ||
                        visited[nextX][nextY]) continue;

                visited[nextX][nextY] = true;
                queue.offer(new Point(nextX, nextY, now.count + 1));
            }
        }

        return -1;
    }

    public static void main (String[] args) throws IOException {

        new Main().solution();

    }
}
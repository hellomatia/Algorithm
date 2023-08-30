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
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int[] dirX = {1, -1, 0, 0, 1, 1, -1, -1};
    static int[] dirY = {0, 0, 1, -1, 1, -1, 1, -1};
    static
    public void solution() throws IOException {

        while (true) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int w = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());

            if (w == 0 && h == 0) break;

            int[][] map = new int[h][w];

            Queue<Point> queue = new LinkedList<>();

            for (int i = 0; i < h; i++) {
                st = new StringTokenizer(bf.readLine());
                for (int j = 0; j < w; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if (map[i][j] == 1) {
                        queue.offer(new Point(i, j));
                    }
                }
            }

            boolean[][] visited = new boolean[h][w];
            int count = 0;
            while(!queue.isEmpty()) {
                Point now = queue.poll();

                if(visited[now.x][now.y]) continue;
                count++;
                visited[now.x][now.y] = true;
                bfs(now.x, now.y, map, visited);
            }

            bw.write(count + "\n");

        }


        bw.flush();
        bw.close();
    }

    public static void bfs(int x, int y, int[][] map, boolean[][] visited) {

        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(x, y));

        while (!queue.isEmpty()) {
            Point now = queue.poll();

            for (int i = 0; i < 8; i++) {
                int nextX = now.x + dirX[i];
                int nextY = now.y + dirY[i];

                if (nextX < 0 || nextY < 0 || map.length <= nextX || map[0].length <= nextY
                        || map[nextX][nextY] == 0 || visited[nextX][nextY]) continue;

                visited[nextX][nextY] = true;
                queue.offer(new Point(nextX, nextY));

            }
        }

    }


    public static void main (String[] args) throws IOException {
        new Main().solution();
    }
}
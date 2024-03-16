import java.io.*;
import java.util.*;

public class Main {

    class Point {
        int x;
        int y;
        int min;
        int max;

        Point(int x, int y, int min, int max) {
            this.x = x;
            this.y = y;
            this.min = min;
            this.max = max;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    ", min=" + min +
                    ", max=" + max +
                    '}';
        }
    }

    private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private int[] dirX = {0, 1, 0, -1};
    private int[] dirY = {1, 0, -1, 0};

    private int n;
    private int[][] map;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        printResult(calcResult());
        bw.close();
    }

    private void init() throws IOException {
        n = Integer.parseInt(bf.readLine());
        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private int calcResult() {
        int result = 200;

        int start = 0;
        int end = 200;

        while (start < end) {
            int mid = start + end >>> 1;

            boolean can = false;
            for (int i = 0; i <= 200 - mid; i++) {
                if (can) break;
                can = bfs(i, i + mid);
            }

            if (can) {
                end = mid;
                result = Math.min(result, mid);
            } else {
                start = mid + 1;
            }
        }

        return result;
    }

    private boolean bfs(int min, int max) {

        Queue<Point> queue = new ArrayDeque<>();
        queue.offer(new Point(0, 0, map[0][0], map[0][0]));

        boolean[][] visited = new boolean[n][n];

        visited[0][0] = true;

        while (!queue.isEmpty()) {

            Point now = queue.poll();

            if (now.x == n - 1 && now.y == n - 1) return true;

            for (int i = 0; i < 4; i++) {
                int nextX = now.x + dirX[i];
                int nextY = now.y + dirY[i];

                if (isIn(nextX, nextY) && !visited[nextX][nextY]) {
                    int nextMin = Math.min(now.min, map[nextX][nextY]);
                    int nextMax = Math.max(now.max, map[nextX][nextY]);

                    Point next = new Point(nextX, nextY, nextMin, nextMax);
                    if (min <= nextMin && nextMax <= max) {
                        visited[nextX][nextY] = true;
                        queue.offer(next);
                    }
                }
            }
        }
        return false;
    }

    private boolean isIn(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }

    public void printResult(int result) throws IOException {
        bw.write(result + "\n");
        bw.flush();
    }
}
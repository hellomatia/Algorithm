import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private final static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private final static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private final static int[] dirX = {-1, 0, 1, 0};
    private final static int[] dirY = {0, -1, 0, 1};
    private int[][] map;
    private int id;
    private int n, m;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        printAns(calcAns());
    }

    private void init() throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private String calcAns() {
        int count = 0;
        int maxSize = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 1) {
                    count++;
                    maxSize = Math.max(maxSize, bfs(i, j));
                }
            }
        }
        return count + "\n" + maxSize;
    }

    private int bfs(int x, int y) {
        --id;
        Queue<Point> queue = new ArrayDeque<>();
        queue.offer(new Point(x, y));
        map[x][y] = id;
        int size = 1;
        while (!queue.isEmpty()) {
            Point now = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nextX = now.x + dirX[i];
                int nextY = now.y + dirY[i];
                if (isIn(nextX, nextY) && map[nextX][nextY] == 1) {
                    map[nextX][nextY] = id;
                    queue.offer(new Point(nextX, nextY));
                    size++;
                }
            }
        }
        return size;
    }

    private boolean isIn(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < m;
    }

    private void printAns(String ans) throws IOException {
        bw.write(ans + "\n");
        bw.flush();
    }

    private class Point {
        private int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
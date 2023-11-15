import java.io.*;
import java.util.*;
class Point {
    int x;
    int y;
    int count;
    Point(int x, int y, int count) {
        this.x = x;
        this.y = y;
        this.count = count;
    }
}

public class Main {

    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int n, m;
    private static int[][] map;
    private static int[][] newMap;
    private static Point start;
    private static int[] dirX = {0, 0, -1, 1};
    private static int[] dirY = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        exploreMap();
        printResult();
        bw.flush();
        bw.close();
    }

    private void init() throws IOException {
        initNAndM();
        initMap();
    }

    private void initNAndM() throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
    }

    private void initMap() throws IOException {
        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    start = new Point(i, j, 0);
                }
            }
        }
    }

    private void exploreMap() {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(start);
        newMap = new int[n][m];
        while (!queue.isEmpty()) {
            Point now = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nextX = now.x + dirX[i];
                int nextY = now.y + dirY[i];

                if (nextX < 0 || nextY < 0 || n <= nextX || m <= nextY
                        || map[nextX][nextY] == 0 || newMap[nextX][nextY] != 0) {
                    continue;
                }
                newMap[nextX][nextY] = now.count + 1;
                queue.offer(new Point(nextX, nextY, now.count + 1));
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (newMap[i][j] == 0 && map[i][j] == 1) {
                    newMap[i][j] = -1;
                }
            }
        }
        newMap[start.x][start.y] = 0;
    }

    private void printResult() throws IOException {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                bw.write(newMap[i][j] + " ");
            }
            bw.write("\n");
        }
    }
}
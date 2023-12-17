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

    private static int N, M;
    private static int[] dirX = {0, 0, -1, 1};
    private static int[] dirY = {-1, 1, 0, 0};
    private static char[][] map;
    private static int[][] visited;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        printResult(calcSafeZoneCount());
        bw.flush();
        bw.close();
    }

    private void init() throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            String rowValue = bf.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = rowValue.charAt(j);
            }
        }

        visited = new int[N][M];
    }

    private int calcSafeZoneCount() {
        int safeZoneCount = 0;
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                count++;
                if (makeOne(i, j, count)) {
                    safeZoneCount++;
                }
            }
        }
        return safeZoneCount;
    }

    private boolean makeOne(int x, int y, int count) {
        if (map[x][y] == 'S') {
            return false;
        }

        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(x, y));

        while (!queue.isEmpty()) {
            Point now = queue.poll();
            if (visited[now.x][now.y] != 0) {
                if (visited[now.x][now.y] == count) {
                    break;
                }
                return false;
            }

            visited[now.x][now.y] = count;

            int dir = getDir(now.x, now.y);
            int nextX = now.x + dirX[dir];
            int nextY = now.y + dirY[dir];

            queue.offer(new Point(nextX, nextY));
        }
        return true;
    }

    private int getDir(int x, int y) {
        char dir = map[x][y];
        if(dir == 'L') {
            return 0;
        }
        else if (dir == 'R') {
            return 1;
        }
        else if (dir == 'U') {
            return 2;
        }
        else if (dir == 'D') {
            return 3;
        }
        return -1;
    }

    private void printResult(int result) throws IOException {
        bw.write(result + "\n");
    }
}
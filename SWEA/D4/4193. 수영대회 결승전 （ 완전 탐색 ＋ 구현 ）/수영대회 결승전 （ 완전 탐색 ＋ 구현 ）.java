import java.io.*;
import java.util.*;
class Point {
    int x;
    int y;
    int time;

    Point (int x, int y) {
        this.x = x;
        this.y = y;
    }
    Point (int x, int y, int time) {
        this.x = x;
        this.y = y;
        this.time = time;
    }
}

public class Solution {

    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static final int[] dirX = {0, 0, -1, 1};
    private static final int[] dirY = {-1, 1, 0, 0};

    private static int N;
    private static int[][] map;
    private static Point start;
    private static Point target;
    private static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        new Solution().solution();
    }

    private void solution() throws IOException {
        int testCase = Integer.parseInt(bf.readLine());
        for (int t = 1; t <= testCase; t++) {
            init();
            printResult(t, calcResult());
        }
        bw.flush();
        bw.close();
    }

    private void init() throws IOException {
        N = Integer.parseInt(bf.readLine());
        map = new int[N][N];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(bf.readLine());
        start = new Point(
                Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken()),
                0);
        st = new StringTokenizer(bf.readLine());
        target = new Point(
                Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken()));
    }

    private int calcResult() {
        int result = Integer.MAX_VALUE;
        visited = new boolean[N][N];

        PriorityQueue<Point> pq = new PriorityQueue<>((o1, o2) -> {
            return o1.time - o2.time;
        });
        pq.offer(start);

        while (!pq.isEmpty()) {
            Point now = pq.poll();
            if (now.x == target.x && now.y == target.y) {
                result = Math.min(now.time, result);
            }
            visited[now.x][now.y] = true;
            for (int i = 0; i < 4; i++) {
                int nextX = now.x + dirX[i];
                int nextY = now.y + dirY[i];
                int nextTime = now.time + 1;
                if (canGo(nextX, nextY, now.time)) {
                    pq.offer(new Point(nextX, nextY, nextTime));
                } else if (isSwirl(nextX, nextY)) {
                    pq.offer(new Point(now.x, now.y, nextTime));
                }
            }
        }
        return result;
    }

    private boolean canGo(int x, int y, int time) {
        if (x < 0 || y < 0 || N <= x || N <= y) {
            return false;
        } else if (visited[x][y]) {
            return false;
        } else if (map[x][y] == 1) {
            return false;
        } else if (map[x][y] == 2) {
            return (time + 1) % 3 == 0;
        }
        return true;
    }

    private boolean isSwirl(int x, int y) {
        if (x < 0 || y < 0 || N <= x || N <= y) {
            return false;
        } else if (visited[x][y]) {
            return false;
        }
        return map[x][y] == 2;
    }

    private void printResult(int testCase, int result) throws IOException {
        if (result == Integer.MAX_VALUE) {
            bw.write("#"+testCase + " " + "-1" + "\n");
            return;
        }
        bw.write("#"+testCase + " " + result + "\n");
    }
}

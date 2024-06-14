import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    private final static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private final static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private final static int[] dirX = {-1, 0, 1, 0};
    private final static int[] dirY = {0, -1, 0, 1};

    private int[][] map;
    private int N, M;
    private int H, W;
    private int Sx, Sy, Fx, Fy;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        printAns(calcAns());
    }

    private void init() throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 1; j <= M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(bf.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        Sx = Integer.parseInt(st.nextToken());
        Sy = Integer.parseInt(st.nextToken());
        Fx = Integer.parseInt(st.nextToken());
        Fy = Integer.parseInt(st.nextToken());
    }

    private String calcAns() {
        // 메모리 초과 발생! 커스텀 class 대신 배열 사용
        Queue<int[]> queue = new ArrayDeque<>();
        int[] start = new int[3];
        start[0] = Sx;
        start[1] = Sy;
        queue.offer(start);
        boolean[][] isVisited = new boolean[N + 1][M + 1];
        isVisited[Sx][Sy] = true;
        int ans = -1;

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            if (now[0] == Fx && now[1] == Fy) {
                return now[2] + "";
            }
            for (int i = 0; i < 4; i++) {
                int nextX = now[0] + dirX[i];
                int nextY = now[1] + dirY[i];
                if (isIn(nextX, nextY) && !isVisited[nextX][nextY] && canGo(nextX, nextY)) {
                    isVisited[nextX][nextY] = true;
                    int[] next = new int[3];
                    next[0] = nextX;
                    next[1] = nextY;
                    next[2] = now[2] + 1;
                    queue.offer(next);
                }
            }
        }
        return ans + "";
    }

    private boolean isIn(int x, int y) {
        return 1 <= x && x <= (N - H) + 1 && 1 <= y && y <= (M - W) + 1;
    }

    private boolean canGo(int x, int y) {
        for (int i = x; i < x + H; i++) {
            if (map[i][y] + map[i][y + W - 1] != 0) {
                return false;
            }
        }
        for (int i = y; i < y + W; i++) {
            if (map[x][i] + map[x + H - 1][i] != 0) {
                return false;
            }
        }
        return true;
    }

    private void printAns(String ans) throws IOException {
        bw.write(ans + "\n");
        bw.flush();
    }

    static class Point {
        private final int x, y;
        private final int count;

        public Point(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getCount() {
            return count;
        }
    }
}
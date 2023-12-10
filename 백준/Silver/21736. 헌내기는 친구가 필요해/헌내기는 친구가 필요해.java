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
    private static Point start;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        printResult(BFS());
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
                if (map[i][j] == 'I') {
                    start = new Point(i, j);
                }
            }
        }
    }

    private int BFS() {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(start);

        int count = 0;

        boolean[][] visited = new boolean[N][M];
        visited[start.x][start.y] = true;
        while (!queue.isEmpty()) {
            Point now = queue.poll();
            if (map[now.x][now.y] == 'P') {
                count++;
            }
            for (int i = 0; i < 4; i++) {
                int nextX = now.x + dirX[i];
                int nextY = now.y + dirY[i];
                if (nextX < 0 || nextY < 0
                        || N <= nextX || M <= nextY
                        || visited[nextX][nextY] || map[nextX][nextY] == 'X') {
                    continue;
                }
                visited[nextX][nextY] = true;
                queue.offer(new Point(nextX, nextY));
            }
        }

        return count;
    }

    private void printResult(int result) throws IOException {
        if (result == 0) {
            bw.write("TT\n");
            return;
        }
        bw.write(result + "\n");
    }
}

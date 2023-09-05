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
    static int M, N, K;
    static int[] dirX = {1, -1, 0, 0};
    static int[] dirY = {0, 0, 1, -1};
    static int[][] map;
    static boolean[][] visited;
    public void solution() throws IOException {

        StringTokenizer st = new StringTokenizer(bf.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[M][N];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(bf.readLine());

            int y1 = Integer.parseInt(st.nextToken());
            int x1 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken())-1;
            int x2 = Integer.parseInt(st.nextToken())-1;

            drawQuadrangle(x1, y1, x2, y2);
        }

        visited = new boolean[M][N];

        int areaCount = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j ++) {

                if (visited[i][j] || map[i][j] == 1) continue;

                areaCount ++;
                pq.offer(calculateArea(i, j));
            }
        }

        bw.write(areaCount + "\n");

        while (!pq.isEmpty()) {

            bw.write(pq.poll() + " ");

        }




        bw.flush();
        bw.close();
    }

    public void drawQuadrangle(int x1, int y1, int x2, int y2) {

        for (int i = x1; i <= x2; i++) {
            for (int j = y1; j <= y2; j++) {

                map[i][j] = 1;

            }
        }
    }

    public int calculateArea(int x, int y) {

        Queue<Point> queue = new LinkedList<>();

        int area = 0;

        area++;
        visited[x][y] = true;
        queue.offer(new Point(x, y));

        while (!queue.isEmpty()) {

            Point now = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nextX = now.x + dirX[i];
                int nextY = now.y + dirY[i];

                if (nextX < 0 || nextY < 0 || M <= nextX || N <= nextY ||
                        visited[nextX][nextY] || map[nextX][nextY] == 1) continue;

                area++;
                visited[nextX][nextY] = true;
                queue.offer(new Point(nextX, nextY));
            }
        }

        return area;

    }



    public static void main (String[] args) throws IOException {

        new Main().solution();

    }
}
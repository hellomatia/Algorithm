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
    static int[] dirX = {1, -1, 0, 0};
    static int[] dirY = {0, 0, 1, -1};
    static PriorityQueue<Integer> pq = new PriorityQueue<>();
    public void solution() throws IOException {

        int T = Integer.parseInt(bf.readLine());

        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());

            int M = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            int[][] map = new int[N][M];
            boolean[][] visited = new boolean[N][M];

            Queue<Point> queue = new LinkedList<>();

            for (int j = 0; j < K; j++) {

                st = new StringTokenizer(bf.readLine());

                int y = Integer.parseInt(st.nextToken());
                int x = Integer.parseInt(st.nextToken());

                queue.offer(new Point(x, y));

                map[x][y] = 1;
            }

            int count = 0;

            while(!queue.isEmpty()) {
                Point now = queue.poll();

                if(visited[now.x][now.y]) continue;

                count++;
                whiteBug(map, visited, now.x, now.y);
            }

            bw.write(count + "\n");
        }


        bw.flush();
        bw.close();
    }

    public void whiteBug(int[][] map, boolean[][] visited, int x, int y) {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(x, y));

        visited[x][y] = true;

        while(!queue.isEmpty()) {
            Point nowPoint = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nextX = nowPoint.x + dirX[i];
                int nextY = nowPoint.y + dirY[i];

                if (nextX < 0 || nextY < 0 || map.length <= nextX || map[0].length <= nextY || map[nextX][nextY] == 0 || visited[nextX][nextY]) continue;

                visited[nextX][nextY] = true;
                queue.offer(new Point(nextX, nextY));
            }
        }


    }

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }
}
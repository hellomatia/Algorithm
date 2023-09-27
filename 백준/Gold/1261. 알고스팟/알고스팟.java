import java.io.*;
import java.util.*;
class Point {
    int x;
    int y;
    int dist;
    public Point(int x, int y, int dist) {
        this.x = x;
        this.y = y;
        this.dist = dist;
    }
}

public class Main {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, M;
    static int[][] map;
    static int[] dirX = {0, 0, -1, 1};
    static int[] dirY = {-1, 1, 0, 0};
    public void solution() throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            String str = bf.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        PriorityQueue<Point> pq = new PriorityQueue<>((o1, o2) -> {
            return o1.dist - o2.dist;
        });

        int targetX = N - 1;
        int targetY = M - 1;

        boolean[][] visited = new boolean[N][M];
        pq.offer(new Point(0, 0, 0));

        while(!pq.isEmpty()) {
            Point now = pq.poll();
            
            if (visited[now.x][now.y]) {
                continue;
            }

            if (targetX == now.x && targetY == now.y) {
                bw.write(now.dist + "\n");
                break;
            }

            visited[now.x][now.y] = true;

            for (int i = 0; i < 4; i++) {
                int nextX = now.x + dirX[i];
                int nextY = now.y + dirY[i];

                if (nextX < 0 || nextY < 0 || N <= nextX || M <= nextY || visited[nextX][nextY]){
                    continue;
                }

                if(map[nextX][nextY] == 0) {
                    pq.offer(new Point(nextX, nextY, now.dist));
                } else {
                    pq.offer(new Point(nextX, nextY, now.dist + 1));
                }
            }
        }


        bw.flush();
        bw.close();
    }


    public static void main (String[] args) throws IOException {

        new Main().solution();

    }
}
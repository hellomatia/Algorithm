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
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, M;
    static int[][] map;
    static int[] dirX = {0, 0, 1, -1};
    static int[] dirY = {1, -1, 0, 0};
    static boolean[][] visited;
    static int min;
    public void solution() throws IOException {

        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            String str = bf.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        visited = new boolean[N][M];
        visited[0][0] = true;
        min = Integer.MAX_VALUE;

        /*
        dfs(0, 0, 1);
        */

        bfs();
        bw.write(min + "\n");


        bw.flush();
        bw.close();
    }

    public void dfs(int x, int y, int count){

        if (x == N-1 && y == M -1) {
            min = Math.min(min, count);
        }

        for (int i = 0; i < 4; i++) {
            int nextX = x + dirX[i];
            int nextY = y + dirY[i];

            if (nextX < 0 || nextY < 0 || N <= nextX || M <= nextY || map[nextX][nextY] == 0 || visited[nextX][nextY]) continue;

            visited[nextX][nextY] = true;
            dfs(nextX, nextY, count + 1);
            visited[nextX][nextY] = false;

        }
    }

    public void bfs() {
        Queue<Point> queue = new LinkedList<>();

        queue.offer(new Point(0, 0, 1));

        while(!queue.isEmpty()) {
            Point now = queue.poll();

            if(now.x == N - 1 && now.y == M - 1) {
                min = now.count;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nextX = now.x + dirX[i];
                int nextY = now.y + dirY[i];

                if (nextX < 0 || nextY < 0 || N <= nextX || M <= nextY || map[nextX][nextY] == 0  || visited[nextX][nextY]) continue;
                visited[nextX][nextY] = true;
                queue.offer(new Point(nextX, nextY, now.count + 1));
            }
        }
    }

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }
}
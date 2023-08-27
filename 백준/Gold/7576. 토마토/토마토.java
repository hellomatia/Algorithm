import java.io.*;
import java.util.*;

class Point {
    int x;
    int y;
    int day;
    Point(int x, int y, int day) {
        this.x = x;
        this.y = y;
        this.day = day;
    }
}

public class Main {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int[] dirX = {1, -1, 0, 0};
    static int[] dirY = {0, 0, 1, -1};
    static int[][] map;
    static boolean[][] visited;
    static int lastDay;
    static int unripeTomatoesCount;
    static Queue<Point> queue = new LinkedList<>();
    public void solution() throws IOException {

        StringTokenizer st = new StringTokenizer(bf.readLine());

        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];


        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < M; j++) {
                int tomato = Integer.parseInt(st.nextToken());

                map[i][j] = tomato;

                if (tomato == 1) {
                    queue.offer(new Point(i, j, 0));
                    visited[i][j] = true;
                }
                else if (tomato == 0) {
                    unripeTomatoesCount++;
                }
            }
        }

        bfs();

        if (unripeTomatoesCount == 0) {
            bw.write(lastDay + "\n");
        } else {
            bw.write("-1" + "\n");
        }



        bw.flush();
        bw.close();
    }

    public void bfs() {

        while(!queue.isEmpty()) {
            Point nowPoint = queue.poll();

            lastDay = Math.max(lastDay, nowPoint.day);

            for (int i = 0; i < 4; i++) {
                int nextX = nowPoint.x + dirX[i];
                int nextY = nowPoint.y + dirY[i];

                if (nextX < 0 || nextY < 0 || map.length <= nextX || map[0].length <= nextY || map[nextX][nextY] == -1 || visited[nextX][nextY]) continue;

                queue.offer(new Point(nextX, nextY, nowPoint.day + 1));
                visited[nextX][nextY] = true;
                unripeTomatoesCount--;
            }
        }


    }

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }
}
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
    static int N;
    static int[][] map;
    static boolean[][] visited;
    static PriorityQueue<Integer> pq = new PriorityQueue<>();
    public void solution() throws IOException {

        N = Integer.parseInt(bf.readLine());

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            String str = bf.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 0 || visited[i][j]) continue;
                findHouseGroup(i, j);
            }

        }

        int groupNum = pq.size();


        bw.write(groupNum + "\n");

        for (int i = 0; i < groupNum; i++) {
            bw.write(pq.poll() + "\n");
        }


        bw.flush();
        bw.close();
    }

    public void findHouseGroup( int x, int y) {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(x, y));

        int count = 0;
        visited[x][y] = true;

        while(!queue.isEmpty()) {
            Point nowPoint = queue.poll();
            count++;

            for (int i = 0; i < 4; i++) {
                int nextX = nowPoint.x + dirX[i];
                int nextY = nowPoint.y + dirY[i];

                if (nextX < 0 || nextY < 0 || N <= nextX || N <= nextY || map[nextX][nextY] == 0 || visited[nextX][nextY]) continue;

                visited[nextX][nextY] = true;
                queue.offer(new Point(nextX, nextY));
            }
        }

        pq.offer(count);

    }

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }
}
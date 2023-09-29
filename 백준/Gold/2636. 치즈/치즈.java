import java.io.*;
import java.util.*;
class Point{
    int x;
    int y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int row, col;
    static int[][] cheese;
    static int[] dirX = {1, -1, 0, 0};
    static int[] dirY = {0, 0, 1, -1};
    static Queue<Point> cheeses;
    static Queue<Point> holes;

    public void solution() throws IOException {

        StringTokenizer st = new StringTokenizer(bf.readLine());

        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());

        cheese = new int[row][col];

        cheeses = new LinkedList<>();
        holes = new LinkedList<>();

        for (int i = 0; i < row; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < col; j++) {
                cheese[i][j] = Integer.parseInt(st.nextToken());

                if (cheese[i][j] == 1) {
                    cheeses.offer(new Point(i, j));
                } else {
                    if (i == 0 || j == 0 || i == row - 1 || j == col - 1) {
                        cheese[i][j] = 2;
                        continue;
                    }
                    holes.offer(new Point(i, j));
                }
            }
        }

        int time = 0;
        int lastCheeseCount = cheeses.size();

        while (!cheeses.isEmpty()) {
            time++;
            lastCheeseCount = cheeses.size();

            exploreHole(holes.size());
            //printCheese();

            exploreCheese(cheeses.size());
            //printCheese();
        }

        bw.write(time + "\n" + lastCheeseCount);

        bw.flush();
        bw.close();
    }

    public void exploreHole(int count) {
        while (count-- > 0) {
            Point now = holes.poll();

            if (cheese[now.x][now.y] != 0 || changeHoleToAir(now.x, now.y)) {
                continue;
            }

            holes.offer(now);
        }
    }

    public boolean changeHoleToAir(int x, int y) {

        if (!canAir(x, y)) {
            return false;
        }

        Queue<Point> queue = new LinkedList<>();
        boolean[][] visited = new boolean[row][col];

        visited[x][y] = true;
        queue.offer(new Point(x, y));

        while(!queue.isEmpty()) {
            Point now = queue.poll();

            cheese[now.x][now.y] = 2;

            for (int i = 0; i < 4; i++) {
                int nextX = now.x + dirX[i];
                int nextY = now.y + dirY[i];

                if (nextX < 0 || nextY < 0 || row <= nextX || col <= nextY
                        || cheese[nextX][nextY] != 0 || visited[nextX][nextY]) {
                    continue;
                }

                visited[nextX][nextY] = true;
                queue.offer(new Point(nextX, nextY));
            }
        }

        return true;
    }

    public boolean canAir(int x, int y) {

        for (int i = 0; i < 4; i++) {
            int nextX = x + dirX[i];
            int nextY = y + dirY[i];

            if (nextX < 0 || nextY < 0 || row <= nextX || col <= nextY) {
                continue;
            }

            if (cheese[nextX][nextY] == 2) {
                return true;
            }
        }

        return false;
    }

    public void exploreCheese(int count) {

        Queue<Point> air = new LinkedList<>();

        while (count-- > 0) {
            Point now = cheeses.poll();

            if (canAir(now.x, now.y)) {
                air.offer(now);
                continue;
            }

            cheeses.offer(now);
        }

        while (!air.isEmpty()) {
            Point now = air.poll();
            cheese[now.x][now.y] = 2;
        }
    }

    public void printCheese() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print(cheese[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main (String[] args) throws IOException {

        new Main().solution();

    }
}
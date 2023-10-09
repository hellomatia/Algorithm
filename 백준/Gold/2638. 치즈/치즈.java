import java.io.*;
import java.util.*;
class Point {
    private int x;
    private int y;
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }

}

public class Main {

    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, M;
    static int[][] paper;
    static Queue<Point> cheeseQ = new LinkedList<>();
    static Queue<Point> holeQ = new LinkedList<>();
    static int[] dirX = {0, 0, 1, -1};
    static int[] dirY = {1, -1, 0, 0};

    public void solution() throws IOException {

        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        paper = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < M; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());

                Point point = new Point();
                point.setX(i);
                point.setY(j);

                if (paper[i][j] == 1) {
                    cheeseQ.offer(point);
                }
            }
        }

        paper[0][0] = 2;
        checkAir();
        //printPaper();


        int count = cheeseQ.size();
        int time = 0;

        Queue<Point> toBeAir = new LinkedList<>();

        while (count-- > 0) {

            Point now = cheeseQ.poll();

            int airCount = 0;

            for (int i = 0; i < 4; i++) {

                int nextX = now.getX() + dirX[i];
                int nextY = now.getY() + dirY[i];

                if (paper[nextX][nextY] == 2) {
                    airCount++;
                }
            }

            if (2 <= airCount) {
                toBeAir.offer(now);
            } else {
                cheeseQ.offer(now);
            }

            if (count==0) {

                time++;

                while (!toBeAir.isEmpty()) {
                    Point air = toBeAir.poll();
                    paper[air.getX()][air.getY()] = 2;
                }

                count = cheeseQ.size();
                checkAir();
            }
        }

        bw.write(time + "\n");

        bw.flush();
        bw.close();
    }

    public void checkAir() {

        boolean[][] visited = new boolean[N][M];

        Queue<Point> airQ = new LinkedList<>();

        Point start = new Point();
        start.setX(0);
        start.setY(0);
        visited[0][0] = true;

        airQ.offer(start);

        while (!airQ.isEmpty()) {
            Point now = airQ.poll();

            for (int i = 0; i < 4; i++) {

                Point next = new Point();

                next.setX(now.getX() + dirX[i]);
                next.setY(now.getY() + dirY[i]);

                int nextX = next.getX();
                int nextY = next.getY();

                if (nextX < 0 || nextY < 0 || N <= nextX || M <= nextY
                        || visited[nextX][nextY] || paper[nextX][nextY] == 1) {
                    continue;
                }

                visited[nextX][nextY] = true;
                paper[nextX][nextY] = 2;
                airQ.offer(next);
            }
        }
    }

    public void printPaper() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {

                System.out.print(paper[i][j] + " ");

            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) throws IOException {

        new Main().solution();

    }
}
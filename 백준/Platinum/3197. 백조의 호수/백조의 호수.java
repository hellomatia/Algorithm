import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Point {
    int x;
    int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {

    private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private int R, C;
    private char[][] map;
    private Point hu1;
    private Point hu2;

    private static final char LAND = '.';
    private static final char ICE = 'X';
    private static final char HU = 'L';

    private final int[] dirX = {0, -1, 0, 1};
    private final int[] dirY = {-1, 0, 1, 0};

    private Queue<Point> wQ;

    private boolean[][] visited;
    private Queue<Point> queue;

    public static void main(String[] args) throws NumberFormatException, IOException {
        new Main().solution();
    }

    private void solution() throws NumberFormatException, IOException {
        init();
        printResult(calcResult());
        bw.flush();
        bw.close();
    }

    private void init() throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        wQ = new LinkedList<>();
        for (int i = 0; i < R; i++) {
            String rowValue = bf.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = rowValue.charAt(j);
                if (map[i][j] == LAND) {
                    wQ.offer(new Point(i, j));
                } else if (map[i][j] == HU) {
                    if (hu1 == null) hu1 = new Point(i, j);
                    else hu2 = new Point(i, j);
                    map[i][j] = LAND;
                    wQ.offer(new Point(i, j));
                }
            }
        }
    }

    private int calcResult() throws IOException {
        int day = 0;

        visited = new boolean[R][C];
        queue = new LinkedList<>();
        queue.offer(hu1);
        visited[hu1.x][hu1.y] = true;

        while (!canMeet()) {
            oneDayAfter();
            day++;
        }

        return day;
    }

    private boolean canMeet() {
        Queue<Point> nextQ = new LinkedList<>();

        while (!queue.isEmpty()) {
            Point now = queue.poll();

            if (now.x == hu2.x && now.y == hu2.y) {
                return true;
            }

            for (int i = 0; i < 4; i++) {
                int nextX = now.x + dirX[i];
                int nextY = now.y + dirY[i];

                if (!isIn(nextX, nextY) || visited[nextX][nextY]) continue;

                visited[nextX][nextY] = true;
                if (map[nextX][nextY] == LAND) {
                    queue.offer(new Point(nextX, nextY));
                } else if (map[nextX][nextY] == ICE) {
                    nextQ.offer(new Point(nextX, nextY));
                }
            }

        }
        queue = nextQ;
        return false;
    }

    private void oneDayAfter() {
        int waterCount = wQ.size();
        for (int i = 0; i < waterCount; i++) {
            Point now = wQ.poll();

            for (int j = 0; j < 4; j++) {
                int nX = now.x + dirX[j];
                int nY = now.y + dirY[j];

                if (isIn(nX, nY)) {
                    if (map[nX][nY] == ICE) {
                        map[nX][nY] = LAND;
                        wQ.offer(new Point(nX, nY));
                    }
                }
            }
        }
    }

    private boolean isIn(int x, int y) {
        return 0 <= x && x < R && 0 <= y && y < C;
    }

    private void printResult(int result) throws IOException {
        bw.write(result + "\n");
    }
}


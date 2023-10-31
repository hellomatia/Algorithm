import java.io.*;
import java.util.*;
import java.util.stream.Stream;

class Point {
    int x;
    int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Dust {
    int x;
    int y;
    int value;

    Dust(int x, int y, int value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }
}

public class Main {

    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int R, C, T;
    private int[][] room;
    private Point airCleaner;
    private Queue<Dust> finDusts = new LinkedList<>();


    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        for (int min = 0; min < T; min++) {
            oneMinLatter();
        }
        printResult(getResult());
    }

    private void init() throws IOException {
        initRCT();
        initRoom();
    }

    private void initRCT() throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
    }

    private void initRoom() throws IOException {
        room = new int[R][C];
        for (int row = 0; row < R; row++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for (int col = 0; col < C; col++) {
                room[row][col] = Integer.parseInt(st.nextToken());
                if (airCleaner == null && room[row][col] == -1) {
                    airCleaner = new Point(row, col);
                }
            }
        }
    }

    private void findDust() {
        finDusts = new LinkedList<>();
        for (int row = 0; row < R; row++) {
            for (int col = 0; col < C; col++) {
                if (room[row][col] > 0) {
                    finDusts.offer(new Dust(row, col, room[row][col]));
                }
            }
        }
    }

    private void oneMinLatter() {
        findDust();
        spreadOfFineDust();
        //printRoom();
        airCleanerOperation();
        //printRoom();
    }

    private void spreadOfFineDust() {
        int[] dirX = {0, 0, -1, 1};
        int[] dirY = {-1, 1, 0, 0};

        Stream.generate(finDusts::poll)
                .limit(finDusts.size())
                .forEach(dust -> {
                    int spreadValue = dust.value / 5;
                    int spreadCount = 0;
                    for (int dir = 0; dir < 4; dir++) {
                        int spreadX = dust.x + dirX[dir];
                        int spreadY = dust.y + dirY[dir];
                        if (canSpread(spreadX, spreadY)) {
                            room[spreadX][spreadY] += spreadValue;
                            spreadCount++;
                        }
                    }
                    room[dust.x][dust.y] -= (spreadValue * spreadCount);
                });
    }

    private boolean canSpread(int x, int y) {
        return 0 <= x && 0 <= y && x < R && y < C && room[x][y] != -1;
    }

    private void airCleanerOperation() {
        airCleanerClockWise(new Point(airCleaner.x + 1, airCleaner.y + 1));
        airCleanerCounterClockWise(new Point(airCleaner.x, airCleaner.y + 1));
    }

    private void airCleanerCounterClockWise(Point start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);

        for (int col = start.y; col < C; col++) {
            queue.offer(room[start.x][col]);
        }
        for (int row = start.x - 1; row >= 0; row--) {
            queue.offer(room[row][C - 1]);
        }
        for (int col = C - 2; col > 0; col--) {
            queue.offer(room[0][col]);
        }
        for (int row = 0; row < start.x; row++) {
            queue.offer(room[row][0]);
        }

        for (int col = start.y; col < C; col++) {
            room[start.x][col] = queue.poll();
        }
        for (int row = start.x - 1; row >= 0; row--) {
            room[row][C - 1] = queue.poll();
        }
        for (int col = C - 2; col > 0; col--) {
            room[0][col] = queue.poll();
        }
        for (int row = 0; row < start.x; row++) {
            room[row][0] = queue.poll();
        }
    }

    private void airCleanerClockWise(Point start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);

        for (int col = start.y; col < C; col++) {
            queue.offer(room[start.x][col]);
        }
        for (int row = start.x + 1; row < R; row++) {
            queue.offer(room[row][C - 1]);
        }
        for (int col = C - 2; col > 0; col--) {
            queue.offer(room[R - 1][col]);
        }
        for (int row = R - 1; row > start.x; row--) {
            queue.offer(room[row][0]);
        }

        for (int col = start.y; col < C; col++) {
            room[start.x][col] = queue.poll();
        }
        for (int row = start.x + 1; row < R; row++) {
            room[row][C - 1] = queue.poll();
        }
        for (int col = C - 2; col > 0; col--) {
            room[R - 1][col] = queue.poll();
        }
        for (int row = R - 1; row > start.x; row--) {
            room[row][0] = queue.poll();
        }
    }


    private int getResult() {
        int result = 2;
        for (int row = 0; row < R; row++) {
            for (int col = 0; col < C; col++) {
                result += room[row][col];
            }
        }
        return result;
    }

    private void printRoom() {
        for (int row = 0; row < R; row++) {
            for (int col = 0; col < C; col++) {
                System.out.print(room[row][col] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private void printResult(int result) throws IOException {
        bw.write(result + "\n");
        bw.flush();
        bw.close();
    }
}
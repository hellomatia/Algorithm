import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
class MoveCommand {
    static int[] dirX = {0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dirY = {-1, -1, 0, 1, 1, 1, 0, -1};
    int dir;
    int distance;

    MoveCommand(int dir, int distance) {
        this.dir = dir - 1;
        this.distance = distance;
    }

    public int getDirX() {
        return dirX[dir];
    }

    public int getDirY() {
        return dirY[dir];
    }
}

class Point {
    int x;
    int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {

    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int N, M;
    private static int[][] cloudMap;
    private static MoveCommand[] moveCommands;
    private static Queue<Point> clouds = new ArrayDeque<>();
    private static boolean wasCloud[][];


    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        for (MoveCommand command : moveCommands) {
            moveClouds(command);
            wasCloud = new boolean[N][N];
            raining();
            findNextClouds();
        }
        printResult(getResult());
    }

    private void init() throws IOException {
        initNAndM();
        initCloudMap();
        initCommand();
        initFirstClouds();
    }

    private void initNAndM() throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
    }

    private void initCloudMap() throws IOException {
        cloudMap = new int[N][N];
        for (int row = 0; row < N; row++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for (int col = 0; col < N; col++) {
                cloudMap[row][col] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private void initCommand() throws IOException {
        moveCommands = new MoveCommand[M];
        for (int count = 0; count < M; count++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int dir = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());
            moveCommands[count] = new MoveCommand(dir, distance);
        }
    }

    private void initFirstClouds() {
        clouds.offer(new Point(N - 1, 0));
        clouds.offer(new Point(N - 1, 1));
        clouds.offer(new Point(N - 2, 0));
        clouds.offer(new Point(N - 2, 1));
    }

    private void moveClouds(MoveCommand command) {
        int cloudCount = clouds.size();
        while (cloudCount-- > 0) {
            Point cloud = clouds.poll();
            cloud.x = cloud.x + (command.getDirX() * command.distance);
            cloud.y = cloud.y + (command.getDirY() * command.distance);
            cloud.x = getResizeXY(cloud.x);
            cloud.y = getResizeXY(cloud.y);
            clouds.offer(cloud);
        }
    }

    private int getResizeXY(int point) {
        if (point < 0) {
            return getResizeXY(point + N);
        }
        return point % N;
    }

    private void raining() {
        int cloudCount = clouds.size();
        while (cloudCount-- > 0) {
            Point cloud = clouds.poll();
            cloudMap[cloud.x][cloud.y]++;
            wasCloud[cloud.x][cloud.y] = true;
            clouds.offer(cloud);
        }

        while (!clouds.isEmpty()) {
            Point cloud = clouds.poll();
            waterCopyMagic(cloud);
        }
    }

    private void waterCopyMagic(Point cloud) {
        int[] dirX = {-1, -1, 1, 1};
        int[] dirY = {-1, 1, 1, -1};

        for (int dir = 0; dir < 4; dir++) {
            int nearX = cloud.x + dirX[dir];
            int nearY = cloud.y + dirY[dir];

            if (nearX < 0 || nearY < 0 || N <= nearX || N <= nearY || cloudMap[nearX][nearY] == 0) {
                continue;
            }
            cloudMap[cloud.x][cloud.y]++;
        }
    }

    private void findNextClouds() {
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                if (wasCloud[row][col]) {
                    continue;
                }
                if (cloudMap[row][col] >= 2) {
                    cloudMap[row][col] -= 2;
                    clouds.offer(new Point(row, col));
                }
            }
        }
    }

    private int getResult() {
        int result = 0;
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                result += cloudMap[row][col];
            }
        }
        return result;
    }

    private void printResult(int result) throws IOException {
        bw.write(result + "\n");
        bw.flush();
        bw.close();
    }
}
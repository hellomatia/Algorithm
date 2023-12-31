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

public class Solution {

    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static final char LAND_MINE = '*';
    private static final char SAFE = '.';

    private static final int[] dirX = {0, 0, -1, 1, 1, 1, -1, -1};
    private static final int[] dirY = {-1, 1, 0, 0, 1, -1, 1, -1};

    private static int N;
    private static char[][] map;
    private static Queue<Point> safeZones;

    public static void main(String[] args) throws IOException {
        new Solution().solution();
    }

    private void solution() throws IOException {
        int testCase = Integer.parseInt(bf.readLine());
        for (int t = 1; t <= testCase; t++) {
            init();
            printResult(t, calcResult());
        }
        bw.flush();
        bw.close();
    }

    private void init() throws IOException {
        N = Integer.parseInt(bf.readLine());
        map = new char[N][N];
        safeZones = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            String rowValue = bf.readLine();
            for (int j = 0; j < N; j++) {
                char landType = rowValue.charAt(j);
                map[i][j] = landType;
                if (landType == SAFE) {
                    safeZones.add(new Point(i, j));
                }
            }
        }
    }

    private int calcResult() {
        int result = 0;

        Queue<Point> zeros = new LinkedList<>();
        Queue<Point> nums = new LinkedList<>();
        while (!safeZones.isEmpty()) {
            Point now = safeZones.poll();
            char landMineCount = calcNearLandMineCount(now);
            map[now.x][now.y] = landMineCount;
            if (landMineCount == '0') {
                zeros.offer(now);
            } else {
                nums.offer(now);
            }
        }

        //printMap();

        while (!zeros.isEmpty()) {
            Point now = zeros.poll();
            if (map[now.x][now.y] != '0') {
                continue;
            }
            calcLinkedZero(now);
            result++;
        }

        while (!nums.isEmpty()) {
            Point now = nums.poll();
            if (hasNearZero(now)) {
                continue;
            }
            result++;
        }

        return result;
    }

    private char calcNearLandMineCount(Point point) {
        int count = 0;
        for (int i = 0; i < 8; i++) {
            int nearX = point.x + dirX[i];
            int nearY = point.y + dirY[i];

            if (nearX < 0 || nearY < 0 || N <= nearX || N <= nearY) {
                continue;
            }
            if (map[nearX][nearY] == LAND_MINE) {
                count++;
            }
        }
        return (char) (count + '0');
    }

    private void calcLinkedZero(Point point) {
        Queue<Point> linkedZero = new LinkedList<>();
        map[point.x][point.y] = '-';
        linkedZero.offer(point);

        while (!linkedZero.isEmpty()) {
            Point now = linkedZero.poll();

            for (int i = 0; i < 8; i++) {
                int nearX = now.x + dirX[i];
                int nearY = now.y + dirY[i];

                if (nearX < 0 || nearY < 0 || N <= nearX || N <= nearY || map[nearX][nearY] != '0') {
                    continue;
                }

                map[nearX][nearY] = '-';
                linkedZero.offer(new Point(nearX, nearY));
            }
        }
    }

    private boolean hasNearZero(Point point) {
        for (int i = 0; i < 8; i++) {
            int nearX = point.x + dirX[i];
            int nearY = point.y + dirY[i];

            if (nearX < 0 || nearY < 0 || N <= nearX || N <= nearY) {
                continue;
            }
            if (map[nearX][nearY] == '-') {
                return true;
            }
        }
        return false;
    }

    private void printMap() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private void printResult(int testCase, int result) throws IOException {
        if (result == Integer.MAX_VALUE) {
            bw.write("#"+testCase + " " + "-1" + "\n");
            return;
        }
        bw.write("#"+testCase + " " + result + "\n");
    }
}
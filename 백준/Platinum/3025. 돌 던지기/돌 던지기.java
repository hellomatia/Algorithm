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

    private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private final char EMPTY = '.';
    private final char ROCK = 'O';
    private final char WALL = 'X';

    private int R, C, N;
    private char[][] map;
    private Map<Integer, Deque<Point>> route;

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }

    public void solution() throws IOException {
        init();
        calcResult();
        printResult();
        bw.flush();
        bw.close();
    }

    private void init() throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R + 1][C + 1];
        for (int i = 1; i <= R; i++) {
            String rowValue = bf.readLine();
            for (int j = 1; j <= C; j++) {
                map[i][j] = rowValue.charAt(j - 1);
            }
        }

        route = new HashMap<>();

        N = Integer.parseInt(bf.readLine());
    }

    private void calcResult() throws IOException {
        for (int i = 0; i < N; i++) {
            int start = Integer.parseInt(bf.readLine());
            if (!route.containsKey(start)) route.put(start, new ArrayDeque<>());

            while (!route.get(start).isEmpty() && map[route.get(start).peekLast().x][route.get(start).peekLast().y] == ROCK) {
                route.get(start).pollLast();
            }

            if (route.get(start).isEmpty()) {
                simulation(1, start, route.get(start));
            } else {
                simulation(route.get(start).peekLast().x, route.get(start).peekLast().y, route.get(start));
            }
        }
    }

    private void simulation(int startX, int startY, Deque<Point> route) {
        int nowX = startX;
        int nowY = startY;

        while (nowX + 1 <= R && map[nowX + 1][nowY] != WALL) {

            if (map[nowX + 1][nowY] == ROCK) {
                if (isIn(nowX + 1, nowY - 1) && map[nowX + 1][nowY - 1] == EMPTY && map[nowX][nowY - 1] == EMPTY) {
                    nowX++;
                    nowY--;
                } else if (isIn(nowX + 1, nowY + 1) && map[nowX + 1][nowY + 1] == EMPTY && map[nowX][nowY + 1] == EMPTY) {
                    nowX++;
                    nowY++;
                } else break;
            }
            else nowX++;

            route.offerLast(new Point(nowX, nowY));
        }

        map[nowX][nowY] = ROCK;
    }

    private boolean isIn(int x, int y) {
        return 1 <= x && x <= R && 1 <= y && y <= C;
    }

    private void printResult() throws IOException {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= R; i++) {
            for (int j = 1; j <= C; j++) {
                sb.append(map[i][j]);
            }
            sb.append("\n");
        }
        bw.write(sb.toString());
    }
}
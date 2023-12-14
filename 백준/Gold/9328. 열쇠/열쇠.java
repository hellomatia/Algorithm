import java.io.*;
import java.util.*;

class Thief {
    int x, y;

    Thief(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Docs {
    int x, y;
    Docs(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {

    private static final char EMPTY = '.';
    private static final char WALL = '*';
    private static final char DOCS = '$';

    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static final int[] dirX = {0, 0, -1, 1};
    private static final int[] dirY = {-1, 1, 0, 0};

    private static int row, col;
    private static char[][] map;
    private static boolean[] hasKey;
    private static boolean[][] visited;
    private static Set<Docs> docsSet;


    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        int testCase = Integer.parseInt(bf.readLine());
        for (int tc = 0; tc < testCase; tc++) {
            initMap();
            initStartThief();
            updateAllKeys();
            printResult(calcAllDocsCount());
        }
        bw.flush();
        bw.close();
    }

    private void initMap() throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());
        map = new char[row][col];
        for (int r = 0; r < row; r++) {
            String rowValue = bf.readLine();
            for (int c = 0; c < col; c++) {
                map[r][c] = rowValue.charAt(c);
            }
        }
    }

    private void initStartThief() throws IOException {
        String keys = bf.readLine();
        hasKey = new boolean[26];
        if (keys.equals("0")) {
            return;
        }
        for (int i = 0; i < keys.length(); i++) {
            hasKey[keys.charAt(i) - 'a'] = true;
        }
    }

    private void updateAllKeys() {
        for (int r = 0; r < row; r++) {
            updateKeys(r, 0);
            updateKeys(r, col - 1);
        }

        for (int c = 0; c < col; c++) {
            updateKeys(0, c);
            updateKeys(row - 1, c);
        }
    }

    private void updateKeys(int x, int y) {
        if (map[x][y] == WALL) {
            return;
        }
        if (isDoor(x, y) && !canOpen(map[x][y])) {
            return;
        }

        Queue<Thief> thieves = new LinkedList<>();
        boolean[][] visited = new boolean[row][col];
        visited[x][y] = true;
        thieves.add(new Thief(x, y));

        while (!thieves.isEmpty()) {
            Thief now = thieves.poll();
            if (isKey(now.x, now.y) && !hasKey[map[now.x][now.y] - 'a']) {
                hasKey[map[now.x][now.y] - 'a'] = true;
                updateAllKeys();
                return;
            }
            for (int dir = 0; dir < 4; dir++) {
                int nextX = now.x + dirX[dir];
                int nextY = now.y + dirY[dir];
                if (!canGo(nextX, nextY) || visited[nextX][nextY]) {
                    continue;
                }
                if (isDoor(nextX, nextY) && !canOpen(map[nextX][nextY])) {
                    continue;
                }
                visited[nextX][nextY] = true;
                thieves.add(new Thief(nextX, nextY));
            }

        }
    }

    private int calcAllDocsCount() {
        docsSet = new HashSet<>();
        visited = new boolean[row][col];

        for (int r = 0; r < row; r++) {
            updateDocs(r, 0);
            updateDocs(r, col - 1);
        }

        for (int c = 0; c < col; c++) {
            updateDocs(0, c);
            updateDocs(row - 1, c);
        }

        return docsSet.size();
    }

    private void updateDocs(int r, int c) {
        if (map[r][c] == WALL || visited[r][c]) {
            return;
        }
        if (isDoor(r, c) && !canOpen(map[r][c])) {
            return;
        }

        Queue<Thief> thieves = new LinkedList<>();
        visited[r][c] = true;
        thieves.add(new Thief(r, c));

        while (!thieves.isEmpty()) {
            Thief now = thieves.poll();
            if (map[now.x][now.y] == DOCS) {
                docsSet.add(new Docs(now.x, now.y));
            }
            for (int dir = 0; dir < 4; dir++) {
                int nextX = now.x + dirX[dir];
                int nextY = now.y + dirY[dir];
                if (!canGo(nextX, nextY) || visited[nextX][nextY]) {
                    continue;
                }
                if (isDoor(nextX, nextY) && !canOpen(map[nextX][nextY])) {
                    continue;
                }
                visited[nextX][nextY] = true;
                thieves.add(new Thief(nextX, nextY));
            }
        }
    }

    private boolean canGo(int x, int y) {
        return 0 <= x && x < row && 0 <= y && y < col && map[x][y] != WALL;
    }

    private boolean isKey(int x, int y) {
        return 'a' <= map[x][y] && map[x][y] <= 'z';
    }

    private boolean isDoor(int x, int y) {
        return 'A' <= map[x][y] && map[x][y] <= 'Z';
    }

    private boolean canOpen(char door) {
        return hasKey[door - 'A'];
    }

    private void printResult(int result) throws IOException {
        bw.write(result + "\n");
    }
}

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    private static final BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static final char EMPTY = '.';
    private static final char WALL = '#';
    private static final char SHEEP = 'o';
    private static final char WOLF = 'v';

    private static final int[] dx = {-1, 0, 1, 0};
    private static final int[] dy = {0, 1, 0, -1};

    private int R, C;
    private char[][] map;
    private boolean[][] isVisited;
    private int sheepCount, wolfCount;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        calcAns();
        printAns();
    }

    private void init() throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];

        for (int i = 0; i < R; i++) {
            String rowValue = bf.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = rowValue.charAt(j);
            }
        }
        isVisited = new boolean[R][C];
    }

    private void calcAns() {
        int totalSheep = 0;
        int totalWolf = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (!isVisited[i][j] && map[i][j] != WALL) {
                    sheepCount = 0;
                    wolfCount = 0;
                    dfs(i, j);
                    if (sheepCount > wolfCount) {
                        totalSheep += sheepCount;
                    } else {
                        totalWolf += wolfCount;
                    }
                }
            }
        }
        sheepCount = totalSheep;
        wolfCount = totalWolf;
    }

    private void dfs(int x, int y) {
        isVisited[x][y] = true;
        if (map[x][y] == SHEEP) sheepCount++;
        if (map[x][y] == WOLF) wolfCount++;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (isIn(nx, ny) && !isVisited[nx][ny] && map[nx][ny] != WALL) {
                dfs(nx, ny);
            }
        }
    }

    private boolean isIn(int x, int y) {
        return 0 <= x && x < R && 0 <= y && y < C;
    }

    private void printAns() throws IOException {
        bw.write(sheepCount + " ");
        bw.write(wolfCount + "\n");
        bw.flush();
    }
}

import java.io.*;
import java.util.*;

class Fireball {
    int r;
    int c;
    int m;
    int s;
    int d;

    Fireball(int r, int c, int m, int s, int d) {
        this.r = r;
        this.c = c;
        this.m = m;
        this.s = s;
        this.d = d;
    }
}

public class Main {

    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int N, M, K;
    private int[] dirX = {-1, -1, 0, 1, 1, 1, 0, -1};
    private int[] dirY = {0, 1, 1, 1, 0, -1, -1, -1};
    private static List<Fireball>[][] grid;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        while (K-- > 0) {
            moveFireballs();
            splitAndCombineFireballs();
        }
        printResult();
    }

    private void init() throws IOException {
        initNMK();
        grid = new ArrayList[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                grid[i][j] = new ArrayList<>();
            }
        }

        while (M-- > 0) {
            initFireball();
        }
    }

    private void initNMK() throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
    }

    private void initFireball() throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int r = Integer.parseInt(st.nextToken()) - 1;
        int c = Integer.parseInt(st.nextToken()) - 1;
        int m = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        grid[r][c].add(new Fireball(r, c, m, s, d));
    }

    private void moveFireballs() {
        List<Fireball>[][] newGrid = new ArrayList[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                newGrid[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (Fireball fireball : grid[i][j]) {
                    if (fireball.m > 0) {
                        int nr = (fireball.r + (fireball.s * dirX[fireball.d])) % N;
                        int nc = (fireball.c + (fireball.s * dirY[fireball.d])) % N;
                        if (nr < 0) nr += N;
                        if (nc < 0) nc += N;
                        newGrid[nr][nc].add(new Fireball(nr, nc, fireball.m, fireball.s, fireball.d));
                    }
                }
            }
        }

        grid = newGrid;
    }

    private void splitAndCombineFireballs() {
        List<Fireball>[][] newGrid = new ArrayList[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                newGrid[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (grid[i][j].size() >= 2) {
                    int totalM = 0, totalS = 0, count = grid[i][j].size();
                    boolean evenDir = true, oddDir = true;

                    for (Fireball fireball : grid[i][j]) {
                        totalM += fireball.m;
                        totalS += fireball.s;
                        if (fireball.d % 2 == 0) {
                            oddDir = false;
                        } else {
                            evenDir = false;
                        }
                    }

                    int newM = totalM / 5;
                    int newS = totalS / count;

                    if (newM > 0) {
                        for (int d = 0; d < 8; d += 2) {
                            if (evenDir || oddDir) {
                                newGrid[i][j].add(new Fireball(i, j, newM, newS, d));
                            }
                            else {
                                newGrid[i][j].add(new Fireball(i, j, newM, newS, d + 1));
                            }
                        }
                    }
                } else if (grid[i][j].size() == 1) {
                    newGrid[i][j].add(grid[i][j].get(0));
                }
            }
        }

        grid = newGrid;
    }

    private void printResult() throws IOException {
        int result = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (Fireball fireball : grid[i][j]) {
                    result += fireball.m;
                }
            }
        }

        bw.write(result + "\n");
        bw.flush();
        bw.close();
    }
}

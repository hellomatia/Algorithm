import java.io.*;
import java.util.*;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static final char EMPTY = '.';
    private static final char WALL = '#';
    private static final char BLUE = 'B';
    private static final char RED = 'R';
    private static final char HOLE = 'O';
    private static final int[] dx = {-1, 1, 0, 0};
    private static final int[] dy = {0, 0, -1, 1};

    private int N, M;
    private char[][] map;
    private int rx, ry, bx, by, hx, hy;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        int result = bfs();
        bw.write(result + "\n");
        bw.flush();
    }

    private void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            String row = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = row.charAt(j);
                if (map[i][j] == RED) {
                    rx = i; ry = j;
                    map[i][j] = EMPTY;
                } else if (map[i][j] == BLUE) {
                    bx = i; by = j;
                    map[i][j] = EMPTY;
                } else if (map[i][j] == HOLE) {
                    hx = i; hy = j;
                }
            }
        }
    }

    private int bfs() {
        Queue<State> queue = new LinkedList<>();
        boolean[][][][] visited = new boolean[N][M][N][M];

        queue.offer(new State(rx, ry, bx, by, 0));
        visited[rx][ry][bx][by] = true;

        while (!queue.isEmpty()) {
            State cur = queue.poll();

            if (cur.rx == hx && cur.ry == hy) {
                return cur.count;
            }

            for (int i = 0; i < 4; i++) {
                int[] nextRed = move(cur.rx, cur.ry, i);
                int[] nextBlue = move(cur.bx, cur.by, i);

                if (nextBlue[0] == hx && nextBlue[1] == hy) {
                    continue;
                }

                if (nextRed[0] == nextBlue[0] && nextRed[1] == nextBlue[1] && map[nextRed[0]][nextRed[1]] != HOLE) {
                    int redDist = Math.abs(nextRed[0] - cur.rx) + Math.abs(nextRed[1] - cur.ry);
                    int blueDist = Math.abs(nextBlue[0] - cur.bx) + Math.abs(nextBlue[1] - cur.by);

                    if (redDist > blueDist) {
                        nextRed[0] -= dx[i];
                        nextRed[1] -= dy[i];
                    } else {
                        nextBlue[0] -= dx[i];
                        nextBlue[1] -= dy[i];
                    }
                }

                if (!visited[nextRed[0]][nextRed[1]][nextBlue[0]][nextBlue[1]]) {
                    visited[nextRed[0]][nextRed[1]][nextBlue[0]][nextBlue[1]] = true;
                    queue.offer(new State(nextRed[0], nextRed[1], nextBlue[0], nextBlue[1], cur.count + 1));
                }
            }
        }

        return -1;
    }

    private int[] move(int x, int y, int dir) {
        int nx = x;
        int ny = y;
        while (map[nx + dx[dir]][ny + dy[dir]] != WALL && map[nx][ny] != HOLE) {
            nx += dx[dir];
            ny += dy[dir];
        }
        return new int[]{nx, ny};
    }

    static class State {
        int rx, ry, bx, by, count;

        State(int rx, int ry, int bx, int by, int count) {
            this.rx = rx;
            this.ry = ry;
            this.bx = bx;
            this.by = by;
            this.count = count;
        }
    }
}

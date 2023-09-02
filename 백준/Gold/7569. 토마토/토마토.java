import java.io.*;
import java.util.*;
class Tomato {
    int z;
    int x;
    int y;
    int day;
    Tomato(int z, int x, int y, int day) {
        this.z = z;
        this.x = x;
        this.y = y;
        this.day = day;
    }
}

public class Main {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, M, H;
    static int[][][] tomatoMap;
    static boolean[][][] visited;
    static Queue<Tomato> tomatoes;
    static int[] dirZ = {1, -1, 0, 0, 0, 0};
    static int[] dirX = {0, 0, 1, -1, 0, 0};
    static int[] dirY = {0, 0, 0, 0, 1, -1};
    static int unripeTomatoCount;
    static int lastDay = 0;
    public void solution() throws IOException {

        StringTokenizer st = new StringTokenizer(bf.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        
        tomatoMap = new int[H][N][M];
        visited = new boolean[H][N][M];
        tomatoes = new LinkedList<>();

        for (int i = 0; i < H; i++) {

            initializeTomatoMap(i);

        }

        exploreTomatoes();

        if (unripeTomatoCount == 0) {

            bw.write(lastDay + "\n");

        } else {

            bw.write("-1" + "\n");

        }



        bw.flush();
        bw.close();
    }

    public void initializeTomatoMap(int h) throws IOException {

        for (int i = 0; i < N; i++) {

            StringTokenizer st = new StringTokenizer(bf.readLine());

            for (int j = 0; j < M; j++) {

                tomatoMap[h][i][j] = Integer.parseInt(st.nextToken());

                if (tomatoMap[h][i][j] == 1) {

                    tomatoes.offer(new Tomato(h, i, j, 0));

                } else if (tomatoMap[h][i][j] == 0) {

                    unripeTomatoCount++;

                }
            }
        }
    }

    public static void exploreTomatoes() {

        while (!tomatoes.isEmpty()) {

            Tomato now = tomatoes.poll();

            visited[now.z][now.x][now.y] = true;

            lastDay = Math.max(now.day, lastDay);

            for (int i = 0; i < 6; i++) {

                int nextZ = now.z + dirZ[i];
                int nextX = now.x + dirX[i];
                int nextY = now.y + dirY[i];

                if (nextZ < 0 || nextX < 0 || nextY < 0 ||
                        H <= nextZ || N <= nextX || M <= nextY ||
                        visited[nextZ][nextX][nextY] || tomatoMap[nextZ][nextX][nextY] != 0) continue;

                unripeTomatoCount--;
                visited[nextZ][nextX][nextY] = true;
                tomatoes.offer(new Tomato(nextZ, nextX, nextY, now.day + 1));

            }
        }
    }

    public static void main (String[] args) throws IOException {

        new Main().solution();

    }
}
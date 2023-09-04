import java.io.*;
import java.util.*;


public class Main {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int R, C;
    static int[][] map;
    static boolean[] visited;
    static int[] dirX = {0, 0, 1, -1};
    static int[] dirY = {1, -1, 0, 0};
    static int maxCount = Integer.MIN_VALUE;
    public void solution() throws IOException {

        StringTokenizer st = new StringTokenizer(bf.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new int[R][C];

        for (int i = 0; i < R; i++) {
            String str = bf.readLine();

            for (int j = 0; j < C; j++) {

                int alphabet = str.charAt(j) - 'A';
                map[i][j] = alphabet;

            }

        }

        visited = new boolean[26];
        visited[map[0][0]] = true;

        exploreMap(0, 0, 1);

        bw.write(maxCount + "\n");

        bw.flush();
        bw.close();
    }

    public void exploreMap(int x, int y, int count) {
        maxCount = Math.max(count, maxCount);

        for (int i = 0; i < 4; i++) {
            int nextX = x + dirX[i];
            int nextY = y + dirY[i];

            if (nextX < 0 || nextY < 0 || R <= nextX || C <= nextY) continue;

            int nextAlphabet = map[nextX][nextY];

            if (visited[nextAlphabet]) continue;

            visited[nextAlphabet] = true;
            exploreMap(nextX, nextY, count + 1);
            visited[nextAlphabet] = false;

        }

    }

    public static void main (String[] args) throws IOException {

        new Main().solution();

    }
}
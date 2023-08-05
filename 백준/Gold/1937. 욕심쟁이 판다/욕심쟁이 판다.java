import java.io.*;
import java.security.spec.RSAOtherPrimeInfo;
import java.util.*;
public class Main {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int n;
    static int[][] map;
    static boolean[][] visited;
    static int[][] dp;
    static int[] dirX = {0, 0, 1, -1};
    static int[] dirY = {1, -1, 0, 0};
    public void solution() throws IOException {

        n = Integer.parseInt(bf.readLine());

        map = new int[n][n];
        visited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < n; j++) {
                 map[i][j] = Integer.parseInt(st.nextToken());
            }
        }



        dp = new int[n][n];

        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                max = Math.max(max, dfs(i, j));
            }
        }



        bw.write(max + "\n");

        bw.flush();
        bw.close();
    }

    public int dfs (int x, int y) {

        if(dp[x][y] != 0) return dp[x][y];

        for(int i = 0; i < 4; i++) {
            int nextX = x + dirX[i];
            int nextY = y + dirY[i];

            if (nextX < 0 || nextY < 0 || nextX >= n || nextY >= n || map[nextX][nextY] <= map[x][y]) {
                continue;
            }

            dp[x][y] = Math.max(dfs(nextX, nextY) + 1, dp[x][y]);

        }

        if(dp[x][y] == 0) {
            dp[x][y] = 1;
            return dp[x][y];
        }

        return dp[x][y];
    }

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }
}
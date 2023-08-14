import java.io.*;
import java.util.*;
public class Main {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N;
    static int[][] W;
    static long[][] dp;

    public void solution() throws IOException {

        N = Integer.parseInt(bf.readLine());

        W = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < N; j++) {
                W[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new long[N][(1<<N)-1];

        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], -1);
        }

        bw.write(dfs(0, 1) + "\n");
        bw.flush();
        bw.close();
    }

    public long dfs (int nowCity, int visitedCity) {

        if (visitedCity == ((1<<N) - 1)) {

            if (W[nowCity][0] == 0) {
                return Integer.MAX_VALUE;
            }

            return W[nowCity][0];
        }

        if (dp[nowCity][visitedCity] != -1) {
            return dp[nowCity][visitedCity];
        }

        dp[nowCity][visitedCity] = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            if (((visitedCity & (1 << i)) == 0) && W[nowCity][i] != 0) {
                dp[nowCity][visitedCity] = Math.min(dp[nowCity][visitedCity],
                        dfs(i, visitedCity | (1 << i)) + W[nowCity][i]);
            }
        }
        return dp[nowCity][visitedCity];
    }

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }
}
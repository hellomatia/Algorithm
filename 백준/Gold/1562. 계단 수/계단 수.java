import java.io.*;
import java.util.*;

public class Main {
    private static final int MOD = 1_000_000_000;
    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int N;
    private static int[][][] dp;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        printResult(calcStairsNumberCount());
        bw.flush();
        bw.close();
    }

    private void init() throws IOException {
        N = Integer.parseInt(bf.readLine());
    }

    private int calcStairsNumberCount() {
        dp = new int[N + 1][10][1 << 10];

        for (int i = 1; i <= 9; i++) {
            dp[1][i][1 << i] = 1;
        }

        for (int i = 2; i <= N; i++) {
            for (int j = 0; j <= 9; j++) {
                for (int k = 0; k <= 0b1111111111; k++) {
                    int visit = k | (1 << j);
                    if (j == 0) {
                        dp[i][j][visit] += dp[i - 1][j + 1][k] % MOD;
                    }
                    else if (j == 9) {
                        dp[i][j][visit] += dp[i - 1][j - 1][k] % MOD;
                    }
                    else {
                        dp[i][j][visit] += (dp[i - 1][j - 1][k] % MOD + dp[i - 1][j + 1][k] % MOD);
                    }
                    dp[i][j][visit] %= MOD;
                }
            }
        }

        int count = 0;
        for (int i = 0; i < 10; i++) {
            count += dp[N][i][0b1111111111];
            count %= MOD;
        }
        return count;
    }

    private void printResult(int result) throws IOException {
        bw.write(result + "\n");
    }
}

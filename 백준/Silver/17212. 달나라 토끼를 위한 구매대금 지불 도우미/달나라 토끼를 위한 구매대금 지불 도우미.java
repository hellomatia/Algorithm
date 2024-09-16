import java.util.*;
import java.io.*;


public class Main {

    private static final BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private int N;
    private int[] dp;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        printAns(calcAns());
        close();
    }

    private void init() throws IOException {
        N = Integer.parseInt(bf.readLine());
    }

    private String calcAns() {
        dp = N < 7 ? new int[8] : new int[N + 1];

        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 1;
        dp[3] = 2;
        dp[4] = 2;
        dp[5] = 1;
        dp[6] = 2;
        dp[7] = 1;

        for (int i = 8; i <= N; i++) {
            dp[i] = Math.min(dp[i], dp[i - 1] + 1);
            dp[i] = Math.min(dp[i], dp[i - 2] + 1);
            dp[i] = Math.min(dp[i], dp[i - 5] + 1);
            dp[i] = Math.min(dp[i], dp[i - 7] + 1);
        }

        return dp[N] + "";
    }

    private void printAns(String ans) throws IOException {
        bw.write(ans + "\n");
        bw.flush();
    }

    private void close() throws IOException {
        bw.close();
        bf.close();
    }
}
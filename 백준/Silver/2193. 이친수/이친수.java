import java.io.*;
import java.util.*;
public class Main {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static long[][] dp;

    public void solution() throws IOException {

        int N = Integer.parseInt(bf.readLine());

        dp = new long[N+1][2];

        dp[1][1] = 1;

        for (int i = 2; i <= N ; i++) {
            dp[i][0] = dp[i-1][0] + dp[i-1][1];
            dp[i][1] = dp[i-1][0];
        }

        long ans = dp[N][0] + dp[N][1];

        bw.write(ans + "\n");

        bw.flush();
        bw.close();
    }

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }
}
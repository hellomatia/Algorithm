import java.io.*;
import java.util.*;
public class Main {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static long[][] dp;
    public void solution() throws IOException {

        int T = Integer.parseInt(bf.readLine());

        dp = new long[100_001][4];

        dp[1][1] = 1;
        dp[1][0] = 1;
        dp[2][2] = 1;
        dp[2][0] = 1;
        dp[3][3] = 1;
        dp[3][2] = 1;
        dp[3][1] = 1;
        dp[3][0] = 3;

        for (int i = 0; i < T; i++) {
            int num = Integer.parseInt(bf.readLine());
            long ans = calculateNumberOfCases(num);

            bw.write(ans + "\n");
        }

        bw.flush();
        bw.close();
    }

    public long calculateNumberOfCases(int num) {

        if(dp[num][0] != 0) return dp[num][0];

        for (int i = 4; i <= num; i++) {

            if(dp[num][0] != 0) continue;

            dp[i][1] = dp[i-1][2] + dp[i-1][3];
            dp[i][1] %= 1_000_000_009;
            dp[i][2] = dp[i-2][1] + dp[i-2][3];
            dp[i][2] %= 1_000_000_009;
            dp[i][3] = dp[i-3][1] + dp[i-3][2];
            dp[i][3] %= 1_000_000_009;

            dp[i][0] = (dp[i][1] + dp[i][2] + dp[i][3]) % 1_000_000_009;
        }

        return  dp[num][0];
    }

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }
}
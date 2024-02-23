import java.io.*;
import java.util.*;

public class Main {

    private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private long[] dp;

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }

    public void solution() throws IOException {
        init();
        printResult(calcResult());
        bw.flush();
        bw.close();
    }

    private void init() {
        dp = new long[91];
        dp[1] = 1;
        for (int i = 2; i <= 90; i++) {
            dp[i] = dp[i -1] + dp[i - 2];
        }
    }

    private long calcResult() throws IOException {
        int n = Integer.parseInt(bf.readLine());
        return dp[n];
    }

    private void printResult(long result) throws IOException {
        bw.write(result + "\n");
    }
}
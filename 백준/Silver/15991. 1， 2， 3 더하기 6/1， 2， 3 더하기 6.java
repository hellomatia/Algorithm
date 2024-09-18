import java.util.*;
import java.io.*;

public class Main {

    private static final BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private int T;
    private int[] nums;
    private int[] dp;

    public static void main(String[] args) throws IOException {
        new Main().soulution();
    }

    private void soulution() throws IOException {
        init();
        printAns(calcAns());
        close();
    }

    private void init() throws IOException {
        T = Integer.parseInt(bf.readLine());
        nums = new int[T];
        for (int i = 0; i < T; i++) {
            nums[i] = Integer.parseInt(bf.readLine());
        }
    }

    private String calcAns() {
        StringBuilder ans = new StringBuilder();
        dp = new int[100_000 + 1];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 2;
        dp[4] = 3;
        dp[5] = 3;
        dp[6] = 6;

        for (int i = 7; i <= 100_000; i++) {
            dp[i] = (dp[i] + dp[i - 6]) % 1_000_000_009;
            dp[i] = (dp[i] + dp[i - 4]) % 1_000_000_009;
            dp[i] = (dp[i] + dp[i - 2]) % 1_000_000_009;
        }

        for (int num : nums) {
            ans.append(dp[num])
                .append("\n");
        }

        return ans.toString();
    }

    private void dfs() {

    }

    private void printAns(String ans) throws IOException {
        bw.write(ans + "\n");
    }

    private void close() throws IOException {
        bw.close();
        bf.close();
    }
}
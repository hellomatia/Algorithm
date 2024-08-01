import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    private static final BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private int T;
    private int[] nums;
    private int[][] dp;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        printAns(calcAns());
    }

    private void init() throws IOException {
        T = Integer.parseInt(bf.readLine());

        nums = new int[T];
        for (int i = 0; i < T; i++) {
            nums[i] = Integer.parseInt(bf.readLine());
        }
    }

    private String calcAns() {
        dp = new int[10_000 + 10][4];
        dp[1][1] = 1; // 1
        dp[2][1] = 1; // 1 + 1
        dp[2][2] = 1; // 2
        dp[3][1] = 1; // 1 + 1 + 1
        dp[3][2] = 1; // 1 + 2
        dp[3][3] = 1; // 3

        for (int i = 4; i <= 10_000; i++) {
            dp[i][1] = dp[i - 1][1];
            dp[i][2] = dp[i - 2][1] + dp[i - 2][2];
            dp[i][3] = dp[i - 3][1] + dp[i - 3][2] + dp[i - 3][3];
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < T; i++) {
            sb.append(dp[nums[i]][1] + dp[nums[i]][2] + dp[nums[i]][3]).append("\n");
        }

        return sb.toString();
    }

    private void printAns(String ans) throws IOException {
        bw.write(ans + "\n");
        bw.flush();
    }
}

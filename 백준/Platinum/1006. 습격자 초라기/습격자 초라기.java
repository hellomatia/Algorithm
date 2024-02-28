import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private int N, W;

    private int[][] oneSumMap;
    private int[] updownSumMap;
    private int[][] rightSumMap;

    private int[][] dp;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        int T = Integer.parseInt(bf.readLine());
        for (int testCase = 0; testCase < T; testCase++) {
            init();
            printResult(calcResult());
        }
    }

    private void init() throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        // 원본값
        oneSumMap = new int[2][N];
        for (int i = 0; i < 2; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < N; j++) {
                oneSumMap[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 위아래 합한값
        updownSumMap = new int[N];
        for (int i = 0; i < N; i++) {
            updownSumMap[i] = oneSumMap[0][i] + oneSumMap[1][i];
        }

        // 오른쪽 값을 합한 값
        rightSumMap = new int[2][N];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < N; j++) {
                int rightIdx = j == N - 1 ? 0 : j + 1;
                rightSumMap[i][j] = oneSumMap[i][j] + oneSumMap[i][rightIdx];
            }
        }

    }

    private int calcResult() {
        int result = Integer.MAX_VALUE;

        dp = new int[N + 1][3];
        dp[1][0] = dp[1][1] = 1;
        dp[1][2] = updownSumMap[0] <= W ? 1 : 2;
        calcDp();
        result = Math.min(result, dp[N][2]);

        if (rightSumMap[0][N - 1] <= W) {
            dp[1][0] = dp[1][1] = 1;
            dp[1][2] = 2;
            int temp2 = rightSumMap[0][0];
            int temp3 = updownSumMap[0];

            rightSumMap[0][0] = updownSumMap[0] = 1_000_000;
            calcDp();
            result = Math.min(result, dp[N][1]);

            rightSumMap[0][0] = temp2;
            updownSumMap[0] = temp3;
        }

        if (rightSumMap[1][N - 1] <= W) {
            dp[1][0] = dp[1][1] = 1;
            dp[1][2] = 2;
            int temp2 = rightSumMap[1][0];
            int temp3 = updownSumMap[0];

            rightSumMap[1][0] = updownSumMap[0] = 1_000_000;
            calcDp();
            result = Math.min(result, dp[N][0]);

            rightSumMap[1][0] = temp2;
            updownSumMap[0] = temp3;
        }

        if (rightSumMap[0][N - 1] <= W && rightSumMap[1][N - 1] <= W) {
            dp[1][0] = dp[1][1] = 1;
            dp[1][2] = 2;
            int temp2 = rightSumMap[0][0];
            int temp4 = rightSumMap[1][0];
            int temp5 = updownSumMap[0];

            rightSumMap[0][0] = rightSumMap[1][0] = updownSumMap[0] = 1_000_000;
            calcDp();
            result = Math.min(result, dp[N - 1][2]);

            rightSumMap[0][0] = temp2;
            rightSumMap[1][0] = temp4;
            updownSumMap[0] = temp5;
        }

        if (N == 1) {
            result = updownSumMap[0] <= W ? 1 : 2;
        }

        return result;
    }

    private void calcDp() {

        for (int i = 2; i <= N; i++) {
            int up = rightSumMap[0][i - 2] <= W ? 1 : 2;
            int down = rightSumMap[1][i - 2] <= W ? 1 : 2;
            int ver = updownSumMap[i - 1] <= W ? 1 : 2;

            dp[i][0] = Math.min(
                    dp[i - 1][1] + up,
                    dp[i - 1][2] + 1
            );

            dp[i][1] = Math.min(
                    dp[i - 1][0] + down,
                    dp[i - 1][2] + 1
            );

            dp[i][2] = Math.min(
                    dp[i - 1][2] + ver,
                    Math.min(dp[i][0], dp[i][1]) + 1
            );
            dp[i][2] = Math.min(
                    dp[i][2],
                    dp[i - 2][2] + up + down
            );
        }
    }

    public void printResult(int result) throws IOException {
        bw.write(result + "\n");
        bw.flush();
    }
}
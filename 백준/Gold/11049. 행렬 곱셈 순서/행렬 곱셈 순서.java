import java.io.*;
import java.util.*;

public class Main {

    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int N;
    private static int[] matrixSize;
    private static long[][] dp;


    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        initN();
        initMatrixSize();
        printResult(findMinCalcCount());
        bw.flush();
        bw.close();
    }

    private void initN() throws IOException {
        N = Integer.parseInt(bf.readLine());
    }

    private void initMatrixSize() throws IOException {
        matrixSize = new int[N + 1];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());
            matrixSize[i] = row;
            matrixSize[i + 1] = col;
        }
    }

    private long findMinCalcCount() {
        dp = new long[N][N];
        for (int i = 2; i <= N; i++) {
            for (int j = 0; j < N - i + 1; j++) {
                dp[j][j + i - 1] = Long.MAX_VALUE;
                for (int k = j; k < j + i - 1; k++) {
                    long count = dp[j][k] + dp[k + 1][j + i - 1] +
                            ((long) matrixSize[j] * matrixSize[k + 1] * matrixSize[j + i]);
                    dp[j][j + i - 1] = Math.min(dp[j][j + i - 1], count);
                }
            }
        }
        return dp[0][N - 1];
    }

    private void printResult(long result) throws IOException {
        bw.write(result + "\n");
    }
}

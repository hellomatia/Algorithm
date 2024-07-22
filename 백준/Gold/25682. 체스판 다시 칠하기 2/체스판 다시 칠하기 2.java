import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static final BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private int N, M, K;
    private int[][] board;
    private int[][] scores;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        printAns(calcAns());
    }

    private void init() throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        scores = new int[N + 1][M + 1];
        for (int i = 0; i < N; i++) {
            String rowValue = bf.readLine();
            for (int j = 0; j < M; j++) {
                if ((i + j) % 2 == 0) {
                    if (rowValue.charAt(j) == 'B') {
                        board[i][j] = 1;
                    }
                } else {
                    if (rowValue.charAt(j) == 'W') {
                        board[i][j] = 1;
                    }
                }
                scores[i][j + 1] += scores[i][j] + board[i][j];
            }
        }
    }

    private String calcAns() {
        int ans = Integer.MAX_VALUE;

        for (int j = 0; j <= M - K; j++) {
            int score = 0;
            for (int i = 0; i < K; i++) {
                scores[i][j] = rowScore(i, j);
                score += scores[i][j];
            }
            ans = Math.min(ans, Math.min(score, K * K - score));
            for (int i = K; i < N; i++) {
                score -= scores[i - K][j];
                scores[i][j] = rowScore(i, j);
                score += scores[i][j];
                ans = Math.min(ans, Math.min(score, K * K - score));
            }
        }
        return ans + "";
    }

    private int rowScore(int x, int y) {
        return scores[x][y + K] - scores[x][y];
    }

    private void printAns(String ans) throws IOException {
        bw.write(ans + "\n");
        bw.flush();
    }
}

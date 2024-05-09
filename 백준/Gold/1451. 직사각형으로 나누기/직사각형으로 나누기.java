import java.util.*;
import java.io.*;

public class Main {

    private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private int N, M;
    private long[][] sum;
    private int[] divide;
    private long[] temp;
    private long ans;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        calcAns();
        printAns();
    }

    private void init() throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        sum = new long[N][M];
        temp = new long[3];
        divide = new int[2];

        for (int i = 0; i < N; i++) {
            String rowValue = bf.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = rowValue.charAt(j) - '0';

                if (i == 0 && j == 0) {
                    sum[0][0] = map[i][j];
                } else if (i == 0) {
                    sum[0][j] = sum[0][j - 1] + map[i][j];
                } else if (j == 0) {
                    sum[i][0] = sum[i - 1][0] + map[i][j];
                }
            }
        }

        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                sum[i][j] = sum[i][j - 1] + sum[i - 1][j] - sum[i - 1][j - 1] + map[i][j];
            }
        }
    }

    private void calcAns() {
        divideRow(0, 0);
        divideCol(0, 0);
        dividePoint();
    }

    private void divideRow(int count, int index) {
        if (count >= 2) {
            temp[0] = getSum(0, 0, M - 1, divide[0] - 1);
            temp[1] = getSum(0, divide[0], M - 1, divide[1] - 1);
            temp[2] = getSum(0, divide[1], M - 1, N - 1);

            ans = Math.max(ans, temp[0] * temp[1] * temp[2]);
            return;
        }

        for (int i = index; i + 1 < N; i++) {
            divide[count] = i + 1;
            divideRow(count + 1, i + 1);
        }
    }

    private void divideCol(int count, int index) {
        if (count >= 2) {
            temp[0] = getSum(0, 0, divide[0] - 1, N - 1);
            temp[1] = getSum(divide[0], 0, divide[1] - 1, N - 1);
            temp[2] = getSum(divide[1], 0, M - 1, N - 1);

            ans = Math.max(ans, temp[0] * temp[1] * temp[2]);
            return;
        }

        for (int i = index; i + 1 < M; i++) {
            divide[count] = i + 1;
            divideCol(count + 1, i + 1);
        }
    }

    private void dividePoint() {
        for (int i = 1; i <= N - 1; i++) {
            for (int j = 1; j <= M - 1; j++) {
                temp[0] = getSum(0, 0, j - 1, i - 1);
                temp[1] = getSum(j, 0, M - 1, i - 1);
                temp[2] = getSum(0, i, M - 1, N - 1);
                ans = Math.max(ans, temp[0] * temp[1] * temp[2]);

                temp[0] = getSum(0, 0, M - 1, i - 1);
                temp[1] = getSum(0, i, j - 1, N - 1);
                temp[2] = getSum(j, i, M - 1, N - 1);
                ans = Math.max(ans, temp[0] * temp[1] * temp[2]);

                temp[0] = getSum(0, 0, j - 1, N - 1);
                temp[1] = getSum(j, 0, M - 1, i - 1);
                temp[2] = getSum(j, i, M - 1, N - 1);
                ans = Math.max(ans, temp[0] * temp[1] * temp[2]);

                temp[0] = getSum(0, 0, j - 1, i - 1);
                temp[1] = getSum(0, i, j - 1, N - 1);
                temp[2] = getSum(j, 0, M - 1, N - 1);
                ans = Math.max(ans, temp[0] * temp[1] * temp[2]);
            }
        }
    }

    private long getSum(int x1, int y1, int x2, int y2) {
        if (x1 == 0 && y1 == 0) {
            return sum[y2][x2];
        } else if (x1 == 0) {
            return sum[y2][x2] - sum[y1 - 1][x2];
        } else if (y1 == 0) {
            return sum[y2][x2] - sum[y2][x1 - 1];
        }

        return sum[y2][x2] - sum[y2][x1 - 1] - sum[y1 - 1][x2] + sum[y1 - 1][x1 - 1];
    }

    private void printAns() throws IOException {
        bw.write(ans + "\n");
        bw.flush();
    }

}
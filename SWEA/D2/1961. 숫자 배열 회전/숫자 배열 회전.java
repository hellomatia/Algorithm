import java.io.*;
import java.util.*;

public class Solution {

    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int N;
    private static int[][] matrix;

    public static void main(String[] args) throws IOException {
        new Solution().solution();
    }

    private void solution() throws IOException {
        int testCase = Integer.parseInt(bf.readLine());
        for (int t = 1; t <= testCase; t++) {
            init();
            printResult(t, calcResult());
        }
        bw.flush();
        bw.close();
    }

    private void init() throws IOException {
        N = Integer.parseInt(bf.readLine());
        matrix = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < N; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private String calcResult() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = N - 1; j >= 0; j--) {
                sb.append(matrix[j][i]);
            }
            sb.append(" ");
            for (int j = N - 1; j >= 0; j--) {
                sb.append(matrix[N - 1 - i][j]);
            }
            sb.append(" ");
            for (int j = 0; j < N; j++) {
                sb.append(matrix[j][N - 1 - i]);
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    private void printResult(int testCase, String result) throws IOException {
        bw.write("#"+testCase + "\n" + result);
    }
}

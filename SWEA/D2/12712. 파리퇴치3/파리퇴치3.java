import java.io.*;
import java.util.*;

public class Solution {

    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static final int[] dirX = {0, 0, 1, -1, 1, 1, -1, -1};
    private static final int[] dirY = {1, -1, 0, 0, 1, -1, 1, -1};

    private static int N;
    private static int M;
    private static int[][] map;

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
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private int calcResult() {
        int result = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                result = Math.max(result, calcCount(i, j));
            }
        }
        return result;
    }

    private int calcCount(int x, int y) {
        int count1 = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 1; j < M; j++) {
                int nextX = x + j * dirX[i];
                int nextY = y + j * dirY[i];
                if (0 <= nextX && nextX < N && 0 <= nextY && nextY < N) {
                    count1 += map[nextX][nextY];
                }
            }
        }
        int count2 = 0;
        for (int i = 4; i < 8; i++) {
            for (int j = 1; j < M; j++) {
                int nextX = x + j * dirX[i];
                int nextY = y + j * dirY[i];
                if (0 <= nextX && nextX < N && 0 <= nextY && nextY < N) {
                    count2 += map[nextX][nextY];
                }
            }
        }
        return map[x][y] + Math.max(count1, count2);
    }

    private void printResult(int testCase, int result) throws IOException {
        bw.write("#"+testCase + " " + result + "\n");
    }
}

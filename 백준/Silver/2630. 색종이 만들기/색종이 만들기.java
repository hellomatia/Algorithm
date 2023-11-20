import java.io.*;
import java.util.*;

public class Main {

    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int N;
    private static int[][] map;
    private static int[] colorCount = new int[2];

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        findSameColorCount(0, 0, N);
        printResult();
        bw.flush();
        bw.close();
    }

    private void init() throws IOException {
        N = Integer.parseInt(bf.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private void findSameColorCount(int x, int y, int length) {
        if (isSameColor(x, y, length)) {
            colorCount[map[x][y]]++;
            return;
        }

        int nextLength = length / 2;

        findSameColorCount(x, y, nextLength);
        findSameColorCount(x + nextLength, y, nextLength);
        findSameColorCount(x, y + nextLength, nextLength);
        findSameColorCount(x + nextLength, y + nextLength, nextLength);
    }

    private boolean isSameColor(int x, int y, int length) {
        if (length == 1) {
            return true;
        }
        int color = map[x][y];
        for (int i = x; i < x + length; i++) {
            for (int j = y; j < y + length; j++) {
                if (color != map[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    private void printResult() throws IOException {
        bw.write(colorCount[0] + "\n");
        bw.write(colorCount[1] + "\n");
    }
}
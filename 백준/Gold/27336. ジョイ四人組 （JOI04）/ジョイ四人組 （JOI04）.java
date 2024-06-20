import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    private final static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private final static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private int N;
    private int[][] heights;
    private int minGap = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        for (int i = 0; i < N; i++) {
            calcAns(heights[0][i], heights[0][i], 1);
        }
        printAns(minGap + "");
    }

    private void init() throws IOException {
        N = Integer.parseInt(bf.readLine());
        heights = new int[4][N];
        for (int i = 0; i < 4; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < N; j++) {
                heights[i][j] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(heights[i]);
        }
    }

    private void calcAns(int minHeight, int maxHeight, int classNum) {
        int gap = maxHeight - minHeight;
        if (classNum == 4) {
            minGap = Math.min(gap, minGap);
            return;
        }

        int mid = (minHeight + maxHeight) / 2;
        int idx = Arrays.binarySearch(heights[classNum], mid);
        if (idx < 0) {
            idx = -idx - 1;
        }
        if (idx >= N) {
            idx = N - 1;
        }

        int newMinHeight = Math.min(minHeight, heights[classNum][idx]);
        int newMaxHeight = Math.max(maxHeight, heights[classNum][idx]);

        calcAns(newMinHeight, newMaxHeight, classNum + 1);

        if (idx > 0) {
            newMinHeight = Math.min(minHeight, heights[classNum][idx - 1]);
            newMaxHeight = Math.max(maxHeight, heights[classNum][idx - 1]);
            calcAns(newMinHeight, newMaxHeight, classNum + 1);
        }
    }

    private void printAns(String ans) throws IOException {
        bw.write(ans + "\n");
        bw.flush();
    }
}
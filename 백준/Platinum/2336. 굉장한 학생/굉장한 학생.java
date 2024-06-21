import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private final static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private final static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int[] segTree;
    private static int N, firstLeafNodeIdx = 1, MAXINT = 1_000_000_000;
    private static int[][] scores;
    private static int[][] v;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        calcAns();
    }

    private void init() throws IOException {
        N = Integer.parseInt(bf.readLine());
        segTree = new int[1_048_576];
        Arrays.fill(segTree, MAXINT);
        v = new int[N][3];
        scores = new int[3][N];

        while (firstLeafNodeIdx < N) firstLeafNodeIdx *= 2;

        for (int i = 0; i < 3; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < N; j++) {
                int student = Integer.parseInt(st.nextToken()) - 1;
                scores[i][j] = student;
                v[student][i] = j;
            }
        }

        Arrays.sort(v, (a, b) -> Integer.compare(a[0], b[0]));
    }

    private void calcAns() throws IOException {
        int result = 0;

        for (int i = 0; i < N; i++) {
            if (findMin(0, v[i][1], 1, 0, firstLeafNodeIdx - 1) > v[i][2]) {
                result++;
            }
            setAncestorNode(v[i][1], v[i][2]);
        }

        printAns(result);
    }

    private void setAncestorNode(int idx, int val) {
        int tmpIdx = idx + firstLeafNodeIdx;
        segTree[tmpIdx] = val;
        while (tmpIdx > 1) {
            tmpIdx /= 2;
            segTree[tmpIdx] = Math.min(segTree[tmpIdx * 2], segTree[tmpIdx * 2 + 1]);
        }
    }

    private int findMin(int targetL, int targetR, int nodeNum, int curL, int curR) {
        if (targetL > curR || curL > targetR) return MAXINT;
        if (targetL <= curL && curR <= targetR) return segTree[nodeNum];
        int mid = (curL + curR) / 2;
        return Math.min(findMin(targetL, targetR, nodeNum * 2, curL, mid),
                findMin(targetL, targetR, nodeNum * 2 + 1, mid + 1, curR));
    }

    private void printAns(int ans) throws IOException {
        bw.write(ans + "\n");
        bw.flush();
    }
}
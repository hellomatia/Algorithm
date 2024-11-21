import java.io.*;
import java.util.*;

public class Main {
    static List<Integer> move;
    static int[][][] dp;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            move = new ArrayList<>();
            init(br, move);

            if (move.size() == 0) {
                bw.write("0");
                bw.flush();
                return;
            }

            dp = new int[5][5][move.size()];
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    Arrays.fill(dp[i][j], -1);
                }
            }

            bw.write(String.valueOf(solve(0, 0, 0)));
            bw.flush();
        }
    }

    private void init(BufferedReader br, List<Integer> positions) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()) {
            int pos = Integer.parseInt(st.nextToken());
            if (pos == 0) break;
            positions.add(pos);
        }
    }

    private int solve(int left, int right, int cnt) {
        if (cnt == move.size()) return 0;
        if (dp[left][right][cnt] != -1) return dp[left][right][cnt];

        int leftMove = solve(move.get(cnt), right, cnt + 1) + calcCost(left, move.get(cnt));
        int rightMove = solve(left, move.get(cnt), cnt + 1) + calcCost(right, move.get(cnt));
        dp[left][right][cnt] = Math.min(leftMove, rightMove);

        return dp[left][right][cnt];
    }

    private int calcCost(int from, int to) {
        if (from == to) return 1;
        if (from == 0) return 2;
        int diff = Math.abs(from - to);
        if (diff == 1 || diff == 3) return 3;
        return 4;
    }
}
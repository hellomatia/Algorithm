import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            List<Integer> positions = new ArrayList<>();
            init(br, positions);

            if (positions.size() == 0) {
                bw.write("0");
                bw.flush();
                return;
            }

            // DP 배열: [현재 단계][왼쪽 발 위치][오른쪽 발 위치]
            int[][][] dp = new int[positions.size() + 1][5][5];
            for (int[][] row : dp) {
                for (int[] subrow : row) {
                    Arrays.fill(subrow, 100000000);
                }
            }

            // 초기 상태: 두 발 모두 0 위치
            dp[0][0][0] = 0;

            for (int step = 0; step < positions.size(); step++) {
                int target = positions.get(step);

                for (int left = 0; left < 5; left++) {
                    for (int right = 0; right < 5; right++) {
                        // 유효하지 않은 상태 건너뛰기
                        if (dp[step][left][right] >= 100000000) continue;

                        // 두 발이 같은 위치면 건너뛰기 (첫 단계 제외)
                        if (left == right && step > 0) continue;

                        // 왼쪽 발 이동
                        if (right != target) {
                            int newCost = dp[step][left][right] + calcCost(left, target);
                            dp[step+1][target][right] = Math.min(dp[step+1][target][right], newCost);
                        }

                        // 오른쪽 발 이동
                        if (left != target) {
                            int newCost = dp[step][left][right] + calcCost(right, target);
                            dp[step+1][left][target] = Math.min(dp[step+1][left][target], newCost);
                        }
                    }
                }
            }

            // 마지막 단계에서 최소 힘 찾기
            int minForce = 100000000;
            for (int left = 0; left < 5; left++) {
                for (int right = 0; right < 5; right++) {
                    // 두 발이 같은 위치면 건너뛰기
                    if (left == right && positions.size() > 0) continue;
                    minForce = Math.min(minForce, dp[positions.size()][left][right]);
                }
            }

            bw.write(String.valueOf(minForce));
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

    private int calcCost(int from, int to) {
        if (from == to) return 1;
        if (from == 0) return 2;
        int diff = Math.abs(from - to);
        if (diff == 1 || diff == 3) return 3;
        return 4;
    }
}
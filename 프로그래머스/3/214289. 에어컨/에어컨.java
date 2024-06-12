import java.util.*;

class Solution {
    private static final int INF = 987654321;
    
    public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {
        if (temperature > t2) {
            temperature = t1 - (temperature - t2);
        }
        t1 -= temperature;
        t2 -= temperature;
        temperature = 0;
        
        int[][] dp = new int[onboard.length][t2 + 2];
        for (int[] cost : dp) {
            Arrays.fill(cost, INF);
        }
        
        dp[0][0] = 0;
        for (int i = 1; i < onboard.length; i++) {
            for (int j = 0; j <= t2 + 1; j++) {
                if (onboard[i] == 1 && (j < t1 || j > t2)) continue;
                int min = INF;
                if (j == 0) {
                    min = Math.min(min, dp[i-1][j]);
                    if (j + 1 <= t2 + 1) min = Math.min(min, dp[i-1][j+1]);
                }
                else {
                    if (j - 1 >= 0) min = Math.min(min, dp[i-1][j-1] + a);
                    min = Math.min(min, dp[i-1][j] + b);
                    if (j + 1 <= t2 + 1) min = Math.min(min, dp[i-1][j+1]);
                }
                dp[i][j] = min;
            }
        }
        
        int answer = Integer.MAX_VALUE;
        for (int j = 0; j <= t2 + 1; j++)
            answer = Math.min(answer, dp[onboard.length-1][j]);
        return answer;
    }
}
import java.io.*;
import java.util.*;
public class Main {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int[][] dp;

    public void solution() throws IOException {

        int tc = Integer.parseInt(bf.readLine());
        int[] nums = new int[tc];
        int maxNum = 0;

        for (int i = 0; i < tc; i++) {
            int n = Integer.parseInt(bf.readLine());
            nums[i] = n;

            maxNum = Math.max(maxNum, n);
        }

        dp = new int[maxNum+1][2];

        fibonacci(maxNum);

        for (int num : nums) {
            bw.write(dp[num][0] + " " + dp[num][1] + "\n");
        }

        bw.flush();
        bw.close();
    }

    public void fibonacci(int n) {

        dp[0][0] = 1;
        if(n == 0) return;

        dp[1][1] = 1;
        if(n==1) return;

        for (int i = 2; i <= n; i++) {
            dp[i][0] = dp[i-1][0] + dp[i-2][0];
            dp[i][1] = dp[i-1][1] + dp[i-2][1];
        }
    }



    public static void main (String[] args) throws IOException {
        new Main().solution();
    }
}
import java.io.*;
import java.util.*;
public class Main {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public void solution() throws IOException {

        int T;

        T = Integer.parseInt(bf.readLine());

        int[] nums = new int[T];
        int max = 0;

        for (int i = 0; i < T; i++) {
            int n = Integer.parseInt(bf.readLine());
            nums[i] = n;
            max = Math.max(max, n);
        }

        long[] dp = new long[max+1];
        if(max == 1) {
            dp[1] = 1;
        } else if (max == 2) {
            dp[1] = 1;
            dp[2] = 2;
        } else if (max >= 3) {
            dp[1] = 1;
            dp[2] = 2;
            dp[3] = 4;
        }

        for (int i = 4; i <= max; i++) {
            dp[i] = (dp[i-3] + dp[i-2] + dp[i-1]) % 1_000_000_009;
        }

        for (int n : nums) {
            bw.write(dp[n] + "\n");
        }

        bw.flush();
        bw.close();
    }



    public static void main (String[] args) throws IOException {
        new Main().solution();
    }
}
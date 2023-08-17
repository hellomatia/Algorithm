import java.io.*;
import java.util.*;
public class Main {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public void solution() throws IOException {

        int N = Integer.parseInt(bf.readLine());
        int[] nums = new int[N];

        StringTokenizer st = new StringTokenizer(bf.readLine());

        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        long[][] dp = new long[N][21];

        dp[0][nums[0]]++;

        for (int i = 1; i < N-1; i++) {
            for (int j = 0; j <= 20; j++) {
                if(dp[i-1][j] == 0) continue;

                int addition = j + nums[i];
                int subtraction = j - nums[i];

                if (addition <= 20) {
                    dp[i][addition] += dp[i-1][j];
                }

                if (0 <= subtraction) {
                    dp[i][subtraction] += dp[i-1][j];
                }
            }
        }


        bw.write(dp[N-2][nums[N-1]] + "\n");

        bw.flush();
        bw.close();
    }


    public static void main (String[] args) throws IOException {
        new Main().solution();
    }
}
import java.io.*;
import java.security.spec.RSAOtherPrimeInfo;
import java.util.*;
public class Main {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public void solution() throws IOException {

        int N = Integer.parseInt(bf.readLine());

        int[] dp= new int[N+1];
        int[] idx = new int[N+1];

        for(int i = 2; i <= N; i++) {
            dp[i] = Integer.MAX_VALUE;
        }

        dp[1] = 0;
        for (int i = 1; i < N; i++) {
            int case1 = i * 3;
            int case2 = i * 2;
            int case3 = i + 1;

            if (case1 <= N && dp[i] + 1 < dp[case1]) {
                dp[case1] = dp[i] + 1;
                idx[case1] = i;
            }
            if (case2 <= N && dp[i] + 1 < dp[case2]) {
                dp[case2] = dp[i] + 1;
                idx[case2] = i;
            }
            if (case3 <= N && dp[i] + 1 < dp[case3]) {
                dp[case3] = dp[i] + 1;
                idx[case3] = i;
            }
        }

        bw.write(dp[N] + "\n");

        int next = N;

        for (int i = 0; i <= dp[N]; i++) {
            bw.write(next + " ");
            next = idx[next];
        }

        bw.flush();
        bw.close();

    }

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }
}
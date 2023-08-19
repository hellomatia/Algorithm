import java.io.*;
import java.util.*;
public class Main {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public void solution() throws IOException {

        int N = Integer.parseInt(bf.readLine());
        int[] childOrder = new int[N];

        int[] dp = new int[N];

        for (int i = 0; i < N; i++) {
            childOrder[i] = Integer.parseInt(bf.readLine());
        }

        int max = 0;

        for (int i = 0; i < N; i++) {
            dp[i] = 1;
            for (int j = i-1; j >= 0; j--) {
                if (childOrder[j] < childOrder[i] && dp[i] <= dp[j]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
            max = Math.max(dp[i], max);
        }

        bw.write((N - max) + "\n");

        bw.flush();
        bw.close();
    }


    public static void main (String[] args) throws IOException {
        new Main().solution();
    }
}
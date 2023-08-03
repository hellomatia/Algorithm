import java.io.*;
import java.util.*;
public class Main {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public void solution() throws IOException {

        int T = Integer.parseInt(bf.readLine());

        for ( int i = 0; i < T; i++) {
            int K = Integer.parseInt(bf.readLine());
            int[] novel = new int[K + 1];
            int[][] dp = new int[K + 1][K + 1];
            int[] sum = new int[K + 1];

            StringTokenizer st = new StringTokenizer(bf.readLine());
            for (int page = 1; page <= K; page++) {
                int cost = Integer.parseInt(st.nextToken());
                novel[page] = cost;
                sum[page] = sum[page - 1] + novel[page];
            }

            for (int n = 1; n <= K; n++) {
                for (int from = 1; from + n <= K; from++) {
                    int to = from + n;
                    dp[from][to] = Integer.MAX_VALUE;
                    for (int divide = from; divide < to; divide++) {
                        dp[from][to] = Math.min(dp[from][to], dp[from][divide] + dp[divide + 1][to] + sum[to] - sum[from - 1]);
                    }
                }
            }

            bw.write(dp[1][K] + "\n");


        }

        bw.flush();
        bw.close();

    }

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }
}
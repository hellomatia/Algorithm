import java.io.*;
import java.util.*;
public class Main {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public void solution() throws IOException {

        int N = Integer.parseInt(bf.readLine());

        int[] dp = new int[N+1];

        StringTokenizer st = new StringTokenizer(bf.readLine());

        for (int i = 1; i <= N; i++) {
            dp[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= N; i++) {
            for (int j = i-1; j >= 0; j--) {
                dp[i] = Math.min(dp[j] + dp [i - j], dp[i]);
            }
        }

        bw.write(dp[N] + "\n");

        bw.flush();
        bw.close();
    }


    public static void main (String[] args) throws IOException {
        new Main().solution();
    }
}
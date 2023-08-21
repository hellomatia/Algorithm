import java.io.*;
import java.math.BigInteger;
public class Main {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public void solution() throws IOException {

        int n = Integer.parseInt(bf.readLine());

        BigInteger[] dp = new BigInteger[n+1];

        dp[0] = BigInteger.ZERO;
        if (n >= 1) {
            dp[1] = BigInteger.ONE;
        }

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1].add(dp[i - 2]);
        }

        bw.write(dp[n]+"\n");

        bw.flush();
        bw.close();
    }


    public static void main (String[] args) throws IOException {
        new Main().solution();
    }
}
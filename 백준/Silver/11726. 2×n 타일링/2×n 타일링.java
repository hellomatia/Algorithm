import java.io.*;
import java.util.*;

public class Main {
    int n;
    public void solution() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(bf.readLine());

        int[] dp = new int[1001];
        dp[1] = 1;
        dp[2] = 2;


        for(int i=3; i<=n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
            dp[i] %= 10007;
        }

        bw.write(dp[n]+"\n");
        bw.flush();
        bw.close();


    }

    public static void main (String[] args) throws IOException {
        new Main().solution();

    }
}
import java.io.*;
import java.util.*;

public class Main {
    int N;
    long[][] dp;

    public void solution() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(bf.readLine());
        dp = new long[N+1][10];

        for(int i=1; i<10; i++) {
            dp[1][i] = 1;
        }

        for(int i=2; i<=N; i++) {
            for(int j=0; j<10; j++) {
                if(j==0) {
                    dp[i][0] = dp[i-1][1];
                }

                else if(j==9) {
                    dp[i][9] = dp[i-1][8];
                }

                else {
                    dp[i][j] = (dp[i-1][j-1]+dp[i-1][j+1]) % 1000000000;
                }
            }
        }

        long ans = 0;
        for(int i=0; i<10; i++) {
            ans += dp[N][i];
        }
        
        ans %= 1000000000;
        
        bw.write(ans+"\n");

        bw.flush();
        bw.close();
    }

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }
}
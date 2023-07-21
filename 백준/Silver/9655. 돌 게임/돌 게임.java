import java.io.*;
import java.util.*;

public class Main {

    static int[] dp;
    public void solution() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(st.nextToken());

        dp = new int[N+1];

        bottomUp(N);

        if (dp[N] == 1) {
            bw.write("SK");
        }
        if (dp[N] == 2) {
            bw.write("CY");
        }




        bw.flush();
        bw.close();

    }

    public void bottomUp (int n) {
        if (n < 4) {
            if (n%2==0) {
                // 2 == CY;
                dp[n] = 2;
                return;
            }
            else if (n%2==1) {
                // 1 == SK
                dp[n] = 1;
                return;
            }
        }

        bottomUp(n-4);
        dp[n] = dp[n-4];
    }


    public static void main (String[] args) throws IOException {
        new Main().solution();
    }
}
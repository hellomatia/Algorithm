import java.io.*;
import java.security.spec.RSAOtherPrimeInfo;
import java.util.*;
public class Main {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public void solution() throws IOException {

        int n;

        n = Integer.parseInt(bf.readLine());

        int[] baskets = new int[n];

        StringTokenizer st = new StringTokenizer(bf.readLine());

        for (int i = 0; i < n; i++) {
             baskets[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[n];

        //LIS
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = i-1; j >= 0; j--) {
                if (baskets[j] < baskets[i] && dp[j] >= dp[i]) {
                    dp[i] = dp[j] + 1;
                }
            }
        }

        //find Max
        int maxCnt = 0;

        for (int i = 0; i < n; i++) {
            maxCnt = Math.max(maxCnt, dp[i]);
        }



        bw.write(maxCnt + "\n");

        bw.flush();
        bw.close();
    }



    public static void main (String[] args) throws IOException {
        new Main().solution();
    }
}
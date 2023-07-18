import java.io.*;
import java.util.*;

public class Main {
    public void solution() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int A = Integer.parseInt(bf.readLine());
        int[] dp = new int[A];
        int[] num = new int[A];

        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < A; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        int maxLds = 1;

        for(int i = 0; i < A; i++) {
            dp[i] = 1;
            for (int j = i-1; j >= 0; j--) {
                if (num[i] < num[j] && dp[j] >= dp[i]) {
                    dp[i] = dp[j] + 1;
                    maxLds = Math.max(maxLds, dp[i]);
                }
            }
        }

        bw.write(maxLds+"\n");

        bw.flush();
        bw.close();

    }


    public static void main (String[] args) throws IOException {
        new Main().solution();
    }
}
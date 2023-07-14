import java.io.*;
import java.util.*;

public class Main {
    public void solution() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


        int N = Integer.parseInt(bf.readLine());

        StringTokenizer st = new StringTokenizer(bf.readLine());

        int[] dp = new int[N+1];
        int[] num = new int[N+1];

        for (int i = 1; i <= N; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        dp[1] = num[1];

        for (int i = 2; i <= N; i++) {
            dp[i] = num[i];
            for (int j = 1; j < i; j++) {
                if (num[i]>num[j]) {
                    dp[i] = Math.max(dp[j]+num[i], dp[i]);
                }
            }
        }

        int max = Integer.MIN_VALUE;
        for (int i = 1; i <= N; i++) {
            max = Math.max(max, dp[i]);
        }

        bw.write(max+"\n");

        bw.flush();
        bw.close();

    }


    public static void main (String[] args) throws IOException {
        new Main().solution();
    }
}
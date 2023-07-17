import java.io.*;
import java.util.*;

public class Main {
    public void solution() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int num = Integer.parseInt(bf.readLine());
        int[] dp = new int[num+1];

        dp[0] = 1;
        if (num >= 2) {
            dp[2] = 3;
        }

        for (int i = 4; i <= num; i+=2) {
            dp[i] = dp[i-2] * 3;
            for (int j = i - 4; j >= 0; j -= 2) {
                dp[i] = dp[i] + (dp[j] * 2);
            }
        }

        bw.write(dp[num]+"\n");

        bw.flush();
        bw.close();

    }


    public static void main (String[] args) throws IOException {
        new Main().solution();
    }
}
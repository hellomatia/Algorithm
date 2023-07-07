import java.io.*;
import java.util.*;

public class Main {
    String[] str = new String[2];
    int length1;
    int length2;
    int[][] dp;

    public void solution() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        str[0] = bf.readLine();
        str[1] = bf.readLine();

        length1 = str[0].length();
        length2 = str[1].length();

        dp = new int[length1+1][length2+1];

        bottomUp();

        bw.write(dp[length1][length2]+"\n");

        bw.flush();
        bw.close();
    }

    public void bottomUp() {
        for (int i=1; i<=length1; i++) {
            for (int j=1; j<=length2; j++) {
                if(str[0].charAt(i-1) == str[1].charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

    }

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }
}
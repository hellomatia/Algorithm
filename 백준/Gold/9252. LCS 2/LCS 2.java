import java.io.*;
import java.security.spec.RSAOtherPrimeInfo;
import java.util.*;
public class Main {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int[][] dp;
    static String str1, str2;
    static int str1Cur, str2Cur;
    public void solution() throws IOException {

        str1 = bf.readLine();
        str2 = bf.readLine();

        str1Cur = str1.length();
        str2Cur = str2.length();

        int maxLength = getMaxLength();

        StringBuilder sb = new StringBuilder();

        while(str1Cur != 0 && str2Cur != 0) {
            if (str1.charAt(str1Cur - 1) == str2.charAt(str2Cur - 1)) {
                sb.insert(0, str1.charAt(str1Cur - 1));
                str1Cur--;
                str2Cur--;
            } else if (dp[str1Cur][str2Cur] == dp[str1Cur - 1][str2Cur]) {
                str1Cur--;
            } else if (dp[str1Cur][str2Cur] == dp[str1Cur][str2Cur - 1]) {
                str2Cur--;
            }
        }

        bw.write(maxLength + "\n" + sb.toString());

        bw.flush();
        bw.close();

    }

    public int getMaxLength() {
        dp = new int[str1Cur+1][str2Cur+1];
        for (int i = 1; i <= str1Cur; i++) {
            for (int j = 1; j <= str2Cur; j++) {
                if (str1.charAt(i-1) == str2.charAt(j-1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
                else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[str1Cur][str2Cur];
    }

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }
}
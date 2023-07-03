import java.io.*;
import java.util.*;

public class Main {
    int T; // 테스트 케이스의 개수
    int[][] dp;

    public void solution() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        T = Integer.parseInt(bf.readLine());
        dp = new int[31][31];

        for(int i=1; i<=30; i++) {
            dp[i][i] = 1;
            dp[i][0] = 1;
        }

        for(int i=2; i<=30; i++) {
            for(int j=1; j<=30; j++) {
                dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
            }
        }

        for(int testCase = 0; testCase<T; testCase++) {
            st = new StringTokenizer(bf.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            bw.write(dp[M][N]+"\n");
        }

        bw.flush();
        bw.close();
    }

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }
}
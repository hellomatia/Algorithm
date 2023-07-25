import java.io.*;
import java.util.*;
public class Main {
    int N;

    public void solution() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(bf.readLine());

        int[][] dp = new int[N+1][3];


        //dp[][0]일 때는 사자가 존재하지 않을 경우
        dp[1][0] = 1;
        //dp[][1]일 때는 사자가 오른쪽에 존재 경우
        dp[1][1] = 1;
        //dp[][2]일 때는 사자가 왼쪽에 존재 경우
        dp[1][2] = 1;

        for (int i = 2; i <= N; i++) {
            dp[i][0] = (dp[i-1][0] + dp[i-1][1] + dp[i-1][2]) % 9901;
            dp[i][1] = (dp[i-1][0] + dp[i-1][2]) % 9901;
            dp[i][2] = (dp[i-1][0] + dp[i-1][1]) % 9901;
        }

        int ans = (dp[N][0] + dp[N][1] + dp[N][2]) % 9901;

        bw.write(ans + "\n");
        bw.flush();
        bw.close();

    }


    public static void main (String[] args) throws IOException {
        new Main().solution();
    }
}
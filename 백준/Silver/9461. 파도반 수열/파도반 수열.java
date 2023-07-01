import java.io.*;
import java.util.*;

public class Main {
    int T; // TestCase 갯수
    long[] dp;

    public void solution() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        T = Integer.parseInt(bf.readLine());
        dp = new long[101];

        dp[1] = 1;
        dp[2] = 1;
        dp[3] = 1;
        dp[4] = 2;
        dp[5] = 2;
        for(int i=6; i<=100; i++) {
            dp[i] = dp[i-1] + dp[i-5];
        }

        for(int i=0; i<T; i++) {
            int N = Integer.parseInt(bf.readLine());
            bw.write(dp[N]+"\n");
        }

        /*
        첫 삼각형은 정삼각형으로 변의 길이는 1이다.
        그 다음에는 다음과 같은 과정으로 정삼각형을 계속 추가한다.
        나선에서 가장 긴 변의 길이를 k라 했을 때, 그 변에 길이가 k인 정삼각형을 추가한다.
         */

        bw.flush();
        bw.close();
    }

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }
}
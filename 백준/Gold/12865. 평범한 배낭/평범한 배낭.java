import java.io.*;
import java.util.*;

public class Main {
    int N; // 물품의 수
    int K; // 준서가 버틸 수 있는 무게
    int[][] dp;
    int[][] bags;

    public void solution() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dp = new int[N+1][K+1];
        bags = new int[N+1][2];

        for (int i=1; i<=N; i++) {
            st = new StringTokenizer(bf.readLine());
            // 각 물건의 무게
            bags[i][0] = Integer.parseInt(st.nextToken());
            // 해당 물건의 가치
            bags[i][1] = Integer.parseInt(st.nextToken());
        }

        bottomUp();

        bw.write(dp[N][K]+"\n");

        bw.flush();
        bw.close();
    }

    public void bottomUp() {

        for (int i=1; i<=N; i++) {
            for (int j=1; j<=K; j++) {

                if(bags[i][0] > j) {
                    dp[i][j] = dp[i-1][j];
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-bags[i][0]]+bags[i][1]);
                }
            }
        }
    }

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }
}
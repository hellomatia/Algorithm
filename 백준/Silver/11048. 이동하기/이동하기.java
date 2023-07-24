import java.io.*;
import java.util.*;
public class Main {
    int N, M;
    int[][] map;
    int[][] dp;
    public void solution() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(bf.readLine());


        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N+1][M+1];
        dp = new int[N+1][M+1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 1; j <= M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]) + map[i][j];
            }
        }

        bw.write(dp[N][M] + "\n");
        bw.flush();
        bw.close();

    }


    public static void main (String[] args) throws IOException {
        new Main().solution();
    }
}
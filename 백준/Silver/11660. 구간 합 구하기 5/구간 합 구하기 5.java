import java.io.*;
import java.util.*;

public class Main {
    public void solution() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] dp = new int[N+1][N+1];

        for (int row = 1; row <= N; row++) {
            st = new StringTokenizer(bf.readLine());
            for (int col = 1; col <= N; col++) {
                int num = Integer.parseInt(st.nextToken());
                dp[row][col] = dp[row][col-1] + num;
            }
        }

        for (int cnt=0; cnt < M; cnt++) {
            st = new StringTokenizer(bf.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            int sum = 0;
            for (int row = x1; row <= x2; row++) {
                sum += (dp[row][y2] - dp[row][y1-1]);
            }

            bw.write(sum+"\n");

        }

        bw.flush();
        bw.close();

    }


    public static void main (String[] args) throws IOException {
        new Main().solution();
    }
}
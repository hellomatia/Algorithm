import java.io.*;
import java.util.*;

public class Main {
    public void solution() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /*
            1번 집의 색은 2번 집의 색과 같지 않아야 한다.
            N번 집의 색은 N-1번 집의 색과 같지 않아야 한다.
            i(2 ≤ i ≤ N-1)번 집의 색은 i-1번, i+1번 집의 색과 같지 않아야 한다.
        */
        // 색은 세가지 R, G, B
        // R => 0, G => 1, B => 2

        int N = Integer.parseInt(bf.readLine());

        int[][] houses = new int[N][3];

        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            houses[i][0] = Integer.parseInt(st.nextToken());
            houses[i][1] = Integer.parseInt(st.nextToken());
            houses[i][2] = Integer.parseInt(st.nextToken());
        }


        int[][] dp = new int[N][3];
        dp[0][0] = houses[0][0];
        dp[0][1] = houses[0][1];
        dp[0][2] = houses[0][2];
        for(int i=1; i<N; i++) {
            dp[i][0] = houses[i][0] + Math.min(dp[i-1][1], dp[i-1][2]);
            dp[i][1] = houses[i][1] + Math.min(dp[i-1][2], dp[i-1][0]);
            dp[i][2] = houses[i][2] + Math.min(dp[i-1][1], dp[i-1][0]);
        }

        bw.write(Math.min(Math.min(dp[N-1][0], dp[N-1][1]), dp[N-1][2])+"\n");





        bw.flush();
        bw.close();
    }

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }
}
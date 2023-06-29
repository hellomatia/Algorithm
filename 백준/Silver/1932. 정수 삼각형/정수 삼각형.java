import java.io.*;
import java.util.*;

public class Main {
    int n; // 삼각형의 크기
    int[][] triangle;
    int[][] dp;
    public void solution() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(bf.readLine());
        triangle = new int[n][n];
        dp = new int[n][n];

        StringTokenizer st;

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(bf.readLine());
            for(int j=0; j<i+1; j++) {
                triangle[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp[n-1] = triangle[n-1].clone();

        /*
        * Top - Down(재귀)방식이 있고, Bottom-Up(반복문)방식이 있다.
         */

        int max = 0;

        //Top-Down
        /*
        for(int i=0; i<n; i++) {
            LISTopDown(i);
        }
         */

        //Bottom-Up
        if(n!=1) {
            LISBottomUp();
        }


        bw.write(dp[0][0]+"\n");

        bw.flush();
        bw.close();
    }

    public int LISTopDown(int layer) {

        return dp[layer][0];
    }

    public void LISBottomUp() {

        for(int i=n-2; i>=1; i--) {
            for(int j=0; j<=i; j++) {
                dp[i][j] = Math.max(dp[i+1][j+1], dp[i+1][j]) + triangle[i][j];
            }
        }
        dp[0][0] = Math.max(dp[1][0], dp[1][1]) + triangle[0][0];

    }

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }
}
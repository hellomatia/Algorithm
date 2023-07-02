import java.io.*;
import java.util.*;

public class Main {
    int n; // 2xn의 직사각형의 크기
    int[] dp;

    public void solution() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(bf.readLine());
        dp = new int[n+1];

        if(n<=2) {
            if(n==1) {
                bw.write("1");
            } else {
                bw.write("3");
            }
        } else {
            bottomUp();
            bw.write(dp[n]+"\n");
        }

        bw.flush();
        bw.close();
    }

    public void bottomUp(){
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 3;

        for(int i=3; i<=n; i++) {
            dp[i] = (dp[i-1] + 2*dp[i-2])%10007;
        }
    }

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }
}
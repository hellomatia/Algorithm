import java.io.*;
import java.util.StringTokenizer;

public class Main {
    int num;
    public void solution() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        num = Integer.parseInt(bf.readLine());

        int[] dp = new int[50001];
        dp[0] = 0;
        dp[1] = 1;
        for(int i=2; i<=num; i++) {
            dp[i] = 5;
        }
        for(int i=2; i<=num; i++) {
            for(int j=1; j*j <=i; j++) {
                dp[i] = Math.min(dp[i], dp[i-j*j]+1);
            }
        }


        bw.write(dp[num]+"\n");


        bw.flush();
        bw.close();
    }


    public static void main (String[] args) throws IOException {
        new Main().solution();
    }
}
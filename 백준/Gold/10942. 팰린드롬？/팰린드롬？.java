import java.io.*;
import java.security.spec.RSAOtherPrimeInfo;
import java.util.*;
public class Main {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int N;
    int M;
    int[] nums;
    int[][] dp;

    public void solution() throws IOException {

        N = Integer.parseInt(bf.readLine());

        nums = new int[N+1];

        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 1; i <= N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        M = Integer.parseInt(bf.readLine());
        dp = new int[N+1][N+1];
        for (int i = 1; i <= N; i++) {
            dp[i][i] = 1;
            if(i < N && nums[i] == nums[i + 1]) {
                dp[i][i + 1]= 1;
            }
        }

        for(int i = 2; i < N; i++){
            for(int j = 1; j <= N - i; j++){
                if(nums[j] == nums[j + i] && dp[j + 1][j + i - 1] == 1)
                    dp[j][j + i] = 1;
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            bw.write(dp[start][end] + "\n");
        }

        bw.flush();
        bw.close();

    }


    public static void main (String[] args) throws IOException {
        new Main().solution();
    }
}
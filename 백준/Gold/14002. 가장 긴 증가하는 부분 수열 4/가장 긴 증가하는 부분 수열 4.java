import java.io.*;
import java.util.*;
public class Main {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int N;
    int[][][] dp;

    public void solution() throws IOException {

        N = Integer.parseInt(bf.readLine());
        int[] nums = new int[N];
        int[] dp = new int[N];
        int[] idx = new int[N];

        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int max = 1;
        int startIdx = 0;
        int nextIdx = 0;

        for (int i = N-1; i >= 0; i--) {
            dp[i] = 1;
            for (int j = i+1; j < N; j++) {
                if (nums[i] < nums[j] && dp[i] <= dp[j]) {
                    dp[i] = dp[j] + 1;
                    idx[i] = j;
                }
                if (max < dp[i]) {
                    max = dp[i];
                    startIdx = i;
                }
            }
        }

        int[] arr = new int[max];
        arr[0] = nums[startIdx];
        nextIdx = idx[startIdx];
        for (int i = 1; i < max; i++) {
            arr[i] = nums[nextIdx];
            nextIdx = idx[nextIdx];
        }

        bw.write(max + "\n");
        for (int i = 0; i < max; i++) {
            bw.write(arr[i]+" ");

        }
        bw.flush();
        bw.close();

    }


    public static void main (String[] args) throws IOException {
        new Main().solution();
    }
}
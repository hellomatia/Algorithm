import java.io.*;

public class Main {
    int T; // 테스트 케이스의 개수
    int[] dp;
    int[] numArr;
    public void solution() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        T = Integer.parseInt(bf.readLine());
        numArr = new int[T];

        int maxNum = Integer.MIN_VALUE;

        for(int i=0; i<T; i++) {
            numArr[i] = Integer.parseInt(bf.readLine());
            maxNum = Math.max(numArr[i], maxNum);
        }

        dp = new int[maxNum+1];

        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;

        for(int i=4; i<=maxNum; i++) {
            dp[i] = dp[i-1] + dp[i-2] + dp[i-3];
        }

        for(int i=0; i<T; i++) {
            bw.write(dp[numArr[i]]+"\n");
        }




        bw.flush();
        bw.close();
    }
    public static void main (String[] args) throws IOException {
        new Main().solution();
    }
}
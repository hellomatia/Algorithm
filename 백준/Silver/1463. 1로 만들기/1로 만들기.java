import java.io.*;
import java.util.Arrays;

public class Main {
    int N; // 정수
    int[] dp;
    public void solution() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(bf.readLine());

        dp = new int[N+1];

        Arrays.fill(dp, -1);

        for(int i=1; i<=N; i++) {
            int count = Integer.MAX_VALUE;
            for(int j=0; j<3; j++) {
                count = Math.min(calculation(i, j, 0), count);
            }
            dp[i] = count;
        }

        bw.write(dp[N]+"\n");


        bw.flush();
        bw.close();
    }

    public int calculation(int num, int type, int count) {

        if(num==1) {
            return count;
        }
        if(dp[num]>-1) {
            return count+dp[num];
        }

        if(type==0) {
            if(num%3==0) {
                for(int i=0; i<3; i++) {
                    return calculation(num/3, i, count+1);
                }
            }
            return Integer.MAX_VALUE;
        } else if(type==1) {
            if (num % 2 == 0) {
                for (int i = 0; i<3; i++) {
                    return calculation(num/2, i, count+1);
                }
            }
            return Integer.MAX_VALUE;
        }  else {
            if (num-1>0) {
                for(int i=0; i<3; i++) {
                    return calculation(num-1, i, count+1);
                }
            }
            return Integer.MAX_VALUE;
        }
    }

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }
}
import java.io.*;
import java.util.*;
public class Main {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int code1Count;
    static int code2Count;

    public void solution() throws IOException {

        int n = Integer.parseInt(bf.readLine());

        fib(n);
        fibonacci(n);


        bw.write(code1Count + " " + code2Count);


        bw.flush();
        bw.close();
    }

    public int fib(int n) {
        if (n == 1 || n==2) {
            code1Count++;
            return 1;
        }
        else return fib(n - 1) + fib(n - 2);
    }

    public int fibonacci(int n) {
        int[] dp = new int[n+1];
        dp[1] = 1;
        dp[2] = 1;

        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i-1] + dp[i - 2];
            code2Count++;
        }

        return dp[n];
    }



    public static void main (String[] args) throws IOException {
        new Main().solution();
    }
}
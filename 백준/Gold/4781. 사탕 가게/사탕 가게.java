import java.util.*;
import java.io.*;

public class Main {

    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private boolean flag = true;
    private int n;
    private int m;
    private Candy[] candies;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    public void solution() throws IOException {
        init();
        while (flag) {
            printAns(calcAns());
            init();
        }
        close();
    }

    private void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = (int) (Double.parseDouble(st.nextToken()) * 100 + 0.5);

        if (n == 0 && m == 0) {
            flag = false;
            return;
        }

        candies = new Candy[n + 1];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st.nextToken());
            int p = (int) (Double.parseDouble(st.nextToken()) * 100 + 0.5);
            candies[i] = new Candy(c, p);
        }
    }

    private String calcAns() {
        int[] dp = new int[m + 1];
        for (int i = 1; i <= n; i++) {
            Candy now = candies[i];
            for (int j = 0; j + now.getP() <= m; j++) {
                int money = j + now.getP();
                dp[money] = Math.max(dp[money], dp[j] + now.getC());
            }
        }
        return dp[m] + "\n";
    }

    private void printAns(String ans) throws IOException {
        bw.write(ans);
        bw.flush();
    }

    private void close() throws IOException {
        bw.close();
        br.close();
    }

    static class Candy {

        private final int c;
        private final int p;

        public Candy(int c, int p) {
            this.c = c;
            this.p = p;
        }

        public int getC() {
            return c;
        }

        public int getP() {
            return p;
        }
    }
}
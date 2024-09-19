import java.util.*;
import java.io.*;

public class Main {

    private static final BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private int N;
    private Hello[] hellos;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        printAns(calcAns());
        close();
    }

    private void init() throws IOException {
        N = Integer.parseInt(bf.readLine());
        hellos = new Hello[N + 1];
        StringTokenizer st1 = new StringTokenizer(bf.readLine());
        StringTokenizer st2 = new StringTokenizer(bf.readLine());
        for (int i = 1; i <= N; i++) {
            hellos[i] = new Hello(Integer.parseInt(st1.nextToken()), Integer.parseInt(st2.nextToken()));
        }
    }

    private String calcAns() {
        int[][] dp = new int[N + 1][100];
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < 100; j++) {
                if (hellos[i].getHealth() <= j) {
                    dp[i][j] = Math.max(dp[i - 1][j - hellos[i].getHealth()] + hellos[i].getHappy(), dp[i - 1][j]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[N][99] + "";
    }

    private void printAns(String ans) throws IOException {
        bw.write(ans + "\n");
    }

    private void close() throws IOException {
        bw.close();
        bf.close();
    }

    static class Hello {
        private int health;
        private int happy;

        public Hello(int health, int happy) {
            this.health = health;
            this.happy = happy;
        }

        public int getHealth() {
            return health;
        }

        public int getHappy() {
            return happy;
        }
    }
}
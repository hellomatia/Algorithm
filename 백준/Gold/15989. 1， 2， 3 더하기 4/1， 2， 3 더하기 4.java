import java.io.*;
import java.util.*;

public class Main {

    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int T;
    private static List<Integer> Ns;
    private static int[][] dp;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        printResult();
        bw.flush();
        bw.close();
    }

    private void init() throws IOException {
        initT();
        Ns = new ArrayList<>();
        for (int i = 0; i < T; i++) {
            initN();
        }
        initDp();
    }

    private void initT() throws IOException {
        T = Integer.parseInt(bf.readLine());
    }

    private void initN() throws IOException {
        Ns.add(Integer.parseInt(bf.readLine()));
    }

    private void initDp() {
        dp = new int[Ns.stream()
                .mapToInt(it -> it)
                .max().orElse(1) + 1][4];

        dp[1][1] = 1;
        dp[2][1] = 1;
        dp[2][2] = 1;
        dp[3][1] = 1;
        dp[3][2] = 1;
        dp[3][3] = 1;

        for (int index = 4; index < dp.length; index++) {
            dp[index][1] = dp[index - 1][1];
            dp[index][2] = dp[index - 2][1] + dp[index - 2][2];
            dp[index][3] = dp[index - 3][1] + dp[index - 3][2] + dp[index - 3][3];
        }
    }

    private void printResult() throws IOException {
        for (int i = 0; i < Ns.size(); i++) {
            int number = Ns.get(i);
            bw.write((dp[number][1] + dp[number][2] + dp[number][3]) + "\n");
        }
    }
}
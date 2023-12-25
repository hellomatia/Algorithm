import java.io.*;
import java.util.*;

public class Main {

    private static final int MOD = 1_000_000_007;

    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int n;
    private static long[] scovilleIndex;
    private static int[] pow;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        printResult(calcResult());
        bw.flush();
        bw.close();
    }

    private void init() throws IOException {
        n = Integer.parseInt(bf.readLine());

        scovilleIndex = new long[n];
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < n; i++) {
            scovilleIndex[i] = Long.parseLong(st.nextToken());
        }
        Arrays.sort(scovilleIndex);
        initPow();
    }

    private void initPow() {
        pow = new int[n + 1];
        pow[0] = 1;
        for (int i = 1; i <= n; i++) {
            pow[i] = (pow[i - 1] * 2) % MOD;
        }
    }

    private long calcResult() {
        long result = 0;
        for (int i = 0; i < n; i++) {
            result += scovilleIndex[i] * (pow[i] - pow[n - i - 1]);
            result %= MOD;
        }

        return result;
    }

    private void printResult(long result) throws IOException {
        bw.write(result + "\n");
    }
}

import java.util.*;
import java.io.*;

public class Main {

    private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private long[] bit;
    private long start;
    private long end;


    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        printAns(calcAns());
    }

    private void init() throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        start = Long.parseLong(st.nextToken());
        end = Long.parseLong(st.nextToken());

        bit = new long[55];
        bit[0] = 1;
        for (int i = 1; i < 55; i++) {
            bit[i] = 2 * bit[i - 1] + (1L << i);
        }
    }

    private long calcAns() {
        return calcBitCount(end) - calcBitCount(start - 1);
    }

    private long calcBitCount(long num) {
        long count = num & 1;

        for (int i = 54; i > 0; i--) {
            if ((num & (1L << i)) > 0L) {
                count += bit[i - 1] + (num - (1L << i) + 1);
                num -= (1L << i);
            }
        }

        return count;
    }

    private void printAns(long ans) throws IOException {
        bw.write(ans + "\n");
        bw.flush();
    }

}
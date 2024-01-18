import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {

    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int N;
    private static String str;

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
        N = Integer.parseInt(bf.readLine());
        str = bf.readLine();
    }

    private long calcResult() {
        BigInteger sum = BigInteger.ZERO;
        BigInteger r = new BigInteger("31");
        BigInteger M = new BigInteger("1234567891");
        for (int i = 0; i < N; i++) {
            int num = str.charAt(i) - 'a';
            sum = sum.add(r.pow(i).multiply(BigInteger.valueOf(num + 1)));
        }
        return sum.mod(M).longValue();
    }

    private void printResult(long result) throws IOException {
        bw.write(result + "\n");
    }
}

import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {

    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int N;

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
    }

    private int calcResult() {
        String string = factorial(BigInteger.valueOf(N)).toString();
        char[] chars = string.toCharArray();
        int count = 0;
        for (int i = chars.length - 1; i >= 0; i--) {
            if (chars[i] != '0') {
                break;
            }
            count++;
        }
        return count;
    }

    private BigInteger factorial(BigInteger num) {
        if (num.equals(BigInteger.ZERO)) {
            return BigInteger.ONE;
        }
        return num.multiply(factorial(num.subtract(BigInteger.ONE)));
    }

    private void printResult(int result) throws IOException {
        bw.write(result + "\n");
    }
}
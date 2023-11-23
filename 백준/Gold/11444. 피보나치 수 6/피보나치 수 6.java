import java.io.*;
import java.math.BigInteger;
import java.util.*;

class Matrix {
    long[][] data = new long[2][2];

    Matrix() {
        data[0][0] = 1; data[0][1] = 1;
        data[1][0] = 1; data[1][1] = 0;
    }
}

public class Main {

    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static BigInteger n;


    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        initN();
        printResult(fibonacci(n));
        bw.flush();
        bw.close();
    }

    private void initN() throws IOException {
        n = new BigInteger(bf.readLine());
    }

    private long fibonacci(BigInteger n) {
        if (n.equals(BigInteger.ZERO)) {
            return 0;
        }
        Matrix matrix = new Matrix();
        matrix = pow(matrix, n);
        return matrix.data[0][1];
    }

    private Matrix pow(Matrix matrix, BigInteger n) {
        if (n.compareTo(BigInteger.ONE) > 0) {
            matrix = pow(matrix, n.divide(BigInteger.TWO));
            matrix = multi(matrix, matrix);

            if (n.remainder(BigInteger.TWO).equals(BigInteger.ONE)) {
                Matrix tempMatrix = new Matrix();
                matrix = multi(matrix, tempMatrix);
            }
        }
        return matrix;
    }

    private Matrix multi(Matrix o1, Matrix o2) {
        Matrix result = new Matrix();
        result.data[0][0] = o1.data[0][0] * o2.data[0][0] + o1.data[0][1] * o2.data[1][0];
        result.data[0][1] = o1.data[0][0] * o2.data[1][0] + o1.data[0][1] * o2.data[1][1];
        result.data[1][0] = o1.data[1][0] * o2.data[0][0] + o1.data[1][1] * o2.data[1][0];
        result.data[1][1] = o1.data[1][0] * o2.data[1][0] + o1.data[1][1] * o2.data[1][1];

        result.data[0][0] %= 1_000_000_007;
        result.data[0][1] %= 1_000_000_007;
        result.data[1][0] %= 1_000_000_007;
        result.data[1][1] %= 1_000_000_007;

        return result;
    }

    private void printResult(long result) throws IOException {
        bw.write(result + "\n");
    }
}

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {

	class Matrix {
		long[][] data = new long[3][3];

		Matrix() {
			data[0][0] = 1;
			data[0][1] = 1;
			data[1][0] = 1;
			data[1][1] = 0;
		}
	}

	private static final BigInteger TWO = new BigInteger("2");
	private static final long MOD = 1_000_000_000;

	private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	private BigInteger a;
	private BigInteger b;

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
		StringTokenizer st = new StringTokenizer(bf.readLine());
		a = new BigInteger(st.nextToken());
		b = new BigInteger(st.nextToken());
	}

	private long calcResult() throws IOException {
		long result = (fibonacci(b.add(TWO)) - 1 - fibonacci(a.add(BigInteger.ONE)) + 1) % MOD;
		if (result < 0) {
			result += MOD;
		}
		return result;
	}

	private long fibonacci(BigInteger n) {
		if (n.equals(BigInteger.ZERO)) {
			return 0;
		}
		Matrix matrix = new Matrix();
		matrix = pow(matrix, n);
		return matrix.data[0][1] % MOD;
	}

	private Matrix pow(Matrix matrix, BigInteger n) {
		if (n.compareTo(BigInteger.ONE) > 0) {
			matrix = pow(matrix, n.divide(TWO));
			matrix = multi(matrix, matrix);

			if (n.remainder(TWO).equals(BigInteger.ONE)) {
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

		result.data[0][0] %= MOD;
		result.data[0][1] %= MOD;
		result.data[1][0] %= MOD;
		result.data[1][1] %= MOD;

		return result;
	}

	private void printResult(long result) throws IOException {
		bw.write(result + "\n");
	}
}
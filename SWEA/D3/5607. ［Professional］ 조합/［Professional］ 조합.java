import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Solution {

	private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	private int N, R;

	public static void main(String[] args) throws IOException {
		new Solution().solution();
	}

	public void solution() throws IOException {
		int testCase = Integer.parseInt(bf.readLine());
		for (int i = 1; i <= testCase; i++) {
			init();
			printResult(i, calcResult(N, R));
		}
	}

	private void init() throws IOException {
		StringTokenizer st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
	}

	private int calcResult(int n, int r) {
		int MOD = 1234567891;
		long A = 1, B = 1;

		for (int i = 1; i <= n; i++) {
			A *= i;
			A %= MOD;
		}

		for (int i = 1; i <= r; i++) {
			B *= i;
			B %= MOD;
		}

		for (int i = 1; i <= n - r; i++) {
			B *= i;
			B %= MOD;
		}

		long B2 = 1;
		int exponent = MOD - 2;
		while (exponent > 0) {
			if (exponent % 2 == 1) {
				B2 *= B;
				B2 %= MOD;
			}

			B *= B;
			B %= MOD;
			exponent /= 2;
		}

		long result = A * B2;
		result %= MOD;

		return (int) result;
	}

	private void printResult(int testCase, int result) throws IOException {
		bw.write("#" + testCase + " ");
		bw.write(result + "\n");
		bw.flush();
	}
}
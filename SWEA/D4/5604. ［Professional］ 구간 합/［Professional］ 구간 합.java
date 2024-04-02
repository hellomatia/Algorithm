import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

class Solution {

	private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	private long A, B;
	private long[] f;
	private HashMap<Long, Long> dp;

	public static void main(String[] args) throws IOException {
		new Solution().solution();
	}

	public void solution() throws IOException {
		int testCase = Integer.parseInt(bf.readLine());
		for (int i = 1; i <= testCase; i++) {
			init();
			printResult(i, calcResult());
		}
	}

	private void init() throws IOException {
		StringTokenizer st = new StringTokenizer(bf.readLine());
		A = Long.parseLong(st.nextToken());
		B = Long.parseLong(st.nextToken());
		dp = new HashMap<>();
		dp.put(0L, 0L);
		for (int i = 1; i <= 9; i++) {
			dp.put((long) i, dp.get((long) i - 1) + i);
		}
	}

	private long calcResult() {
		if (A == 0) {
			return f(B) - f(A);
		}
		return f(B) - f(A - 1);
	}

	private long f(long n) {
		if (dp.containsKey(n)) {
			return dp.get(n);
		}

		long v = v(n);
		long f = f(n - 1 - n % v);
		long g = (n / v) * (n % v + 1) + f(n % v);
		long num = f + g;

		dp.put(n, num);

		return num;
	}

	private long v(long n) {
		long v = 1;
		while (n >= 10) {
			v = v * 10;
			n = n / 10;
		}
		return v;
	}

	private void printResult(int testCase, long result) throws IOException {
		bw.write("#" + testCase + " ");
		bw.write(result + "\n");
		bw.flush();
	}
}
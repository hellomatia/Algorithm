import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	private long n;
	private long[] fb;

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
		n = Long.parseLong(bf.readLine());
		n %= 1_500_000;
	}

	private long calcResult() throws IOException {
		fb = new long[(int) n + 1];
		fb[1] = 1;
		for (int i = 2; i <= n; i++) {
			fb[i] = fb[i - 1] + fb[i - 2];
		}
		return fb[(int) n];
	}

	private void printResult(long result) throws IOException {
		bw.write(result + "\n");
	}
}
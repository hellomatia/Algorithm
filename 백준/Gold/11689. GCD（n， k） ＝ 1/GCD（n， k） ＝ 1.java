import java.io.*;
import java.util.*;

public class Main {
	
	private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	
	private static long N;
	private static long result;
	
	public static void main(String[] args) throws IOException {
		new Main().solution();
	}
	
	private void solution() throws IOException {
		init();
		calcResult();
		printResult();
		bw.flush();
		bw.close();
	}
	
	private void init() throws IOException {
		N = Long.parseLong(bf.readLine());
	}
	
	private void calcResult() {
		result = N;
		for (int i = 2; i <= Math.sqrt(N); i++) {
			if (N % i == 0) {
				result = result / i * (i - 1);
			}
			while (N % i == 0) {
				N = N / i;
			}
		}
		if (N != 1) {
			result = result / N * (N - 1);
		}
	}
	
	private void printResult() throws IOException {
		bw.write(result + "\n");
	}
}

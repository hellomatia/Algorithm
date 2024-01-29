import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {
	
	private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	private int N, M;
	
	public static void main(String[] args) throws IOException {
		new Solution().solution();
	}
	
	private void solution() throws IOException {
		int T = Integer.parseInt(bf.readLine());
		for (int testCase = 1; testCase <= T; testCase++) {
			init();
			printResult(testCase, calcResult());
		}
		bw.flush();
		bw.close();
	}
	
	private void init() throws IOException {
		StringTokenizer st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
	}
	
	private String calcResult() {
		for (int i = 0; i < N; i++) {
			if ((M & (1 << i)) == 0) {
				return "OFF";
			}
		}
		return "ON";
	}
	
	private void printResult(int testCase, String result) throws IOException {
		bw.write("#" + testCase + " " + result + "\n");
	}
}

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {
	
	private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	private int N;
	private int center;
	private int[][]  farm;
	
	public static void main(String[] args) throws IOException {
		new Solution().solution();
	}
	
	private void solution() throws IOException {
		int T = Integer.parseInt(bf.readLine());
		for (int testCase = 1; testCase <= T; testCase++) {
			init();
			printResult(testCase, calcResult(0));
		}
		bw.flush();
		bw.close();
	}
	
	private void init() throws IOException {
		N = Integer.parseInt(bf.readLine());
		center = N / 2;
		farm = new int[N][N];
		for (int i = 0; i < N; i++) {
			String rowValue = bf.readLine();
			for (int j = 0; j < N; j++) {
				farm[i][j] = rowValue.charAt(j) - '0';
			}
		}
	}
	
	private int calcResult(int count) {
		if (count == center) {
			int sum = 0;
			for (int i = 0; i < N; i++) {
				sum += farm[i][count];
			}
			return sum;
		}
		
		int sum = farm[center][count] + farm[center][N - count - 1];
		for (int i = 1; i <= count; i++) {
			sum += (farm[center + i][count] + farm[center + i][N - count - 1]);
			sum += (farm[center - i][count] + farm[center - i][N - count - 1]);
		}
		
		return sum + calcResult(count + 1);
	}
	
	private void printResult(int testCase, int result) throws IOException {
		bw.write("#" + testCase + " " + result + "\n");
	}
}

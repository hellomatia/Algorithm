import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Solution {
	
	private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	private int N;
	private int visited;
	
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
		N = Integer.parseInt(bf.readLine());
		visited = 0;
	}
	
	private int calcResult() {
		int number = N;
		while (!isAllVisited(number)) {
			for (int digit = number; digit != 0; digit /= 10) {
				checkNum(digit % 10);
			}
			number += N;
		}
		return number - N;
	}
	
	private void checkNum(int num) {
		visited = (visited | (1 << num));
	}
	
	private boolean isAllVisited(int number) {
		return (((1 << 10) - 1) & (visited)) == ((1 << 10) - 1);
	}
	
	private void printResult(int testCase, int result) throws IOException {
		bw.write("#" + testCase + " " + result + "\n");
	}
}

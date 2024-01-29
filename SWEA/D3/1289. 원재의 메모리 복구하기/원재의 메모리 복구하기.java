import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Solution {
	
	private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	private String memory;
	
	public static void main(String[] args) throws IOException {
		new Solution().solution();
	}
	
	private void solution() throws IOException {
		// 테스트 케이스 입력받기
		int T = Integer.parseInt(bf.readLine());
		for (int testCase = 1; testCase <= T; testCase++) {
			init();
			printResult(testCase, calcResult());
		}
		bw.close();
	}
	
	private void init() throws IOException {
		memory = bf.readLine();
	}
	
	private int calcResult() {
		int result = 0;
		char now = '0';
		for (int i = 0; i < memory.length(); i++) {
			if (now != memory.charAt(i)) {
				result++;
				now = memory.charAt(i);
			}
		}
		return result;
	}
	
	private void printResult(int testCase, int result) throws IOException {
		bw.write("#" + testCase + " " + result + "\n");
		bw.flush();
	}
}

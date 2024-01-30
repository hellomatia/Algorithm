import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	
	private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	private int dumpCount;
	private int[] boxHight;
	
	public static void main(String[] args) throws IOException {
		new Solution().solution();
	}
	
	private void solution() throws IOException {
		int T = 10;
		for (int testCase = 1; testCase <= T; testCase++) {
			init();
			printResult(testCase, calcResult());
		}
		bw.flush();
		bw.close();
	}
	
	private void init() throws IOException {
		dumpCount = Integer.parseInt(bf.readLine());
		StringTokenizer st = new StringTokenizer(bf.readLine());
		boxHight = new int[100];
		for (int i = 0; i < 100; i++) {
			boxHight[i] = Integer.parseInt(st.nextToken());
		}
	}
	
	private int calcResult() {
		int diff = 0;
		for (int i = 0; i < dumpCount; i++) {
			Arrays.sort(boxHight);
			diff = calcMinMaxDiff();
			if (diff == 0 || diff == 1) {
				return diff;
			}
			boxHight[99]--;
			boxHight[0]++;
		}
		Arrays.sort(boxHight);
		diff = calcMinMaxDiff();
		return diff;
	}
	
	private int calcMinMaxDiff() {
		return boxHight[99] - boxHight[0];
	}
	
	private void printResult(int testCase, int result) throws IOException {
		bw.write("#" + testCase + " " + result + "\n");
	}
}

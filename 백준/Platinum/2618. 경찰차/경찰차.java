import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	
	private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	private int N, W;
	private int[][] events;
	private int[][] dp;
	private int result;
	private int[] nypd;
	
	public static void main(String[] args) throws IOException {
		new Main().solution();
	}
	
	private void solution() throws IOException {
		init();
		result = calcResult(1, 0, 0);
		tracePath();
		printResult();
		bw.close();
	}
	
	private void init() throws IOException {
		N = Integer.parseInt(bf.readLine());
		W = Integer.parseInt(bf.readLine());
		events = new int[W + 2][2];
		for (int i = 1; i <= W; i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			events[i][0] = Integer.parseInt(st.nextToken());
			events[i][1] = Integer.parseInt(st.nextToken());
		}
		dp = new int[W + 1][W + 1];
	}
	
	private int calcResult(int eventNum, int one, int two) {
		
		if (eventNum > W) return 0;
		if (dp[one][two] != 0) return dp[one][two];
		
		int oneDist = calcResult(eventNum + 1, eventNum, two) + calcDist(true, one, eventNum);
		int twoDist = calcResult(eventNum + 1, one, eventNum) + calcDist(false, two, eventNum);
		
		dp[one][two] = Math.min(oneDist, twoDist);
		return dp[one][two];
	}
	
	private int calcDist(boolean isOne, int from, int to) {
		
		if (from == 0) {
			if (isOne) events[from][0] = events[from][1] = 1;
			else events[from][0] = events[from][1] = N;
		}
		
		return Math.abs(events[from][0] - events[to][0]) 
				+ Math.abs(events[from][1] - events[to][1]);
	}
	
	private void tracePath() {
		nypd = new int[W + 1];
		int one = 0;
		int two = 0;
		for (int i = 1; i <= W; i++) {
			int oneDist = calcDist(true, one, i);
			if (dp[one][two] - oneDist == dp[i][two]) {
				nypd[i] = 1;
				one = i;
			} else {
				nypd[i] = 2;
				two = i;
			}
		}
	}
	
	public void printResult() throws IOException {
		bw.write(result + "\n");
		for (int i = 1; i <= W; i++) {
			bw.write(nypd[i] + "\n");
		}
		bw.flush();
	}
}

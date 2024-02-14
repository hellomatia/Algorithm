
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	private int t;
	private int[][] gameResult;
	private int winCount, loseCount, drawCount;
	private int[] gameCount;
	private int drawCountry;
	private int matchCount;
	private int[][] teamMatch;
	private int[][] gameSimul;
	private boolean isPossible;
	
	
	public static void main(String[] args) throws IOException {
		new Main().solution();
	}

	private void solution() throws IOException {
		t = 4;
		for (int i = 0; i < t; i++) {
			init();
			printResult(calcResult());
		}
		bw.flush();
		bw.close();
	}

	private void init() throws IOException {
		gameResult = new int[6][3];
		winCount = 0;
		loseCount = 0;
		drawCount = 0;
		gameCount = new int[6];
		drawCountry = 0;
		StringTokenizer st = new StringTokenizer(bf.readLine());
		for (int i = 0; i < 6; i++) {
			int win = Integer.parseInt(st.nextToken());
			int draw = Integer.parseInt(st.nextToken());
			int lose = Integer.parseInt(st.nextToken());
			gameResult[i][0] = win;
			gameResult[i][1] = draw;
			gameResult[i][2] = lose;

			winCount += win;
			drawCount += draw;
			loseCount += lose;
			gameCount[i] = win + draw + lose;
			if (draw > 0) drawCountry++;
		}
		isPossible = false;
	}
	
	private int calcResult() {
		if (!validateWinLoseCount() || !validateDrawCount() || !validateGameCount())
			return 0;
		matchCount = 15;
		teamMatch = new int[matchCount][2];
		int index = 0;
		for (int i = 0; i < 6; i++) {
			for (int j = i + 1; j < 6; j++) {
				teamMatch[index][0] = i;
				teamMatch[index][1] = j;
				index++;
			}
		}
		
		gameSimul = new int[6][3];
		dfs(0);
		
		if (isPossible) return 1;
		return 0;
	}
	
	private boolean validateWinLoseCount() {
		return winCount == loseCount;
	}
	
	private boolean validateDrawCount() {
		return drawCount % 2 == 0 && drawCountry != 1;
	}
	
	private boolean validateGameCount() {
		for (int i = 0; i < 6; i++) {
			if (gameCount[i] != 5) return false;
		}
		return true;
	}
	
	private void dfs(int count) {

		if (count == matchCount) {
			isPossible = true;
			return;
		}
		
		int teamA = teamMatch[count][0];
		int teamB = teamMatch[count][1];
		
		// 승
		gameSimul[teamA][0]++;
		gameSimul[teamB][2]++;
		if (gameSimul[teamA][0] <= gameResult[teamA][0] 
				&& gameSimul[teamB][2] <= gameResult[teamB][2]) {
			dfs(count + 1);
		}
		gameSimul[teamA][0]--;
		gameSimul[teamB][2]--;
		
		// 무
		gameSimul[teamA][1]++;
		gameSimul[teamB][1]++;
		if (gameSimul[teamA][1] <= gameResult[teamA][1] 
				&& gameSimul[teamB][1] <= gameResult[teamB][1]) {
			dfs(count + 1);
		}
		gameSimul[teamA][1]--;
		gameSimul[teamB][1]--;
		
		// 패
		gameSimul[teamA][2]++;
		gameSimul[teamB][0]++;
		if (gameSimul[teamA][2] <= gameResult[teamA][2] 
				&& gameSimul[teamB][0] <= gameResult[teamB][0]) {
			dfs(count + 1);
		}
		gameSimul[teamA][2]--;
		gameSimul[teamB][0]--;
	}

	private void printResult(int result) throws IOException {
		bw.write(result + " ");
	}
}

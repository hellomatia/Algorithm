
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

	private int N;
	private char[][] map;
	private StringBuilder sb;
	
	public static void main(String[] args) throws IOException {
		new Main().solution();
	}

	private void solution() throws IOException {
		init();
		calcResult(N, 0, 0);
		
		printResult(sb.toString());
		bw.flush();
		bw.close();
	}

	private void init() throws IOException {
		N = Integer.parseInt(bf.readLine());
		map = new char[N][N];
		for (int i = 0; i < N; i++) {
			String rowValue = bf.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = rowValue.charAt(j);
			}
		}
		sb = new StringBuilder();
	}
	
	private void calcResult(int size, int x, int y) {
		if (isAllSame(size, x, y)) {
			sb.append(map[x][y]);
			return;
		} else {
			sb.append("(");
			calcResult(size / 2, x, y);
			calcResult(size / 2, x, y + size / 2);
			calcResult(size / 2, x + size / 2, y);
			calcResult(size / 2, x + size / 2, y + size / 2);
			sb.append(")");
		}
	}
	
	private boolean isAllSame(int size, int x, int y) {
		char color = map[x][y];
		for (int i = x; i < x + size; i++) {
			for (int j = y; j < y + size; j++) {
				if (color != map[i][j]) return false;
			}
		}
		return true;
	}

	private void printResult(String result) throws IOException {
		bw.write(result + "\n");
	}
}

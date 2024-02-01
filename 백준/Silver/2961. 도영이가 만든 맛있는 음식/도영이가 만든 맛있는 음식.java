import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
class 재료 {
	int S;
	int B;
	
	재료(int S, int B) {
		this.S = S;
		this.B = B;
	}
}

public class Main {
	
	private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	private int N;
	private 재료[] 재료들;
	private int foodLevel;
	
	public static void main(String[] args) throws IOException {
		new Main().solution();
	}
	
	private void solution() throws IOException {
		init();
		calcResult(N);
		printResult();
		bw.flush();
		bw.close();
	}
	
	private void init() throws IOException {
		N = Integer.parseInt(bf.readLine());
		재료들 = new 재료[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			재료들[i] = new 재료(Integer.parseInt(st.nextToken()),
							Integer.parseInt(st.nextToken()));
		}
	}
	
	private void calcResult(int count) {
		foodLevel = Integer.MAX_VALUE;
		for (int i = 1, usedCount = 1 << N; i < usedCount; i++) {
			int S = 1;
			int B = 0;
			for (int j = 0; j < N; j++) {
				if ((i & (1 << j)) != 0) {
					S *= 재료들[j].S;
					B += 재료들[j].B;
				}
			}
			foodLevel = Math.min(Math.abs(S - B), foodLevel);
		}
	}

	private void printResult() throws IOException {
		bw.write(foodLevel + "\n");
	}
}


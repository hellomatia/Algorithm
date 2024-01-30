import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	
	private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	private int N, M;
	private int visited;
	
	public static void main(String[] args) throws IOException {
		new Main().solution();
	}
	
	private void solution() throws IOException {
		init();
		calcResult(0, "");
		bw.flush();
		bw.close();
	}
	
	private void init() throws IOException {
		StringTokenizer st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
	}
	
	private void calcResult(int count, String result) throws IOException {
		if (count == M) {
			printResult(result);
		}
		for (int num = 1; num <= N; num++) {
			if (isVisited(num)) {
				continue;
			}
			
			checkVisited(num);
			calcResult(count + 1, result + num + " ");
			unCheckVisited(num);
		}
	}
	
	private void checkVisited(int num) {
		visited |= (1 << num);
	}
	
	private void unCheckVisited(int num) {
		visited &= (~ (1 << num));
	}
	
	private boolean isVisited(int num) {
		return (visited & (1 << num)) > 0; 
	}
	
	private void printResult(String result) throws IOException {
		bw.write(result + "\n");
	}
}


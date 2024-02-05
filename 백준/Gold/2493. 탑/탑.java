import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

class Top {
	int index;
	int height;
	
	Top(int index, int height) {
		this.index = index;
		this.height = height;
	}
}

public class Main {
	
	private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	private int N;
	private Top[] tops;
	
	public static void main(String[] args) throws IOException {
		new Main().solution();
	}
	
	private void solution() throws IOException {
		init();
		printResult(calcResult());
		bw.flush();
		bw.close();
	}
	
	private void init() throws IOException {
		N = Integer.parseInt(bf.readLine());
		tops = new Top[N + 1];
		StringTokenizer st = new StringTokenizer(bf.readLine());
		for (int i = 1; i <= N; i++) {
			tops[i] = new Top(i - 1, Integer.parseInt(st.nextToken()));
		}
	}
	
	private int[] calcResult() {
		int[] result = new int[N];
		Stack<Top> stack = new Stack<>();
		for (int i = N; i > 0; i--) {
			while (!stack.isEmpty() && tops[i].height > stack.peek().height) {
				Top now = stack.pop();
				result[now.index] = i;
			}
			stack.push(tops[i]);
		}
		return result;
	}
	
	private void printResult(int[] result) throws IOException {
		for (int r : result) {
			bw.write(r + " ");
		}
	}
}


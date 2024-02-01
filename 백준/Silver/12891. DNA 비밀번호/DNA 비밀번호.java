import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	
	private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	private int S, P;
	private String DNA;
	private int[] passwordCriteria;
	private int[] useCount;

	private static final int A = 0;
	private static final int C = 1;
	private static final int G = 2;
	private static final int T = 3;
	
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
		StringTokenizer st = new StringTokenizer(bf.readLine());
		S = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		DNA = bf.readLine();
		passwordCriteria = new int[4];
		st = new StringTokenizer(bf.readLine());
		for (int i = 0; i < 4; i++) {
			passwordCriteria[i] = Integer.parseInt(st.nextToken());
		}
	}
	
	private int calcResult() {
		int result = 0;
		Deque<Character> dq = new ArrayDeque<>();
		useCount = new int[4];
		for (int i = 0; i < P; i++) {
			char ch = DNA.charAt(i);
			dq.offer(DNA.charAt(i));
			checkUse(ch);
		}
		if (canMakePassword()) {
			result++;
		}
		for (int i = P; i < S; i++) {
			char pollCh = dq.poll();
			unCheckUse(pollCh);
			
			char ch = DNA.charAt(i);
			dq.offer(DNA.charAt(i));
			checkUse(ch);
			
			if (canMakePassword()) {
				result++;
			}
		}
		return result;
	}
	
	private void checkUse(char ch) {
		if (ch == 'A') {
			useCount[A]++;
			return;
		}
		if (ch == 'C') {
			useCount[C]++;
			return;
		}
		if (ch == 'G') {
			useCount[G]++;
			return;
		}
		if (ch == 'T') {
			useCount[T]++;
			return;
		}
	}
	
	private void unCheckUse(char ch) {
		if (ch == 'A') {
			useCount[A]--;
			return;
		}
		if (ch == 'C') {
			useCount[C]--;
			return;
		}
		if (ch == 'G') {
			useCount[G]--;
			return;
		}
		if (ch == 'T') {
			useCount[T]--;
			return;
		}
	}
	
	private boolean canMakePassword() {
		for (int i = 0; i < 4; i++) {
			if (passwordCriteria[i] > useCount[i]) {
				return false;
			}
		}
		return true;
	}

	private void printResult(int result) throws IOException {
		bw.write(result + "\n");
	}
}


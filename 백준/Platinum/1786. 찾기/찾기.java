
import java.io.*;
import java.util.*;

public class Main {

	private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	private static String T, P;
	private static char[] TChars;
	private static char[] PChars;
	private static int[] lps;
			
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
		T = bf.readLine();
		P = bf.readLine();
		TChars = T.toCharArray();
		PChars = P.toCharArray();
	}

	private List<Integer> calcResult() {
		List<Integer> result = new ArrayList<>();
		
		lps = new int[P.length()];
		calcLps();
		
		int n = T.length();
		int m = P.length();
		
		int index = 0;
		
		for (int i = 0; i < n; i++) {
			while (index > 0 && TChars[i] != PChars[index]) {
				index = lps[index - 1];
			}
			if (TChars[i] == PChars[index]) {
				if (index == m - 1) {
					result.add(i - index + 1);
					index = lps[index];
				} else {
					index += 1;
				}
			}
		}
		return result;
	}
	
	private void calcLps() {
		int m = P.length();
		int index = 0;
		
		for (int i = 1; i < m; i++) {
			while (index > 0 && PChars[i] != PChars[index]) {
				index = lps[index - 1];
			}
			
			if (PChars[i] == PChars[index]) {
				index++;
				lps[i] = index;
			}
			
		}
	}

	private void printResult(List<Integer> result) throws IOException {
		bw.write(result.size() + "\n");
		for (int startPoint : result) {
			bw.write(startPoint + " ");
		}
	}
}

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class Solution {
	
	private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	private int count;
	private Stack<Character> stack;
	private char[] brackets;
	
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
		count = Integer.parseInt(bf.readLine());
		String rowValue = bf.readLine();
		brackets = rowValue.toCharArray();
	}
	
	private int calcResult() {
		int index = 0;
		stack = new Stack<>();
		while (index < count) {
			if (isStartBracket(brackets[index])) {
				stack.push(brackets[index]);
                index++;
				continue;
			}
			if (isEndBracket(brackets[index])) {
				if (!isPair(stack.pop(), brackets[index])) {
					return 0;
				}
			}
			index++;
		}
		if (!stack.isEmpty()) {
			return 0;
		}
		return 1;
	}
	
	private boolean isStartBracket(char ch) {
		return ch == '(' || ch == '[' || ch == '{' || ch == '<';
	}

	private boolean isEndBracket(char ch) {
		return !isStartBracket(ch);
	}
	
	private boolean isPair(char ch1 ,char ch2) {
		if (ch1 == '(') {
			return ch2 == ')';
		} else if (ch1 == '[') {
			return ch2 == ']';
		} else if (ch1 == '{') {
			return ch2 == '}';
		} else if (ch1 == '<') {
			return ch2 == '>';
		}
		return false;
	}
	
	private void printResult(int testCase, int result) throws IOException {
		bw.write("#" + testCase + " " + result + "\n");
	}
}

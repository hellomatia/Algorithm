
import java.io.*;
import java.util.*;

public class Solution {
	
	private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static int T;
	private static int[] numbers;
	private static int count;
	private static int max;
	
	public static void main(String args[]) throws Exception {
		solution();
	}
	
	private static void solution() throws IOException {
		initT();
		for (int testCase = 1; testCase <= T; testCase++) {
			initNumbersAndCount();
			max = Integer.MIN_VALUE;
			makeMaxNumber(0, 0);
			printResult(testCase, max);
		}
		bw.flush();
		bw.close();
	}
	
	private static void initT() throws IOException {
		T = Integer.parseInt(bf.readLine());
	}
	
	private static void initNumbersAndCount() throws IOException {
		StringTokenizer st = new StringTokenizer(bf.readLine());
		String string = st.nextToken();		
		numbers = new int[string.length()];

		for (int index = 0; index < string.length(); index++) {
			numbers[index] = string.charAt(index) - '0';
		}
		count = Integer.parseInt(st.nextToken());
	}
	
	
	private static void makeMaxNumber(int start, int cnt) {
		int standardNumber = numbers[start];

		if (cnt == count) {
			max = Math.max(max, toNumber());
			return;
		}
		
		if (start == numbers.length - 1) {
			numbers[start] = numbers[start - 1];
			numbers[start - 1] = standardNumber;
			makeMaxNumber(start - 1, cnt + 1);
			numbers[start - 1] = numbers[start];
			numbers[start] = standardNumber;
			return;
		}
		
		for (int index = start + 1; index < numbers.length; index++) {
			if (standardNumber <= numbers[index]) {
				numbers[start] = numbers[index];
				numbers[index] = standardNumber;
				makeMaxNumber(start + 1, cnt + 1);
				numbers[index] = numbers[start];
				numbers[start] = standardNumber;
			}
		}
		makeMaxNumber(start + 1, cnt);
	}
	
	private static int toNumber() {
		int number = 0;
		for (int index = numbers.length - 1; index >= 0; index--) {
			number += Math.pow(10, numbers.length - index - 1) * numbers[index];
		}
		return number;
	}
	
	
	private static void printResult(int testCase, int result) throws IOException {
		bw.write("#"+testCase+" "+result + "\n");
	}
}

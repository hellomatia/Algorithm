
import java.io.*;
import java.util.*;

public class Solution {
	
	private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static int T;
	private static int[] memory;
	
	public static void main(String args[]) throws Exception {
		solution();
	}
	
	private static void solution() throws IOException {
		initT();
		for (int testCase = 1; testCase <= T; testCase++) {
			initMemory();
			printResult(testCase, findMinCount());
		}
		bw.flush();
		bw.close();
	}
	
	private static void initT() throws IOException {
		T = Integer.parseInt(bf.readLine());
	}
	
	private static void initMemory() throws IOException {
		String string = bf.readLine();
		memory = new int[string.length()];
		for (int i = 0; i < string.length(); i++) {
			memory[i] = string.charAt(i) - '0';
		}
	}
	
	private static int findMinCount() {
		int nowBit = 0;
		int count = 0;
		for (int i = 0; i < memory.length; i++) {
			if (nowBit != memory[i]) {
				nowBit = memory[i];
				count++;
			}
		}
		return count;
	}
		
	private static void printResult(int testCase, int result) throws IOException {
		bw.write("#"+testCase + " " + result +"\n");
	}
}

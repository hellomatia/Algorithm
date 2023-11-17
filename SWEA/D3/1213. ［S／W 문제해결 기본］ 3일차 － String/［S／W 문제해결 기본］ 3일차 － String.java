
import java.io.*;
import java.util.*;

class Point {
	int x;
	int y;
	
	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Solution {
	
	private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static int T, N, M;
	private static String findString;
	private static String string;
	
	
	public static void main(String args[]) throws Exception {
		solution();
	}
	
	private static void solution() throws IOException {
		T = 10;
		for (int testCase = 1; testCase <= T; testCase++) {
			initFindString();
			initString();
			printResult(testCase, calculateCount());
		}
		bw.flush();
		bw.close();
	}
	
	private static void initT() throws IOException {
		T = Integer.parseInt(bf.readLine());
	}
	
	private static void initFindString() throws IOException {
		bf.readLine();
		findString = bf.readLine();
	}
	
	private static void initString() throws IOException {
		string = bf.readLine();
	}
	
	private static int calculateCount() {
		int size = string.length();
		
		string = string.replaceAll(findString, "");
		
		int changeSize = size - string.length();
		
		return (changeSize / findString.length());
	}
		
	private static void printResult(int testCase, int result) throws IOException {
		bw.write("#"+testCase + " " +result);
		bw.write("\n");
	}
}

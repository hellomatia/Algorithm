
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
	private static int T, H, W;
	private static String num;
	private static String[] deleteNums = {"00", "11", "22", "33", "44", "55", "66", "77", "88", "99"};
	
	
	public static void main(String args[]) throws Exception {
		solution();
	}
	
	private static void solution() throws IOException {
		T = 10;
		for (int testCase = 1; testCase <= T; testCase++) {
			initString();
			findPW();
			printResult(testCase);
		}
		bw.flush();
		bw.close();
	}
	
	private static void initT() throws IOException {
		T = Integer.parseInt(bf.readLine());
	}
	
	private static void initString() throws IOException {
		StringTokenizer st = new StringTokenizer(bf.readLine());
		st.nextToken();
		num = st.nextToken();
	}
	
	private static void findPW() {
		boolean isChange = true;
		int beforeSize  = num.length();

		while (isChange) {
			for (String delete : deleteNums) {
				num = num.replaceAll(delete, "");
			}
			if (beforeSize == num.length()) {
				isChange = false;
			}
			beforeSize = num.length();
		}
	}
	
	private static void printResult(int testCase) throws IOException {
		bw.write("#"+testCase + " " +num);
		bw.write("\n");
	}
}

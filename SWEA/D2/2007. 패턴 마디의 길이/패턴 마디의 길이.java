
import java.io.*;
import java.util.*;

import com.sun.imageio.plugins.wbmp.WBMPImageReader;

public class Solution {
	private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static int T;
	private static String string;
	private static String madi;
	
	
	public static void main(String[] args) throws IOException {
		initT();
		for (int testCase = 1; testCase <= T; testCase++) {
			initString();
			findMadi();
			printResult(testCase);
		}
		bw.flush();
		bw.close();
	}
	
	private static void initT() throws IOException {
		T = Integer.parseInt(bf.readLine());
	}
	
	private static void initString() throws IOException {
		string = bf.readLine();
	}
	
	private static void findMadi() {
		for (int i = 1; i <= 10; i++) {
			String madi1 = makeMadi(0, i);
			String madi2 = makeMadi(i, i);
			if (madi1.equals(madi2)) {
				madi = madi1;
				return;
			}
		}
	}
	
	private static String makeMadi(int start, int length) {
		StringBuilder sb = new StringBuilder();
		for (int i = start; i < start + length; i++) {
			sb.append(string.charAt(i));
		}
		
		return sb.toString();
	}
	
	private static int calculateMadiCount() {
		return string.length() / madi.length();
	}
	
	private static void printResult(int testCase) throws IOException {
		bw.write("#" + testCase + " " + madi.length());
		bw.write("\n");
	}
}

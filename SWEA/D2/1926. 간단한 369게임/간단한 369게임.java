
import java.io.*;
import java.util.*;


public class Solution {
	private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static int num;
	private static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		initNum();
		findResult();
		printResult(sb.toString());
		bw.flush();
		bw.close();
	}
	
	private static void initNum() throws IOException {
		num = Integer.parseInt(bf.readLine());
	}
	
	private static void findResult() {
		for (int i = 1; i <= num; i++) {
			sb.append(convert(i)).append(" ");
		}
	}
	
	private static String convert(int num) {
		String string = String.valueOf(num);
		StringBuilder temp = new StringBuilder();
		
		for (int i = 0; i < string.length(); i++) {
			char  ch = string.charAt(i);
			if (ch == '3' || ch == '6' || ch == '9') {
				temp.append("-");
			}
		}
		
		if (temp.length() == 0) {
			return String.valueOf(num);
		}
		return temp.toString();
	}
	
	private static void printResult(String result) throws IOException {
		bw.write(result);
		bw.write("\n");
	}
	
}

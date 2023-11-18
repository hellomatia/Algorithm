
import java.io.*;
import java.util.*;

public class Solution {

	private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static int T;
	private static int[] nums;
	private static int min;
	private static int max;
	
	
	public static void main(String[] args) throws IOException {
		initT();
		for (int i = 1; i <= T; i++) {
			min = Integer.MAX_VALUE;
			max = Integer.MIN_VALUE;
			initNum();
			exploreNums(0);
			printResult(i, min, max);
			bw.flush();
		}
		bw.close();
		
	}
	
	private static void initT() throws IOException {
		T = Integer.parseInt(bf.readLine());
	}
	
	private static void initNum() throws IOException {
		String string = bf.readLine();
		nums = new int[string.length()];
		for (int i = 0; i < string.length(); i++) {
			nums[i] = string.charAt(i) - '0';
		}
		makeNumber(nums);
	}
	
	private static void exploreNums(int start) {
		if (start == nums.length) {
			return;
		}
		for (int i = start + 1; i < nums.length; i++) {
			if (start == 0 && nums[i] == 0) {
				continue;
			}
			makeNumber(change(start, i));
		}
		exploreNums(start + 1);
	}
	
	private static int[] change(int index1, int index2) {
		int[] cloneNums = nums.clone();
		cloneNums[index1] = nums[index2];
		cloneNums[index2] = nums[index1];
		return cloneNums;
	}
	
	private static void makeNumber(int[] nums) {
		StringBuilder sBuilder = new StringBuilder();
		for(int num : nums) {
			sBuilder.append(num);
		}
		min = Math.min(min, Integer.parseInt(sBuilder.toString()));
		max = Math.max(max, Integer.parseInt(sBuilder.toString()));
	}
	
	private static void printResult(int testCase, int result1, int result2) throws IOException {
		bw.write("#" + testCase + " " + result1 + " " + result2);
		bw.write("\n");
	}
}

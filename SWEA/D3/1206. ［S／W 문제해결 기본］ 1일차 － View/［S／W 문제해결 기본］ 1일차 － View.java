import java.io.*;
import java.util.*;

public class Solution {
	
	static private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int T = 10;
	static int buildingCount;
	static int[] buildings;
	
	public static void main(String args[]) throws Exception {
		solution();
	}
	
	private static void solution() throws IOException {
		for (int testCase = 1; testCase <= T; testCase++) {
			init();
			printResult(testCase, findViewGood());
		}
		bw.flush();
		bw.close();
	}
	
	private static void init() throws IOException {
		initBuildingCount();
		initBuildings();
	}
	
	private static void initBuildingCount() throws IOException {
		buildingCount = Integer.parseInt(bf.readLine());
	}
	
	private static void initBuildings() throws IOException {
		buildings = new int[buildingCount];
		StringTokenizer st = new StringTokenizer(bf.readLine());
		for (int i = 0; i < buildingCount; i++) {
			buildings[i] = Integer.parseInt(st.nextToken());
		}
	}
	
	private static int findViewGood() {
		int result = 0;
		for (int i = 2; i < buildingCount - 2; i++) {
			result += findTwoPoint(i);
		}
		return result;
	}
	
	private static int findTwoPoint(int index) {
		int maxPoint = Integer.MIN_VALUE;
		
		maxPoint = Math.max(buildings[index - 2], maxPoint);
		maxPoint = Math.max(buildings[index - 1], maxPoint);
		maxPoint = Math.max(buildings[index + 1], maxPoint);
		maxPoint = Math.max(buildings[index + 2], maxPoint);

		if (maxPoint < buildings[index]) {
			return buildings[index] - maxPoint;
		}
		return 0;
	}
	
	private static void printResult(int testCase, int result) throws IOException {
		bw.write("#"+testCase+" "+result + "\n");
	}
}

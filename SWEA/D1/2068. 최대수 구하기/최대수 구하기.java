import java.io.*;
import java.util.*;

public class Solution {

    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int count;
    private static int[] nums;

    public static void main(String[] args) throws IOException {
        new Solution().solution();
    }

    private void solution() throws IOException {
        int testCase = Integer.parseInt(bf.readLine());
        for (int t = 1; t <= testCase; t++) {
            init();
            printResult(t, calcResult());
        }
        bw.flush();
        bw.close();
    }

    private void init() throws IOException {
        count = 10;
        nums = new int[count];
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < count; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
    }

    private int calcResult() {
        Arrays.sort(nums);
        return nums[count - 1];
    }

    private void printResult(int testCase, int result) throws IOException {
        bw.write("#"+testCase + " " + result + "\n");
    }
}

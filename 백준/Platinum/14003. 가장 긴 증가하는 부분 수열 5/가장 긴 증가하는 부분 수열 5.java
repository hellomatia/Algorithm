import java.io.*;
import java.util.*;

public class Main {

    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int N;
    private static int maxIndex;
    private static int[] nums;
    private static int[] increaseNums;
    private static int[] indexs;


    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        printResult(calcMaxLength());
        bw.flush();
        bw.close();
    }

    private void init() throws IOException {
        N = Integer.parseInt(bf.readLine());
        nums = new int[N];
        increaseNums = new int[N];

        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        increaseNums[0] = nums[0];
        maxIndex = 1;
    }

    private int calcMaxLength() {
        indexs = new int[N];
        for (int i = 1; i < N; i++) {
            int index = Arrays.binarySearch(increaseNums, 0, maxIndex, nums[i]);
            if (index < 0) {
                index = Math.abs(index) - 1;
            }
            increaseNums[index] = nums[i];
            if (index == maxIndex) {
                maxIndex++;
            }
            indexs[i] = index;
        }
        return maxIndex;
    }

    private void printResult(int result) throws IOException {
        bw.write(result + "\n");
        String[] results = new String[result];
        result--;
        for (int i = N - 1; i >= 0; i--) {
            if (result == -1) {
                break;
            }
            if (indexs[i] == result) {
                results[result] = String.valueOf(nums[i]);
                result--;
            }
        }
        bw.write(String.join(" ", results) + "\n");
    }
}

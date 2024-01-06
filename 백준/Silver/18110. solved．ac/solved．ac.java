import java.io.*;
import java.util.*;

public class Main {

    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int n;
    private static int[] nums;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        printResult(calcResult());
        bw.flush();
        bw.close();
    }

    private void init() throws IOException {
        n = Integer.parseInt(bf.readLine());
        nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(bf.readLine());
        }
    }

    private int calcResult() {
        Arrays.sort(nums);
        int exceptNum = (int) Math.round(n * 0.15D);
        int sum = 0;
        for (int i = exceptNum; i < n - exceptNum; i++) {
            sum += nums[i];
        }
        return (int) Math.round((double) sum / (n - 2 * exceptNum));
    }

    private void printResult(int result) throws IOException {
        bw.write(result + "\n");
    }
}

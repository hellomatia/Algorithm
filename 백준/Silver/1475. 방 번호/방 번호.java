import java.io.*;
import java.util.*;


public class Main {

    private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private int N;
    private int[] nums;

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
        N = Integer.parseInt(bf.readLine());
    }

    private int calcResult() {
        nums = new int[10];
        for (int n = N; n != 0; n /= 10) {
            int digit = n % 10;
            if(digit == 9) {
                digit = 6;
            }
            nums[digit]++;
        }

        nums[6]++;
        nums[6] /= 2;

        int result = 0;
        for (int count : nums) {
            result = Math.max(result, count);
        }

        return result;
    }

    private void printResult(int result) throws IOException {
        bw.write(result + "\n");
    }
}

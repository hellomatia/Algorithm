import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int N;
    private static int sum;
    private static int[] nums;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        printResult(calcArithmeticMean());
        printResult(calcMedian());
        printResult(calcMode());
        printResult(calcRange());
        bw.flush();
        bw.close();
    }

    private void init() throws IOException {
        N = Integer.parseInt(bf.readLine());
        nums = new int[N];
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(bf.readLine());
            nums[i] = num;
            sum += num;
        }
        Arrays.sort(nums);
    }

    private int calcArithmeticMean() {
        return (int) Math.round((double) sum / N);
    }

    private int calcMedian() {
        return nums[N / 2];
    }

    private int calcMode() {
        int count = 0;
        int max = -1;
        int mod = nums[0];

        boolean check = false;

        for(int i = 0; i < N - 1; i++) {
            if(nums[i] == nums[i + 1]) {
                count++;
            }else {
                count = 0;
            }

            if(max < count) {
                max = count;
                mod = nums[i];
                check = true;
            }else if(max == count && check == true) {
                mod = nums[i];
                check = false;
            }
        }
        return mod;
    }

    private int calcRange() {
        return nums[N - 1] - nums[0];
    }

    private void printResult(int result) throws IOException {
        bw.write(result + "\n");
    }
}

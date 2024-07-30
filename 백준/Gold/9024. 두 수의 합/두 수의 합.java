import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static final BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private int t, n,  k;
    private int[] nums;


    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        t = Integer.parseInt(bf.readLine());
        for (int i = 1; i <= t; i++) {
            init();
            printAns(calcAns());
            nums = null;
        }
    }

    private void init() throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        nums = new int[n];
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(nums);
    }

    private String calcAns() {
        int forward = 0;
        int backward = n - 1;

        int minDiff = Integer.MAX_VALUE;
        int ans = 0;
        while (forward < backward) {
            int sum = nums[forward] + nums[backward];

            if (Math.abs(sum - k) < minDiff) {
                minDiff = Math.abs(sum - k);
                ans = 1;
            } else if (Math.abs(sum - k) == minDiff) {
                ans++;
            }

            if (sum < k) {
             forward++;
            } else {
                backward--;
            }
        }
        return ans + "";
    }

    private void printAns(String ans) throws IOException {
        bw.write(ans + "\n");
        bw.flush();
    }
}

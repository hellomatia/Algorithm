import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    private static final BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private int n, m;
    private int[] hints;
    private int ans;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        printAns(calcAns());
    }

    private void init() throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        hints = new int[m];
        if (m != 0) {
            st = new StringTokenizer(bf.readLine());
        }
        for (int i = 0; i < m; i++) {
            hints[i] = Integer.parseInt(st.nextToken());
        }
    }

    private String calcAns() {
        int[] nums = new int[n];
        dfs(0, nums);
        return ans + "";
    }

    private void dfs(int count, int[] nums) {
        if (count == n) {
            if (hasHint(nums)) {
                ans++;
            }
            return;
        }

        for (int i = 0; i < 10; i++) {
            nums[count] = i;
            dfs(count + 1, nums);
        }
    }

    private boolean hasHint(int[] nums) {
        int count = 0;
        for (int hint : hints) {
            for (int num : nums) {
                if (hint == num) {
                    count++;
                    break;
                }
            }
        }
        return count == m;
    }

    private void printAns(String ans) throws IOException {
        bw.write(ans);
        bw.flush();
    }
}
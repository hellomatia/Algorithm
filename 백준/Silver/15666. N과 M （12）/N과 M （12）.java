import java.io.*;
import java.util.*;

public class Main {

    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int N;
    private static int M;
    private static int[] nums;
    private static Set<String> set;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        for (int i = 0; i < N; i++) {
            calcResult(i, 1, String.valueOf(nums[i]));
        }
        bw.flush();
        bw.close();
    }

    private void init() throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        nums = new int[N];
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(nums);

        set = new HashSet<>();
    }

    private void calcResult(int start, int count, String result) throws IOException {
        if (count == M) {
            if (!set.contains(result)) {
                set.add(result);
                printResult(result);
            }
            return;
        }

        for (int i = start; i < N; i++) {
            calcResult(i, count + 1, result + " " + nums[i]);
        }
    }

    private void printResult(String result) throws IOException {
        bw.write(result + "\n");
    }
}

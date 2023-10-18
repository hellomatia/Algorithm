import java.io.*;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Main {

    static final int INF = Integer.MAX_VALUE;
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N, S;
    static int minCount = INF;
    static long[] nums;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        findMincount();
        printResult();

        bw.flush();
        bw.close();
    }

    private void init() throws IOException {
        initialNANdS();
        initialNums();
    }

    private void initialNANdS() throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
    }

    private void initialNums() throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        nums = new long[N + 1];
        IntStream.range(0, N)
                .forEach(index -> nums[index] = Integer.parseInt(st.nextToken()));
    }

    private void findMincount() {
        int start = 0;
        int sum = 0;
        for (int end = 0; end <= N; end++) {
            sum += nums[end];
            while (sum >= S) {
                minCount = Math.min(minCount, end - start + 1);
                sum -= nums[start];
                start++;
            }
        }
    }

    private void printResult() throws IOException {
        if (minCount == INF) {
            bw.write("0" + "\n");
            return;
        }
        bw.write(minCount + "\n");
    }
}
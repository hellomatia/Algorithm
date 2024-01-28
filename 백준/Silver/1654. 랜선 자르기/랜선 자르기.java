import java.io.*;
import java.util.*;

public class Main {

    private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private int K, N;
    private long[] lines;

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
        StringTokenizer st = new StringTokenizer(bf.readLine());
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        lines = new long[K];
        for (int i = 0; i < K; i++) {
            lines[i] = Long.parseLong(bf.readLine());
        }
        Arrays.sort(lines);
    }

    private long calcResult() {
        long min = 0;
        long max = lines[K - 1] + 1;

        while (min < max) {
            long mid = (max + min) / 2;

            long count = calcCount(mid);

            if (count < N) {
                max = mid;
            } else {
                min = mid + 1;
            }
        }

        return min - 1;
    }

    private long calcCount(long size) {
        long count = 0;
        for (int i = 0; i < K; i++) {
            count += (lines[i] / size);
        }
        return count;
    }

    private void printResult(long result) throws IOException {
        bw.write(result + "\n");
    }
}

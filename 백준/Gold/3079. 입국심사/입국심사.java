import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    private final static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private final static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private int N, M;
    private long[] times;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        printAns(calcAns());
    }

    private void init() throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        times = new long[N];
        for (int i = 0; i < N; i++) {
            times[i] = Integer.parseInt(bf.readLine());
        }
        Arrays.sort(times);
    }

    private String calcAns() {
        long start = 1;
        long end = M * times[N - 1];
        long ans = end;

        while (start <= end) {
            long mid = start + end >>> 1;
            long count = 0;
            for (long time : times) {
                count += mid / time;
                if (count >= M) {
                    break;
                }
            }
            if (count >= M) {
                ans = Math.min(ans, mid);
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return ans + "";
    }

    private void printAns(String ans) throws IOException {
        bw.write(ans + "\n");
        bw.flush();
    }
}
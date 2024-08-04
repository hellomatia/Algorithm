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

    private final int START_DAY = 301;
    private final int END_DAY = 1201;
    private int n;
    private Flower[] flowers;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        printAns(calcAns());
    }

    private void init() throws IOException {
        n = Integer.parseInt(bf.readLine());
        flowers = new Flower[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int sM = Integer.parseInt(st.nextToken());
            int sD = Integer.parseInt(st.nextToken());
            int eM = Integer.parseInt(st.nextToken());
            int eD = Integer.parseInt(st.nextToken());

            int start = sM * 100 + sD;
            int end = eM * 100 + eD;
            flowers[i] = new Flower(start, end);
        }

        Arrays.sort(flowers);
    }

    private String calcAns() {
        int ans = 0;
        int start = START_DAY;
        int max = 0;
        int index = 0;
        while (start < END_DAY) {
            boolean finished = false;
            for (int i = index; i < n; i++) {
                if (flowers[i].start > start) {
                    break;
                }
                if (max < flowers[i].end) {
                    finished = true;
                    max = flowers[i].end;
                    index = i + 1;
                }
            }
            if (finished) {
                start = max;
                ans++;
            } else {
                break;
            }
        }
        if (max < END_DAY) {
            return "0";
        }
        return ans + "";
    }

    private void printAns(String ans) throws IOException {
        bw.write(ans + "\n");
        bw.flush();
    }

    static class Flower implements Comparable<Flower> {
        int start;
        int end;

        Flower(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Flower o) {
            if (this.start == o.start) {
                return o.end - end;
            }
            return start - o.start;
        }
    }
}

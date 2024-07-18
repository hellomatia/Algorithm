import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    private static final BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private int c, d, n;
    private int[] array;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        for (int i = 0; i < c; i++) {
            initTestCase();
            printAns(calcAns());
        }
    }

    private void init() throws IOException {
        c = Integer.parseInt(bf.readLine());
    }

    private void initTestCase() throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        d = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(bf.readLine());
        array = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            array[i] = (array[i - 1] + Integer.parseInt(st.nextToken())) % d;
        }
    }

    private String calcAns() {
        int[] count = new int[d + 1];
        int ans = 0;
        for (int i = 0; i <= n; i++) {
            ans += count[array[i]];
            count[array[i]]++;
        }
        return ans + "";
    }

    private void printAns(String ans) throws IOException {
        bw.write(ans + "\n");
        bw.flush();
    }
}

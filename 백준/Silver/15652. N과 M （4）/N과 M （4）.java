import java.io.*;
import java.util.*;

public class Main {

    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int N;
    private static int M;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        for (int i = 1; i <= N; i++) {
            calcResult(i, 1, String.valueOf(i));
        }
        bw.flush();
        bw.close();
    }

    private void init() throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
    }

    private void calcResult(int start, int count, String nums) throws IOException {
        if (count == M) {
            printResult(nums);
            return;
        }

        for (int i = start; i <= N; i++) {
            calcResult(i, count + 1, nums + " " +i);
        }
    }

    private void printResult(String result) throws IOException {
        bw.write(result + "\n");
    }
}

import java.io.*;
import java.util.*;

public class Main {

    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int[] num;

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
        num = new int[5];
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < 5; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }
    }

    private int calcResult() {
        return Arrays.stream(num)
                .map(it -> it * it)
                .sum() % 10;
    }

    private void printResult(int result) throws IOException {
        bw.write(result + "\n");
    }
}

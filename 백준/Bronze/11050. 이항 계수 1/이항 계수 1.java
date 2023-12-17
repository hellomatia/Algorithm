import java.io.*;
import java.util.*;

public class Main {

    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int N, K;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        printResult(calc());
        bw.flush();
        bw.close();
    }

    private void init() throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
    }

    private int calc() {
        return factorial(N) / (factorial(N - K) * factorial(K));
    }

    private int factorial(int num) {
        if (num <= 1) return 1;
        return num * factorial(num - 1);
    }

    private void printResult(int result) throws IOException {
        bw.write(result + "\n");
    }
}
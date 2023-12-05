import java.io.*;
import java.util.*;

public class Main {

    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int N;
    private static Deque<Integer> deque;

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
        N = Integer.parseInt(bf.readLine());
        deque = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) {
            deque.offer(i);
        }
    }

    private String calcResult() {
        while (deque.size() != 1) {
            deque.poll();
            deque.offer(deque.poll());
        }
        return String.valueOf(deque.poll());
    }

    private void printResult(String result) throws IOException {
        bw.write(result);
        bw.write("\n");
    }
}

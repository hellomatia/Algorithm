import java.io.*;
import java.util.*;

public class Main {

    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int N, K;
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
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        deque = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) {
            deque.offer(i);
        }
    }

    private String calcResult() {
        StringBuilder sb = new StringBuilder("<");
        String delimiter = ", ";
        while (!deque.isEmpty()) {
            for (int i = 0; i < K - 1; i++) {
                deque.offer(deque.poll());
            }
            sb.append(deque.poll());
            if (!deque.isEmpty()) {
                sb.append(delimiter);
            }
        }
        sb.append(">");
        return sb.toString();
    }

    private void printResult(String result) throws IOException {
        bw.write(result);
        bw.write("\n");
    }
}

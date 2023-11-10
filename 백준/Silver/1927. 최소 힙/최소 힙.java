import java.io.*;
import java.util.*;

public class Main {

    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int N;
    private static PriorityQueue<Integer> pq = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        for (int i = 0; i < N; i++) {
            calculateMinNum();
        }
        bw.flush();
        bw.close();
    }

    private void init() throws IOException {
        initN();
    }

    private void initN() throws IOException {
        N = Integer.parseInt(bf.readLine());
    }

    private void calculateMinNum() throws IOException {
        int num = Integer.parseInt(bf.readLine());
        if (num == 0) {
            if (pq.isEmpty()) {
                printResult(0);
            } else {
                printResult(pq.poll());
            }
            return;
        }
        pq.offer(num);
    }

    private void printResult(int result) throws IOException {
        bw.write(result + "\n");
    }
}

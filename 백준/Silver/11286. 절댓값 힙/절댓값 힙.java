import java.io.*;
import java.util.*;

public class Main {

    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int N;
    private static PriorityQueue<Integer> pq;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        for (int i = 0; i < N; i++) {
            calcResult(Integer.parseInt(bf.readLine()));
        }
        bw.flush();
        bw.close();
    }

    private void init() throws IOException {
        N = Integer.parseInt(bf.readLine());
        pq = new PriorityQueue<>((o1, o2) -> {
            if (Math.abs(o1) == Math.abs(o2)) {
                return o1 - o2;
            }
            return Math.abs(o1) - Math.abs(o2);
        });
    }

    private void calcResult(int num) throws IOException {
        if (num == 0) {
            if (pq.isEmpty()) {
                printResult(0);
                return;
            }
            int minNum = pq.poll();
            printResult(minNum);
            return;
        }

        pq.offer(num);
    }

    private void printResult(int result) throws IOException {
        bw.write(result + "\n");
    }
}